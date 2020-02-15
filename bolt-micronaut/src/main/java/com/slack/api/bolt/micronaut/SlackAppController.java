package com.slack.api.bolt.micronaut;

import com.slack.api.bolt.App;
import com.slack.api.bolt.request.Request;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import java.util.LinkedHashMap;

@Controller("/slack")
public class SlackAppController {

    private final App slackApp;
    private final SlackAppMicronautAdapter adapter;

    public SlackAppController(App slackApp, SlackAppMicronautAdapter adapter) {
        this.slackApp = slackApp;
        this.adapter = adapter;
    }

    @Post(value = "/events", consumes = {MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public HttpResponse<String> dispatch(HttpRequest<String> request, @Body LinkedHashMap<String, String> body) throws Exception {
        Request<?> slackRequest = adapter.toSlackRequest(request, body);
        return adapter.toMicronautResponse(slackApp.run(slackRequest));
    }

}
