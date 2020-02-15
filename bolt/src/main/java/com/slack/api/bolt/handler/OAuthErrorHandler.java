package com.slack.api.bolt.handler;

import com.slack.api.bolt.request.builtin.OAuthCallbackRequest;
import com.slack.api.bolt.response.Response;

@FunctionalInterface
public interface OAuthErrorHandler {

    Response handle(OAuthCallbackRequest request, Response response);

}
