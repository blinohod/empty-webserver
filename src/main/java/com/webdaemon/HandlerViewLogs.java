package com.webdaemon;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class HandlerViewLogs implements HandlerAPI {

	@Override
	public boolean handleAndStop(Request request, Response response) {
		
		if(request.getPath().equals("/logs") && request.getMethod().equals("GET")) {
            String credentials = "Basic " + Base64.encode("admin:hunter2".getBytes());
//           YWRtaW46aHVudGVyMg==
//            System.out.println(credentials + "'\r\nActual'" + request.getHeader("Authorization"));
//            System.out.println("Body " + request.getBody() + "\r\n");
            if(request.getHeader("Authorization") != null && request.getHeader("Authorization").contains(credentials)) {
                response.setStatus(200);
                response.setBody("Authorized!!!");
            } else {
                response.setStatus(401);
                response.setBody("Authentication required");
                response.setHeader("WWW-Authenticate", "Basic realm=\"empty_server\"");
            }
			response.setStatus(200);
			response.setBody("Show logs here");
			return true;
		} else 
			return false;
	}

}
