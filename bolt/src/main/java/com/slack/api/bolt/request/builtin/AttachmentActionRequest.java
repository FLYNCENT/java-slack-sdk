package com.slack.api.bolt.request.builtin;

import com.slack.api.app_backend.interactive_components.payload.AttachmentActionPayload;
import com.slack.api.bolt.context.builtin.ActionContext;
import com.slack.api.bolt.request.Request;
import com.slack.api.bolt.request.RequestHeaders;
import com.slack.api.bolt.request.RequestType;
import com.slack.api.util.json.GsonFactory;
import lombok.ToString;

@ToString(callSuper = true)
public class AttachmentActionRequest extends Request<ActionContext> {

    private final String requestBody;
    private final RequestHeaders headers;
    private final AttachmentActionPayload payload;

    public AttachmentActionRequest(
            String requestBody,
            String payloadBody,
            RequestHeaders headers) {
        this.requestBody = requestBody;
        this.headers = headers;
        this.payload = GsonFactory.createSnakeCase().fromJson(payloadBody, AttachmentActionPayload.class);
        getContext().setResponseUrl(payload.getResponseUrl());
        getContext().setEnterpriseId(payload.getTeam().getEnterpriseId());
        getContext().setTeamId(payload.getTeam().getId());
        getContext().setRequestUserId(payload.getUser().getId());
    }

    private ActionContext context = new ActionContext();

    @Override
    public ActionContext getContext() {
        return context;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.AttachmentAction;
    }

    @Override
    public String getRequestBodyAsString() {
        return requestBody;
    }

    @Override
    public RequestHeaders getHeaders() {
        return this.headers;
    }

    public AttachmentActionPayload getPayload() {
        return payload;
    }

}
