package com.hydra;


public class Router {
    public String route(String req) {

        Request request = new Request(req);

        Response response = new Response(request.getPath());

        return response.getResponseRequest() + request.getRequestLine();
    }

}
