package com.hydra;


public class Router {
    public String route(String request) {
        if(isPathAllowed(request)){
            return "HTTP/1.0 404 Not Found\r\n\r\nNoothing here\r\n" + request;
        } else {
            return "HTTP/1.0 401 Unauthorized\r\n\r\nAuthentication required\r\n";
        }
    }

    public boolean isPathAllowed(String request) {
        return !request.contains("logs");
    }
}
