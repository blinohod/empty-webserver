package com.webdaemon;

public class FakeHandlerStack implements HandlerStackAPI {

	@Override
	public Response getResponse(Request request) {

		Response response = new Response();
		response.setStatus(404);
		
		if (request.getMethod().equals("GET") && request.getPath().equals("/logs"))
			response.setStatus(401);
			
		if (request.getMethod().equals("GET") && request.getPath().equals("/file.exists"))
			response.setStatus(200);
		
		return response;

	}

}
