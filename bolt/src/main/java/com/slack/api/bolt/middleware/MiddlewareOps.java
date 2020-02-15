package com.slack.api.bolt.middleware;

import com.slack.api.bolt.request.RequestType;

public class MiddlewareOps {
    private MiddlewareOps() {
    }

    public static boolean isNoSlackSignatureRequest(RequestType requestType) {
        return requestType == RequestType.OAuthStart
                || requestType == RequestType.OAuthCallback
                || requestType == RequestType.SSLCheck;
    }

    public static boolean isNoAuthRequiredRequest(RequestType requestType) {
        return isNoSlackSignatureRequest(requestType)
                || requestType == RequestType.UrlVerification;
    }
}
