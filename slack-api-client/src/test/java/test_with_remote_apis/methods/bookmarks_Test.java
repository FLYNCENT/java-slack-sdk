package test_with_remote_apis.methods;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.bookmarks.*;
import config.Constants;
import config.SlackTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class bookmarks_Test {

    static String botToken = System.getenv(Constants.SLACK_SDK_TEST_BOT_TOKEN);

    static SlackTestConfig testConfig = SlackTestConfig.getInstance();
    static Slack slack = Slack.getInstance(testConfig.getConfig());

    @AfterClass
    public static void tearDown() throws InterruptedException {
        SlackTestConfig.awaitCompletion(testConfig);
    }

    @Test
    public void noArgs() throws IOException, SlackApiException {
        {
            BookmarksListResponse response = slack.methods(botToken).bookmarksList(req -> req);
            assertThat(response.getError(), is(notNullValue()));
        }
        {
            BookmarksAddResponse response = slack.methods(botToken).bookmarksAdd(req -> req);
            assertThat(response.getError(), is(notNullValue()));
        }
        {
            BookmarksEditResponse response = slack.methods(botToken).bookmarksEdit(req -> req);
            assertThat(response.getError(), is(notNullValue()));
        }
        {
            BookmarksRemoveResponse response = slack.methods(botToken).bookmarksRemove(req -> req);
            assertThat(response.getError(), is(notNullValue()));
        }
    }

    @Test
    public void successfulCalls() throws IOException, SlackApiException {
        slack.methods(botToken).bookmarksAdd(req -> req);
    }
}