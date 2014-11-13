package com.hydra;

import java.nio.ByteBuffer;
import java.util.*;

public class Response {

	private int status;
	private String statusMessage;
	private Map<String,String> headers; 
	private String body;
	
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
		
	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}
	
	public String getStatusLine() {
		return "HTTP/1.0 " + getStatus() + " " + getStatusMessage();
	}
	
	public ByteBuffer toByteBuffer() {
		String res = getStatusLine() + "\r\n\r\n" + getBody();
		ByteBuffer buf = ByteBuffer.wrap(res.getBytes());
		return buf;
	}
}
