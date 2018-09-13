package ralphmcneal.codechallenge;

import com.google.gson.Gson;
import ralphmcneal.codechallenge.git.GitController;
import ralphmcneal.codechallenge.git.GitService;
import spark.ResponseTransformer;

import static spark.Spark.*;

public class Application {
    public static final int MAX_DEPTH = 3;
    public static final int MAX_FOLLOWERS = 5;

    public static void main(String[] args) {
        port(8080);
        exception(RuntimeException.class, ExceptionHandler::handle);

        Gson gson = new Gson();
        ResponseTransformer responseTransformer = gson::toJson;
        GitService gitService = new GitService("https://api.github.com");
        GitController gitController = new GitController(gitService);

        get("/followers/:userId", gitController::getFollowers, responseTransformer);
    }
}