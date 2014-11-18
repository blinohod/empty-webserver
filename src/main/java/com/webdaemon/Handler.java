package com.webdaemon;

public class Handler implements HandlerAPI {

	@Override
	public Response getResponse(Request request) {

		Response response = new Response();
		response.setStatus(404);
		
		if (request.getMethod().equals("GET") && request.getPath().equals("/logs"))
			response.setStatus(401);

		if (request.getMethod().equals("GET") && request.getPath().equals("/"))
			response.setStatus(200);
		
		if (request.getMethod().equals("OPTIONS") && request.getPath().equals("/method_options")) {
			response.setHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
			response.setStatus(200);
		}

		if (request.getMethod().equals("GET") && request.getPath().equals("/redirect")) {
			response.setHeader("Location", "http://localhost:5000/");
			response.setStatus(302);
		}

		if (request.getMethod().equals("POST") && request.getPath().equals("/form")) {
			response.setStatus(200);
		}

		if (request.getMethod().equals("PUT") && request.getPath().equals("/form")) {
			response.setStatus(200);
		}

		return response;
	}

}
