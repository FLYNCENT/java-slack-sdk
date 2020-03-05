package test_with_remote_apis.methods;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.request.chat.ChatUnfurlRequest;
import com.slack.api.methods.request.chat.ChatUpdateRequest;
import com.slack.api.methods.response.chat.*;
import com.slack.api.methods.response.chat.scheduled_messages.ChatScheduledMessagesListResponse;
import com.slack.api.methods.response.conversations.ConversationsListResponse;
import com.slack.api.methods.response.conversations.ConversationsMembersResponse;
import com.slack.api.model.Attachment;
import com.slack.api.model.Conversation;
import com.slack.api.model.User;
import com.slack.api.model.block.DividerBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.util.json.GsonFactory;
import config.Constants;
import config.SlackTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@Slf4j
public class chat_Test {

    String botToken = System.getenv(Constants.SLACK_SDK_TEST_BOT_TOKEN);
    String userToken = System.getenv(Constants.SLACK_SDK_TEST_USER_TOKEN);

    static SlackTestConfig testConfig = SlackTestConfig.getInstance();
    static Slack slack = Slack.getInstance(testConfig.getConfig());

    @AfterClass
    public static void tearDown() throws InterruptedException {
        SlackTestConfig.awaitCompletion(testConfig);
    }

    private String randomChannelId = null;

    void loadRandomChannelId() throws IOException, SlackApiException {
        if (randomChannelId == null) {
            ConversationsListResponse channelsListResponse =
                    slack.methods().conversationsList(r -> r.token(botToken).excludeArchived(true).limit(100));
            assertThat(channelsListResponse.getError(), is(nullValue()));
            for (Conversation channel : channelsListResponse.getChannels()) {
                if (channel.getName().equals("random")) {
                    randomChannelId = channel.getId();
                    break;
                }
            }
        }
    }

    String blocksAsString = "[\n" +
            "  {\n" +
            "    \"type\": \"section\",\n" +
            "    \"text\": {\n" +
            "      \"type\": \"mrkdwn\",\n" +
            "      \"text\": \"Hello, Assistant to the Regional Manager Dwight! *Michael Scott* wants to know where you'd like to take the Paper Company investors to dinner tonight.\\n\\n *Please select a restaurant:*\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"type\": \"divider\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"type\": \"section\",\n" +
            "    \"text\": {\n" +
            "      \"type\": \"mrkdwn\",\n" +
            "      \"text\": \"*Farmhouse Thai Cuisine*\\n:star::star::star::star: 1528 reviews\\n They do have some vegan options, like the roti and curry, plus they have a ton of salad stuff and noodles can be ordered without meat!! They have something for everyone here\"\n" +
            "    },\n" +
            "    \"accessory\": {\n" +
            "      \"type\": \"image\",\n" +
            "      \"image_url\": \"https://s3-media3.fl.yelpcdn.com/bphoto/c7ed05m9lC2EmA3Aruue7A/o.jpg\",\n" +
            "      \"alt_text\": \"alt text for image\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"type\": \"section\",\n" +
            "    \"text\": {\n" +
            "      \"type\": \"mrkdwn\",\n" +
            "      \"text\": \"*Kin Khao*\\n:star::star::star::star: 1638 reviews\\n The sticky rice also goes wonderfully with the caramelized pork belly, which is absolutely melt-in-your-mouth and so soft.\"\n" +
            "    },\n" +
            "    \"accessory\": {\n" +
            "      \"type\": \"image\",\n" +
            "      \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/korel-1YjNtFtJlMTaC26A/o.jpg\",\n" +
            "      \"alt_text\": \"alt text for image\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"type\": \"section\",\n" +
            "    \"text\": {\n" +
            "      \"type\": \"mrkdwn\",\n" +
            "      \"text\": \"*Ler Ros*\\n:star::star::star::star: 2082 reviews\\n I would really recommend the  Yum Koh Moo Yang - Spicy lime dressing and roasted quick marinated pork shoulder, basil leaves, chili & rice powder.\"\n" +
            "    },\n" +
            "    \"accessory\": {\n" +
            "      \"type\": \"image\",\n" +
            "      \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/DawwNigKJ2ckPeDeDM7jAg/o.jpg\",\n" +
            "      \"alt_text\": \"alt text for image\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"type\": \"divider\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"type\": \"actions\",\n" +
            "    \"elements\": [\n" +
            "      {\n" +
            "        \"type\": \"button\",\n" +
            "        \"text\": {\n" +
            "          \"type\": \"plain_text\",\n" +
            "          \"text\": \"Farmhouse\",\n" +
            "          \"emoji\": true\n" +
            "        },\n" +
            "        \"value\": \"click_me_123\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": \"button\",\n" +
            "        \"text\": {\n" +
            "          \"type\": \"plain_text\",\n" +
            "          \"text\": \"Kin Khao\",\n" +
            "          \"emoji\": true\n" +
            "        },\n" +
            "        \"value\": \"click_me_123\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": \"button\",\n" +
            "        \"text\": {\n" +
            "          \"type\": \"plain_text\",\n" +
            "          \"text\": \"Ler Ros\",\n" +
            "          \"emoji\": true\n" +
            "        },\n" +
            "        \"value\": \"click_me_123\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]";

