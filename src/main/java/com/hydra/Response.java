package com.hydra;

/**
 * Created by evelina.lurye on 11/13/14.
 */
public class Response {

    private String response;
    private Request request;

    public Response(Request request) {
        this.request = request;
    }

    public String getSuccessStatus() {
        return "HTTP/1.1 200 OK";
    }

    public String getFailedStatus() {
        return "HTTP/1.1 404 Not Found";
    }
}
