package com.slack.api.methods.response.openid.connect;

import com.slack.api.methods.SlackApiTextResponse;
import lombok.Data;

/**
 * https://api.slack.com/methods/openid.connect.userInfo
 */
@Data
public class OpenIDConnectUserInfoResponse implements SlackApiTextResponse {

    private boolean ok;
    private String warning;
    private String error;
    private String needed;
    private String provided;

    // TODO

}