    @Test
    public void postMessage_bot() throws Exception {
        loadRandomChannelId();
        ChatPostMessageResponse response = slack.methods(botToken).chatPostMessage(req -> req
                .channel(randomChannelId)
                .text("You can also do slack.methods(token)"));
        assertThat(response.getError(), is(nullValue()));
    }

    @Test
    public void postMessage_user() throws Exception {
        loadRandomChannelId();
        ChatPostMessageResponse response = slack.methods(userToken).chatPostMessage(req -> req
                .channel(randomChannelId)
                .text("You can also do slack.methods(token)"));
        assertThat(response.getError(), is(nullValue()));
    }

    // https://github.com/slackapi/java-slack-sdk/issues/157
    @Test
    public void postMessage_blocksInAttachment_do_not_work_bot() throws Exception {
        loadRandomChannelId();
        ChatPostMessageResponse firstMessageCreation = slack.methods().chatPostMessage(req -> req
                .channel(randomChannelId)
                .token(botToken)
                .attachments(Arrays.asList(
                        Attachment
                                .builder()
                                .id(123)
                                .callbackId("callback")
                                .title("hi")
                                .blocks(Arrays.asList(DividerBlock.builder().blockId("123").build()))
                                .build())));
        assertThat(firstMessageCreation.getError(), is("invalid_attachments"));
    }

    @Test
    public void postMessage_blocksInAttachment_do_not_work_user() throws Exception {
        loadRandomChannelId();
        ChatPostMessageResponse firstMessageCreation = slack.methods().chatPostMessage(req -> req
                .channel(randomChannelId)
                .token(userToken)
                .attachments(Arrays.asList(
                        Attachment
                                .builder()
                                .id(123)
                                .callbackId("callback")
                                .title("hi")
                                .blocks(Arrays.asList(DividerBlock.builder().blockId("123").build()))
                                .build())));
        assertThat(firstMessageCreation.getError(), is("invalid_attachments"));
    }

    @Test
    public void chat_getPermalink_bot() throws IOException, SlackApiException {
        ConversationsListResponse channels = slack.methods().conversationsList(req -> req
                .token(botToken)
                .excludeArchived(true));
        assertThat(channels.getError(), is(nullValue()));
        assertThat(channels.isOk(), is(true));

        String channelId = channels.getChannels().get(0).getId();

        ChatPostMessageResponse postResponse = slack.methods().chatPostMessage(req -> req
                .channel(channelId)
                .token(botToken)
                .text("Hi, this is a test message from Java Slack SDK's unit tests")
                .linkNames(true));
        assertThat(postResponse.getError(), is(nullValue()));
        assertThat(postResponse.isOk(), is(true));

        ChatGetPermalinkResponse permalink = slack.methods().chatGetPermalink(req -> req
                .token(botToken)
                .channel(channelId)
                .messageTs(postResponse.getTs()));
        assertThat(permalink.getError(), is(nullValue()));
        assertThat(permalink.isOk(), is(true));
        assertThat(permalink.getPermalink(), is(notNullValue()));
    }

    @Test
    public void chat_getPermalink_user() throws IOException, SlackApiException {
        ConversationsListResponse channels = slack.methods().conversationsList(req -> req
                .token(userToken)
                .excludeArchived(true));
        assertThat(channels.getError(), is(nullValue()));
        assertThat(channels.isOk(), is(true));

        String channelId = channels.getChannels().get(0).getId();

        ChatPostMessageResponse postResponse = slack.methods().chatPostMessage(req -> req
                .channel(channelId)
                .token(userToken)
                .text("Hi, this is a test message from Java Slack SDK's unit tests")
                .linkNames(true));
        assertThat(postResponse.getError(), is(nullValue()));
        assertThat(postResponse.isOk(), is(true));

        ChatGetPermalinkResponse permalink = slack.methods().chatGetPermalink(req -> req
                .token(userToken)
                .channel(channelId)
                .messageTs(postResponse.getTs()));
        assertThat(permalink.getError(), is(nullValue()));
        assertThat(permalink.isOk(), is(true));
        assertThat(permalink.getPermalink(), is(notNullValue()));
    }

