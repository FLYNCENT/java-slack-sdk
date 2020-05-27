package com.slack.api.methods.response.admin.teams;

import com.slack.api.methods.SlackApiResponse;
import com.slack.api.model.ResponseMetadata;
import lombok.Data;

import java.util.List;

@Data
public class AdminTeamsListResponse implements SlackApiResponse {

    private boolean ok;
    private String warning;
    private String error;
    private String needed;
    private String provided;

    private List<Team> teams;
    private ResponseMetadata responseMetadata;

    @Data
    public static class Team {
        private String id;
        private String name;
        private String teamUrl;
        private String discoverability;
        private PrimaryOwner primaryOwner;
    }

    @Data
    public static class PrimaryOwner {
        private String userId;
        private String email;
    }

}