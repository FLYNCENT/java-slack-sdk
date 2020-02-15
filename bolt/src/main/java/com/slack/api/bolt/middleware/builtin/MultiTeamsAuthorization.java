package com.slack.api.bolt.middleware.builtin;

import com.slack.api.bolt.AppConfig;
import com.slack.api.bolt.context.Context;
import com.slack.api.bolt.middleware.Middleware;
import com.slack.api.bolt.middleware.MiddlewareChain;
import com.slack.api.bolt.model.Bot;
import com.slack.api.bolt.model.Installer;
import com.slack.api.bolt.request.Request;
import com.slack.api.bolt.response.Response;
import com.slack.api.bolt.service.InstallationService;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.auth.AuthTestResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.slack.api.bolt.middleware.MiddlewareOps.isNoAuthRequiredRequest;

@Slf4j
public class MultiTeamsAuthorization implements Middleware {

    private final InstallationService installationService;

    private boolean alwaysRequestUserTokenNeeded;

    public boolean isAlwaysRequestUserTokenNeeded() {
        return alwaysRequestUserTokenNeeded;
    }

    public void setAlwaysRequestUserTokenNeeded(boolean alwaysRequestUserTokenNeeded) {
        this.alwaysRequestUserTokenNeeded = alwaysRequestUserTokenNeeded;
    }

    public MultiTeamsAuthorization(AppConfig config, InstallationService installationService) {
        setAlwaysRequestUserTokenNeeded(config.isAlwaysRequestUserTokenNeeded());
        this.installationService = installationService;
    }

    @Override
    public Response apply(Request req, Response resp, MiddlewareChain chain) throws Exception {
        if (isNoAuthRequiredRequest(req.getRequestType())) {
            return chain.next(req);
        }

        Context context = req.getContext();

        String botToken = null;
        String userToken = null;

        Bot bot = installationService.findBot(context.getEnterpriseId(), context.getTeamId());
        Installer installer = null;
        if (bot != null) {
            botToken = bot.getBotAccessToken();
        }

        if ((isAlwaysRequestUserTokenNeeded() || bot == null) && context.getRequestUserId() != null) {
            // no bot for this app - try to fetch installer's access token instead
            installer = installationService.findInstaller(
                    context.getEnterpriseId(),
                    context.getTeamId(),
                    context.getRequestUserId()
            );
            if (installer != null) {
                userToken = installer.getInstallerUserAccessToken();
            }
        }

        if (botToken == null && userToken == null) {
            return buildError(401, null, null, null);
        }

        try {
            String token = botToken != null ? botToken : userToken;
            AuthTestResponse authTestResponse = context.client().authTest(r -> r.token(token));
            if (authTestResponse.isOk()) {
                context.setBotToken(botToken);
                context.setRequestUserToken(userToken);
                context.setTeamId(authTestResponse.getTeamId());
                context.setEnterpriseId(authTestResponse.getEnterpriseId());
                if (bot != null) {
                    context.setBotId(bot.getBotId());
                    context.setBotUserId(authTestResponse.getUserId());
                }
                return chain.next(req);
            } else {
                return handleAuthTestError(authTestResponse.getError(), bot, installer, authTestResponse);
            }
        } catch (IOException e) {
            return buildError(503, null, e, null);
        } catch (SlackApiException e) {
            return buildError(503, null, null, e);
        }
    }

    protected Response handleAuthTestError(
            String errorCode,
            Bot foundBot,
            Installer foundInstaller,
            AuthTestResponse authTestResponse) throws Exception {
        if (errorCode.equals("account_inactive")) {
            // this is not recoverable - going to remove the data not to repeat the same error
            if (foundBot != null) {
                installationService.deleteBot(foundBot);
            } else if (foundInstaller != null) {
                installationService.deleteInstaller(foundInstaller);
            }
        }
        return buildError(401, authTestResponse, null, null);
    }

    protected Response buildError(
            int statusCode,
            AuthTestResponse authTestResponse,
            IOException ioException,
            SlackApiException slackException) {

        log.info("auth.test result: {}, io error: {}, api error: {}", authTestResponse, ioException, slackException);

        return Response.builder()
                .statusCode(statusCode)
                .contentType(Response.CONTENT_TYPE_APPLICATION_JSON)
                .body("{\"error\":\"a request for an unknown workspace detected\"}")
                .build();
    }

}
