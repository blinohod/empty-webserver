package com.webdaemon;

public class HandlerOptions implements HandlerAPI {

	@Override
	public boolean handleAndStop(Request request, Response response) {
		
		if (!request.getPath().equals("/method_options") && !request.getMethod().equals("OPTIONS")) return false;
		response.setHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
		response.setStatus(200);
		
		return true;
	}

}