    @Test
    public void chat_getPermalink_bot_async() throws ExecutionException, InterruptedException {
        ConversationsListResponse channels = slack.methodsAsync().conversationsList(req -> req
                .token(botToken)
                .excludeArchived(true))
                .get();
        assertThat(channels.getError(), is(nullValue()));
        assertThat(channels.isOk(), is(true));

        String channelId = channels.getChannels().get(0).getId();

        ChatPostMessageResponse postResponse = slack.methodsAsync().chatPostMessage(req -> req
                .channel(channelId)
                .token(botToken)
                .text("Hi, this is a test message from Java Slack SDK's unit tests")
                .linkNames(true))
                .get();
        assertThat(postResponse.getError(), is(nullValue()));
        assertThat(postResponse.isOk(), is(true));

        ChatGetPermalinkResponse permalink = slack.methodsAsync().chatGetPermalink(req -> req
                .token(botToken)
                .channel(channelId)
                .messageTs(postResponse.getTs()))
                .get();
        assertThat(permalink.getError(), is(nullValue()));
        assertThat(permalink.isOk(), is(true));
        assertThat(permalink.getPermalink(), is(notNullValue()));
    }

    // It's also possible to post a message by giving the name of a channel.
    //
    // https://api.slack.com/methods/chat.postMessage#channels
    // You can either pass the channel's name (#general) or encoded ID (C024BE91L),
    // and the message will be posted to that channel.
    // The channel's ID can be retrieved through the channels.list API method.
    //
    // https://github.com/slackapi/python-slackclient/blob/master/README.md#sending-a-message-to-slack
    //  In our examples, we specify the channel name, however it is recommended to use the channel_id where possible.
    @Test
    public void postingWithChannelName() throws IOException, SlackApiException {
        makeSureIfGivingChannelNameWorks("random");
        makeSureIfGivingChannelNameWorks("#random");
    }

    private void makeSureIfGivingChannelNameWorks(String channelName) throws IOException, SlackApiException {
        ChatPostMessageResponse response = slack.methods().chatPostMessage(ChatPostMessageRequest.builder()
                .token(botToken)
                .channel(channelName)
                .text("Hello!")
                .build());

        assertThat(response.getError(), is(nullValue()));
        assertThat(response.getChannel(), is(startsWith("C")));
        assertThat(response.getMessage().getText(), is(startsWith("Hello!")));
    }

    // NOTE: You need to add "youtube.com" at
    // Features > Event Subscriptions > App Unfurl Domains
    @Test
    public void unfurl_raw_json_bot() throws Exception {
        loadRandomChannelId();

        String url = "https://www.youtube.com/watch?v=wq1R93UMqlk";
        ChatPostMessageResponse postResponse = slack.methods().chatPostMessage(ChatPostMessageRequest.builder()
                .token(userToken)
                .channel(randomChannelId)
                .text(url)
                .unfurlLinks(true)
                .unfurlMedia(true)
                .build());
        assertThat(postResponse.getError(), is(nullValue()));

        String ts = postResponse.getTs();
        Map<String, ChatUnfurlRequest.UnfurlDetail> unfurls = new HashMap<>();
        ChatUnfurlRequest.UnfurlDetail detail = new ChatUnfurlRequest.UnfurlDetail();
        detail.setText("Every day is the test.");
        unfurls.put(url, detail);

        ChatUnfurlResponse unfurlResponse = slack.methods().chatUnfurl(ChatUnfurlRequest.builder()
                .token(botToken)
                .channel(randomChannelId)
                .ts(ts)
                .rawUnfurls(GsonFactory.createSnakeCase().toJson(unfurls))
                .build());
        assertThat(unfurlResponse.getError(), is(nullValue()));
    }

    @Test
    public void unfurl_raw_json_user() throws Exception {
        loadRandomChannelId();

        String url = "https://www.youtube.com/watch?v=wq1R93UMqlk";
        ChatPostMessageResponse postResponse = slack.methods().chatPostMessage(ChatPostMessageRequest.builder()
                .token(userToken)
                .channel(randomChannelId)
                .text(url)
                .unfurlLinks(true)
                .unfurlMedia(true)
                .build());
        assertThat(postResponse.getError(), is(nullValue()));

        String ts = postResponse.getTs();
        Map<String, ChatUnfurlRequest.UnfurlDetail> unfurls = new HashMap<>();
        ChatUnfurlRequest.UnfurlDetail detail = new ChatUnfurlRequest.UnfurlDetail();
        detail.setText("Every day is the test.");
        unfurls.put(url, detail);

        ChatUnfurlResponse unfurlResponse = slack.methods().chatUnfurl(ChatUnfurlRequest.builder()
                .token(userToken)
                .channel(randomChannelId)
                .ts(ts)
                .rawUnfurls(GsonFactory.createSnakeCase().toJson(unfurls))
                .build());
        assertThat(unfurlResponse.getError(), is(nullValue()));
    }

