package com.slack.api.bolt.handler.builtin;

import com.slack.api.bolt.handler.OAuthStateErrorHandler;
import com.slack.api.bolt.request.builtin.OAuthCallbackRequest;
import com.slack.api.bolt.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class OAuthDefaultStateErrorHandler implements OAuthStateErrorHandler {

    @Override
    public Response handle(OAuthCallbackRequest request, Response response) {
        log.warn("Invalid state parameter detected - payload: {}", request.getPayload());

        response.setStatusCode(302);
        response.getHeaders().put("Location", Arrays.asList(request.getContext().getOauthCancellationUrl()));
        return response;
    }

}
