package ralphmcneal.codechallenge;

import spark.Request;
import spark.Response;

public class ExceptionHandler {
    public static void handle(Exception e, Request request, Response response) {
        e.printStackTrace();
        response.body("Sorry, this was not supposed to happen :(");
    }
}
