package com.webdaemon;

public class HandlerParameters implements HandlerAPI {

	@Override
	public boolean handleAndStop(Request request, Response response) {
		
		if (!request.getMethod().equals("GET") || !request.getPath().equals("/parameters"))
			return false;
		
		//
		return true;
	}

}
