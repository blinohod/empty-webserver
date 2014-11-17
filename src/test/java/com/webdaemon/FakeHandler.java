package com.webdaemon;

public class FakeHandler implements HandlerAPI {

	@Override
	public String processRequest(Request request) {

		if (request.getMethod().equals("GET") && request.getPath().equals("/logs"))
			return "HTTP/1.0 401 Authentication Required";
			
		if (request.getMethod().equals("GET") && request.getPath().equals("/file.exists"))
			return "HTTP/1.0 200 OK";
		
		return "HTTP/1.0 404 Not Found";

	}

}
