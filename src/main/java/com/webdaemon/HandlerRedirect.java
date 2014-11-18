package com.webdaemon;

public class HandlerRedirect implements HandlerAPI {

	@Override
	public boolean handleAndStop(Request request, Response response) {
		
		if (!request.getPath().equals("/redirect")) return false;
		
		response.setHeader("Location", "http://localhost:5000/");
		response.setStatus(302);
		
		return true;
		
	}

}
