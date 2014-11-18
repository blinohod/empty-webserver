package com.webdaemon;

import java.util.Base64;

public class HandlerViewLogs implements HandlerAPI {

    private String credentials = "Basic " + Base64.getEncoder().encodeToString("admin:hunter2".getBytes());

	@Override
	public boolean handleAndStop(Request request, Response response) {
		
		if(request.getPath().equals("/logs") && request.getMethod().equals("GET")) {

            if(request.getHeader("Authorization") != null && request.getHeader("Authorization").contains(credentials)) {
                setAuthorized(response);
            } else {
                setUnAuthorized(response);
            }
			return true;
		} else 
			return false;
	}

    public void setAuthorized(Response response) {
        response.setStatus(200);
        response.setBody("Authorized!!!");
    }

    public void setUnAuthorized(Response response) {
        response.setStatus(401);
        response.setBody("Authentication required");
        response.setHeader("WWW-Authenticate", "Basic realm=\"empty_server\"");
    }

}
