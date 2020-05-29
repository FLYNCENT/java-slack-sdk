package com.slack.api.rtm;

import com.slack.api.methods.SlackApiException;
import com.slack.api.model.event.GoodbyeEvent;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
public class AutoReconnectHandler implements RTMMessageHandler {

    private final RTMMessageHandler underlying;

    private static class GoodbyeHandler extends RTMEventHandler<GoodbyeEvent> {
        final RTMClient rtmClient;
        public GoodbyeHandler(RTMClient rtmClient) {
            this.rtmClient = rtmClient;
        }
        @Override
        public void handle(GoodbyeEvent event) {
            log.info("As this connection received goodbye event, going to reconnect to Slack");
            try {
                this.rtmClient.reconnect();
            } catch (IOException|SlackApiException|URISyntaxException|DeploymentException e) {
                log.error("Failed to reconnect to Slack - {}", e.getMessage(), e);
            }
        }
    }

    public AutoReconnectHandler(RTMClient rtmClient) {
        RTMEventsDispatcher dispatcher = RTMEventsDispatcherFactory.getInstance();
        dispatcher.register(new GoodbyeHandler(rtmClient));
        this.underlying = dispatcher.toMessageHandler();
    }

    public void handle(String message) {
        this.underlying.handle(message);
    }
}
