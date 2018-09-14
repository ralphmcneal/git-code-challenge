package ralphmcneal.codechallenge.git;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static ralphmcneal.codechallenge.Application.MAX_DEPTH;
import static ralphmcneal.codechallenge.Application.MAX_FOLLOWERS;

public class GitServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private static final String FOLLOWERS_URL = "/users/user(.*)/followers\\?per_page=" + MAX_FOLLOWERS;

    private GitService underTest = new GitService("http://localhost:8089");
    private Gson gson = new Gson();

    @Test
    public void getFollowers_expectSuccess() throws Exception {
        stubFor(get(urlMatching(FOLLOWERS_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(buildFollowers(MAX_FOLLOWERS)))));

        List<User> users = underTest.getFollowers("user0", MAX_DEPTH);

        assertEquals(MAX_FOLLOWERS, users.size());
        assertFollowersMaxDepth(users, MAX_DEPTH);
    }

    @Test(expected = GitServiceException.class)
    public void getFollowers_whenServiceFails_expectException() throws Exception {
        stubFor(get(urlMatching(FOLLOWERS_URL))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBody("error")));

        underTest.getFollowers("user0", MAX_DEPTH);
    }

    private void assertFollowersMaxDepth(List<User> users, int depth) {
        if (depth <= 1) {
            users.forEach(user -> assertEquals("unexpected followers at depth " + depth, 0, user.getFollowers().size()));
            return;
        }

        int nextLevel = depth - 1;
        users.forEach(user -> {
            assertEquals("unexpected followers at depth " + depth, MAX_FOLLOWERS, user.getFollowers().size());
            assertFollowersMaxDepth(user.getFollowers(), nextLevel);
        });
    }

    private List<User> buildFollowers(int count) {
        List<User> users = new ArrayList<>();
        IntStream.range(0, count).forEach(i -> {
                    User user = new User("user" + i, String.valueOf(i), "http://localhost:8089/users/user" + i + "/followers");
                    users.add(user);
                }
        );
        return users;
    }
}