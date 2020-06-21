package com.slack.api.methods.response.channels;

import com.slack.api.methods.SlackApiResponse;
import com.slack.api.model.User;
import lombok.Data;

@Deprecated // use com.slack.api.methods.response.users.UsersLookupByEmailResponse instead
@Data
public class UsersLookupByEmailResponse implements SlackApiResponse {

    private boolean ok;
    private String warning;
    private String error;
    private String needed;
    private String provided;

    private User user;
}
