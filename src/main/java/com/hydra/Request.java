package com.hydra;

import java.io.BufferedReader;

public class Request {

	private String method;	
	private String path;
	private String queryString;
	
	public void parse(String rawRequest) {

		String buffer = rawRequest;
		String[] parts;

		// Parse 1st line (method, url, query string, http version)
		parts = buffer.split(" ", 2);
		this.method = parts[0];
		buffer = parts[1];
		
		parts = buffer.split("\\?", 2);
		this.path = parts[0];
		buffer = parts[1];
		
		parts = buffer.split(" ");
		queryString = parts[0];
		
		/*
		parts = rawRequest.split("\r\n\r\n", 2);
		String requestHeader = parts[0];
		String requestBody = parts[1];
		
		parts = requestHeader.split("\r\n", 2);
		String requestLine = parts[0];
		String requestHeaders = parts[1];
		
		parts = requestLine.split("\\s+");
		this.method = parts[0];
		*/
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
	
}
