package com.slack.api.bolt.handler;

import com.slack.api.bolt.context.Context;
import com.slack.api.bolt.request.Request;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;

import java.io.IOException;

@FunctionalInterface
public interface Handler<
        CTX extends Context,
        REQ extends Request<CTX>,
        RESP extends Response> {

    RESP apply(REQ req, CTX context) throws IOException, SlackApiException;

}
