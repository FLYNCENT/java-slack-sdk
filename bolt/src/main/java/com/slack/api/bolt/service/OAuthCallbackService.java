package com.slack.api.bolt.service;

import com.slack.api.bolt.request.builtin.OAuthCallbackRequest;
import com.slack.api.bolt.response.Response;

public interface OAuthCallbackService {

    Response handle(OAuthCallbackRequest request);

}
