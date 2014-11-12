package com.hydra;

public class Request {

	private String rawRequest; // original request as a string
	
	private String requestLine;
	private String rawHeader;
	private String rawBody;
	
	private String method;	
	private String path;
	private String queryString;
	
	public Request(String rawRequest) {
		this.rawRequest = rawRequest;
		this.splitRawRequest();
		this.splitRequestLine();
	}
	
	public String getRequestLine() {
		return requestLine;
	}
	
	public String getRawHeader() {
		return rawHeader;
	}
	
	public String getRawBody() {
		return rawBody;
	}
	
	public Object getMethod() {
		return method;
	}

	public Object getPath() {
		return path;
	}

	public Object getQueryString() {
		return queryString;
	}
	
	private void splitRawRequest() {
		String[] parts = rawRequest.split("\r\n\r\n", 2);
		rawBody = parts[1];
		String buffer = parts[0];
		
		parts = buffer.split("\r\n", 2);
		requestLine = parts[0];
		rawHeader = parts[1];

	}
	
	private void splitRequestLine() {
		String[]parts = requestLine.split(" ", 3);
		method = parts[0];
		String buffer = parts[1];
		
		parts = buffer.split("\\?", 2);
		path = parts[0];
		if (parts.length == 2)
			buffer = parts[1];
		
		parts = buffer.split(" ");
		queryString = parts[0];
	}


}
