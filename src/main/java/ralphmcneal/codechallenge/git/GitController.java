package ralphmcneal.codechallenge.git;

import spark.Request;
import spark.Response;

import java.util.List;

import static ralphmcneal.codechallenge.Application.MAX_DEPTH;

public class GitController {
    private GitService gitService;

    public GitController(GitService gitService) {
        this.gitService = gitService;
    }

    public List<User> getFollowers(Request request, Response response) {
        response.type("application/json");
        return gitService.getFollowers(request.params("userId"), MAX_DEPTH);
    }
}
