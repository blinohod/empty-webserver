package com.webdaemon;

public class Handler implements HandlerAPI {

	@Override
	public Response getResponse(Request request) {
		return new Response();
	}

}
