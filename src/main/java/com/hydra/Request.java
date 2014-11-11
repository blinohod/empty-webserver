package com.hydra;

public class Request {

	private String method;
	


	public Request(String inputString) {
		String[] parts = new String[1024];
		
		parts = inputString.split("\r\n\r\n", 2);
		String requestHeader = parts[0];
		String requestBody = parts[1];
		
		parts = requestHeader.split("\r\n", 2);
		String requestLine = parts[0];
		String requestHeaders = parts[1];
		
		parts = requestLine.split("\\s+");
		this.method = parts[0];
	}

	public Object getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
}