    // NOTE: You need to add "youtube.com" at
    // Features > Event Subscriptions > App Unfurl Domains
    @Test
    public void unfurl_text() throws Exception {
        loadRandomChannelId();

        String url = "https://www.youtube.com/watch?v=wq1R93UMqlk";
        ChatPostMessageResponse postResponse = slack.methods().chatPostMessage(ChatPostMessageRequest.builder()
                .token(botToken)
                .channel(randomChannelId)
                .text(url)
                .unfurlLinks(true)
                .unfurlMedia(true)
                .build());
        assertThat(postResponse.getError(), is(nullValue()));

        String ts = postResponse.getTs();
        Map<String, ChatUnfurlRequest.UnfurlDetail> unfurls = new HashMap<>();
        ChatUnfurlRequest.UnfurlDetail detail = new ChatUnfurlRequest.UnfurlDetail();
        detail.setText("Every day is the test.");
        unfurls.put(url, detail);

        ChatUnfurlResponse unfurlResponse = slack.methods().chatUnfurl(ChatUnfurlRequest.builder()
                .token(botToken)
                .channel(randomChannelId)
                .ts(ts)
                .unfurls(unfurls)
                .build());
        assertThat(unfurlResponse.getError(), is(nullValue()));
    }

    @Test
    public void unfurl_blocks() throws Exception {
        loadRandomChannelId();

        String url = "https://www.youtube.com/watch?v=wq1R93UMqlk";
        ChatPostMessageResponse postResponse = slack.methods().chatPostMessage(ChatPostMessageRequest.builder()
                .token(botToken)
                .channel(randomChannelId)
                .text(url)
                .unfurlLinks(true)
                .unfurlMedia(true)
                .build());
        assertThat(postResponse.getError(), is(nullValue()));

        String ts = postResponse.getTs();
        Map<String, ChatUnfurlRequest.UnfurlDetail> unfurls = new HashMap<>();
        ChatUnfurlRequest.UnfurlDetail detail = new ChatUnfurlRequest.UnfurlDetail();
        detail.setBlocks(Arrays.asList(DividerBlock.builder().blockId("123").build()));
        unfurls.put(url, detail);

        ChatUnfurlResponse unfurlResponse = slack.methods().chatUnfurl(ChatUnfurlRequest.builder()
                .token(botToken)
                .channel(randomChannelId)
                .ts(ts)
                .unfurls(unfurls)
                .build());
        assertThat(unfurlResponse.getError(), is(nullValue()));
    }

    @Test
    public void chatUpdateWithBotToken_issue_372() throws IOException, SlackApiException {
        loadRandomChannelId();
        ChatPostMessageResponse creation = slack.methods(botToken).chatPostMessage(r -> r.channel(randomChannelId).text("This is original"));
        assertThat(creation.getError(), is(nullValue()));
        ChatUpdateResponse modified = slack.methods(botToken).chatUpdate(r -> r.channel(randomChannelId).text("modified").ts(creation.getTs()));
        assertThat(modified.getError(), is(nullValue()));
    }

    @Test
    public void postMessage_blocksAsString_bot() throws Exception {
        loadRandomChannelId();

        ChatPostMessageResponse postResponse = slack.methods().chatPostMessage(ChatPostMessageRequest.builder()
                .token(botToken)
                .channel(randomChannelId)
                .text("test")
                .blocksAsString(blocksAsString)
                .build());
        assertThat(postResponse.getError(), is(nullValue()));

        ChatUpdateResponse updateMessage = slack.methods().chatUpdate(ChatUpdateRequest.builder()
                .channel(randomChannelId)
                .token(botToken)
                .ts(postResponse.getTs())
                .text("modified")
                .blocksAsString(blocksAsString)
                .build());
        assertThat(updateMessage.getError(), is(nullValue()));

        // To show the text instead of blocks
        ChatUpdateResponse updateMessage2 = slack.methods().chatUpdate(ChatUpdateRequest.builder()
                .channel(randomChannelId)
                .token(botToken)
                .ts(postResponse.getTs())
                .blocksAsString("[]")
                .text("modified2")
                .build());
        assertThat(updateMessage2.getError(), is(nullValue()));
    }

