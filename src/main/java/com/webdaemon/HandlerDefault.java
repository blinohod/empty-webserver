package com.webdaemon;

public class HandlerDefault implements HandlerAPI {

	@Override
	public boolean handleAndStop(Request request, Response response) {
		/*
		 * This is default handler that should be called only
		 * if no other handlers processed request.
		 */
		response.setStatus(404);
		response.setHeader("Content-type", "text/plain");
		response.setBody("Location is not found");
		return true;
	}

}
