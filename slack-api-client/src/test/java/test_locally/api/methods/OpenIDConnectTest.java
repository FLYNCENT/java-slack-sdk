package test_locally.api.methods;

import com.google.gson.Gson;
import com.slack.api.SlackConfig;
import com.slack.api.methods.response.openid.connect.OpenIDConnectTokenResponse;
import com.slack.api.util.json.GsonFactory;
import config.SlackTestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.MockSlackApiServer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class OpenIDConnectTest {

    MockSlackApiServer server = new MockSlackApiServer();
    SlackConfig config = new SlackConfig();

    @Before
    public void setup() throws Exception {
        server.start();
        config.setMethodsEndpointUrlPrefix(server.getMethodsEndpointPrefix());
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    static String token_response_json = "{\n" +
            "    \"ok\": true,\n" +
            "    \"access_token\": \"xoxp-1234\",\n" +
            "    \"token_type\": \"Bearer\",\n" +
            "    \"id_token\": \"eyJhbGcMjY5OTA2MzcWNrLmNvbVwvdGVhbV9p...\"\n" +
            "}";

    @Test
    public void token() {
        SlackTestConfig testConfig = SlackTestConfig.getInstance();
        Gson gson = GsonFactory.createSnakeCase(testConfig.getConfig());
        OpenIDConnectTokenResponse response = gson.fromJson(token_response_json, OpenIDConnectTokenResponse.class);
        assertThat(response.getError(), is(nullValue()));
        assertThat(response.getAccessToken(), is("xoxp-1234"));
        assertThat(response.getTokenType(), is("Bearer"));
        assertThat(response.getIdToken(), is("eyJhbGcMjY5OTA2MzcWNrLmNvbVwvdGVhbV9p..."));
    }

    // TODO: token rotation pattern

    // TODO: userInfo response

}
