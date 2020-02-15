package com.slack.api.bolt.handler.builtin;

import com.slack.api.bolt.context.builtin.ActionContext;
import com.slack.api.bolt.handler.Handler;
import com.slack.api.bolt.request.builtin.AttachmentActionRequest;
import com.slack.api.bolt.response.Response;

@FunctionalInterface
public interface AttachmentActionHandler extends Handler<ActionContext, AttachmentActionRequest, Response> {
}
