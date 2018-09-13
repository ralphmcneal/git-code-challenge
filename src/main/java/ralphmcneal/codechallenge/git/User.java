package ralphmcneal.codechallenge.git;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    public User() {
    }

    public User(String login, String id, String followersUrl) {
        this.login = login;
        this.id = id;
        this.followersUrl = followersUrl;
    }

    private String login;
    private String id;
    @SerializedName("followers_url")
    private String followersUrl;
    private List<User> followers;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
