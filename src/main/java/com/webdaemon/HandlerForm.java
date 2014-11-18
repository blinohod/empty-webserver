package com.webdaemon;

public class HandlerForm implements HandlerAPI {

	@Override
	public boolean handleAndStop(Request request, Response response) {

		if (!request.getPath().equals("/form")) return false;
		
		response.setStatus(200);
		response.setBody("Form processed");
		return true;
	}

}
