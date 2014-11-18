package com.webdaemon;

public class HandlerStatic implements HandlerAPI {

	@Override
	public boolean handleAndStop(Request request, Response response) {
		
		if (request.getMethod().equals("GET") && request.getPath().equals("/")) {
			response.setStatus(200);
			return true;
		}
		
		return false;
		
	}

}
