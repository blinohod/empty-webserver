package com.webdaemon;

public class HandlerViewLogs implements HandlerAPI {

	@Override
	public boolean handleAndStop(Request request, Response response) {
		
		if(request.getPath().equals("/logs")) {
			response.setStatus(200);
			response.setBody("Show logs here");
			return true;
		} else 
			return false;
	}

}
