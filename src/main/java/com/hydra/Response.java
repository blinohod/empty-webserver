package com.hydra;

import java.util.Arrays;

public class Response {

    private String path;

    public String[] passwordProtectedPaths = new String[]{"/logs"};

    public String[] accessiblePaths = new String[]{"", "/", "/logs", "/method_options", "/form"};

    public Response() {
        this("");
    }

    public Response(String path) {
        this.path = path;
    }

    public String getResponseRequest() {
        String[] response;
        if (!isPathAccessible()) {
            response = getNotFoundStatus();
        } else if (!pathAuthorization()) {
            response = getNotAuthorizedStatus();
        } else {
            response = getSuccessStatus();
        }

        return formatStatus(response);
    }

    public String[] getSuccessStatus() {
        if (path.contains("method_options"))
            return new String[]{"HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", "Method Options Page"};
        else if (path.contains("form"))
            return new String[]{"HTTP/1.1 200 OK\r\nAccess-Control-Allow-Methods: POST, GET, OPTIONS", "Form page that accepts data from a POST"};
        else
            return new String[]{"HTTP/1.1 200 OK", "Hello World"};
    }

    public String[] getNotFoundStatus() {
        return new String[]{"HTTP/1.1 404 Not Found", "Ooops there is nothing here :("};
    }

    public String[] getNotAuthorizedStatus() {
        return new String[]{"HTTP/1.1 401 Unauthorized", "Authentication required", "Nothing here"};
    }

    public String formatStatus(String[] status) {
        return String.join("\r\n\r\n", status) + "\r\n";
    }

    public boolean pathAuthorization() {
        return !Arrays.asList(passwordProtectedPaths).contains(path);
    }

    public boolean isPathAccessible() {
        return Arrays.asList(accessiblePaths).contains(path);
    }
}
