package com.slack.api.bolt.middleware;

import com.slack.api.bolt.request.Request;
import com.slack.api.bolt.response.Response;

@FunctionalInterface
public interface MiddlewareChain {

    Response next(Request req) throws Exception;

}
