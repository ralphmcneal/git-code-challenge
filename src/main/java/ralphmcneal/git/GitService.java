package ralphmcneal.git;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static ralphmcneal.codechallenge.Application.MAX_FOLLOWERS;

public class GitService {
    private Gson gson = new Gson();
    private String apiBaseUrl;

    public GitService(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    public List<User> getFollowers(String userId, int maxDepth) {
        return buildFollowersList(apiBaseUrl + "/users/" + userId + "/followers", maxDepth);
    }

    private List<User> buildFollowersList(String url, int maxDepth) {
        if (maxDepth < 1) return Collections.emptyList();

        HttpResponse<String> response;
        try {
            response = Unirest.get(url)
                    .queryString("per_page", MAX_FOLLOWERS)
                    .asString();
        } catch (UnirestException e) {
            throw new GitServiceException(e.getMessage(), e);
        }

        if(response.getStatus() != 200) {
            throw new GitServiceException(response.getStatus());
        }

        int nextLevel = --maxDepth;
        List<User> users = asList(gson.fromJson(response.getBody(), User[].class));
        users.forEach(user -> user.setFollowers(buildFollowersList(user.getFollowersUrl(), nextLevel)));
        return users;
    }
}