    @Test
    public void postMessage_blocksAsString_user() throws Exception {
        loadRandomChannelId();

        ChatPostMessageResponse postResponse = slack.methods().chatPostMessage(ChatPostMessageRequest.builder()
                .token(userToken)
                .channel(randomChannelId)
                .text("test")
                .blocksAsString(blocksAsString)
                .build());
        assertThat(postResponse.getError(), is(nullValue()));

        ChatUpdateResponse updateMessage = slack.methods().chatUpdate(ChatUpdateRequest.builder()
                .channel(randomChannelId)
                .token(userToken)
                .ts(postResponse.getTs())
                .text("modified")
                .blocksAsString(blocksAsString)
                .build());
        assertThat(updateMessage.getError(), is(nullValue()));

        // To show the text instead of blocks
        ChatUpdateResponse updateMessage2 = slack.methods().chatUpdate(ChatUpdateRequest.builder()
                .channel(randomChannelId)
                .token(userToken)
                .ts(postResponse.getTs())
                .blocksAsString("[]")
                .text("modified2")
                .build());
        assertThat(updateMessage2.getError(), is(nullValue()));
    }

    @Test
    public void postEphemeral_thread() throws Exception {
        loadRandomChannelId();
        String userId = findUser();
        ChatPostMessageResponse first = slack.methods(botToken).chatPostMessage(r -> r
                .channel(randomChannelId)
                .text("first message"));
        assertThat(first.getError(), is(nullValue()));

        ChatPostMessageResponse second = slack.methods(botToken).chatPostMessage(r -> r
                .channel(randomChannelId)
                .threadTs(first.getTs())
                .text("reply to create an active thread"));
        assertThat(second.getError(), is(nullValue()));

        ChatPostEphemeralResponse third = slack.methods(botToken).chatPostEphemeral(r -> r
                .user(userId)
                .channel(randomChannelId)
                .text("ephemeral reply in thread")
                .threadTs(first.getTs()));
        assertThat(third.getError(), is(nullValue()));
    }

    @Test
    public void postEphemeral_authorship() throws Exception {
        loadRandomChannelId();

        String userId = findUser();
        ChatPostEphemeralResponse response = slack.methods(botToken).chatPostEphemeral(r -> r
                .channel(randomChannelId)
                .user(userId)
                .iconEmoji(":wave:")
                .username("given name")
                .text(":wave: Hi there!"));
        assertThat(response.getError(), is(nullValue()));
    }

    private String findUser() throws IOException, SlackApiException {

        String userId = null;

        ConversationsMembersResponse membersResponse = slack.methods(botToken)
                .conversationsMembers(r -> r.channel(randomChannelId).limit(100));
        assertThat(membersResponse.getError(), is(nullValue()));
        List<String> userIds = membersResponse.getMembers();
        for (String id : userIds) {
            User user = slack.methods(botToken).usersInfo(r -> r.user(id)).getUser();
            if (user.isBot() || user.isAppUser() || user.isDeleted() || user.isWorkflowBot() || user.isStranger()) {
                continue;
            }
            userId = id;
            break;
        }
        assertThat(userId, is(notNullValue()));
        return userId;
    }

    @Test
    public void scheduleMessages() throws IOException, SlackApiException {
        loadRandomChannelId();
        int postAt = (int) ((new Date().getTime() / 1000) + 180);

        ChatScheduledMessagesListResponse before = slack.methods(botToken).chatScheduledMessagesList(r -> r.limit(100));
        assertNull(before.getError());

        ChatScheduleMessageResponse message1 = slack.methods(botToken).chatScheduleMessage(r ->
                r.channel(randomChannelId).postAt(postAt).text("hello"));
        assertNull(message1.getError());

        List<LayoutBlock> blocks = Arrays.asList(
                DividerBlock.builder().build(),
                SectionBlock.builder().text(PlainTextObject.builder().text("foo").build()).build()
        );
        ChatScheduleMessageResponse message2 = slack.methods(botToken).chatScheduleMessage(r ->
                r.channel(randomChannelId).postAt(postAt).blocks(blocks));
        assertNull(message2.getError());

        ChatScheduledMessagesListResponse after = slack.methods(botToken).chatScheduledMessagesList(r -> r.limit(100));
        assertNull(after.getError());
        assertTrue(after.getScheduledMessages().size() - before.getScheduledMessages().size() == 2);
    }

}
