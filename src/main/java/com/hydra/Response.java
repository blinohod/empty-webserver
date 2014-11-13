package com.hydra;

import java.nio.ByteBuffer;
import java.util.*;

public class Response {

	private int status;
	private String statusMessage;
	private Map<String,String> headers; 
	private ByteBuffer body;
	
	public Response() {
		// Set defaults
		this.status = 200;
		this.statusMessage = "OK";
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public ByteBuffer getBody() {
		return body;
	}
	public void setBody(ByteBuffer body) {
		this.body = body;
	}
	
	public void setBody(String body) {
		this.body.put(body.getBytes());
	}
	
	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}
	
}
