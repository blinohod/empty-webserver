package com.webdaemon;

public class Handler implements HandlerAPI {

	@Override
	public Response getResponse(Request request) {

		Response response = new Response();
		response.setStatus(404);
		
		if (request.getMethod().equals("GET") && request.getPath().equals("/logs"))
			response.setStatus(401);

		if (request.getMethod().equals("GET") && request.getPath().equals("/filehere"))
			response.setStatus(200);

		return response;
	}

}
