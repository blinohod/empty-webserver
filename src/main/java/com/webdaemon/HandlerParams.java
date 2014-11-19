package com.webdaemon;

public class HandlerParams implements HandlerAPI {

    @Override
    public boolean handleAndStop(Request request, Response response) {

        if (!request.getPath().equals("/parameters")) return false;

        response.setStatus(200);

        String body = "";
        String variable_1 = request.getQueryParam("variable_1");
        String variable_2 = request.getQueryParam("variable_2");
        body = "variable_1 = " + variable_1;
        body += "variable_2 = " + variable_2;

        response.setBody(body);

        return true;
    }

}
