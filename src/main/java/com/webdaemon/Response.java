package com.webdaemon;

import java.util.Hashtable;

public class Response {

	private int status = 200;
	private Hashtable<String, String> headers = new Hashtable<String, String>();
	private byte[] body;
		
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}

	public String getStatusLine() {
		return "HTTP/1.0 " + this.getStatus() + " " + getStatusMessage();
	}

	public byte[] getBytes() {
		String responseHeader = getStatusLine();
		for(String name : headers.keySet())
			responseHeader += "\r\n" +  name + ": " + headers.get(name);
		responseHeader += "\r\n\r\n";
		if(body == null)
			body = new byte[0];
		byte[] ret = new byte[responseHeader.getBytes().length + body.length];
		System.arraycopy(responseHeader.getBytes(), 0, ret, 0, responseHeader.getBytes().length);
		System.arraycopy(body, 0, ret, responseHeader.getBytes().length, body.length);
		return ret;
	}
	
	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public void setBody(String bodyString) {
		setBody(bodyString.getBytes());
	}

	public void setBody(byte[] bodyBytes) {
		setHeader("Content-length", "" + bodyBytes.length);
        this.body = bodyBytes;
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

	public byte[] getBodyBytes() {
		return this.body;
	}
	
	// ================= Private Methods After This Line !!!
	private String getStatusMessage() {
		Hashtable<Integer,String> statusMessages = new Hashtable<Integer, String>();
		statusMessages.put(200, "OK");
		statusMessages.put(401, "Authentication Required");
		statusMessages.put(404, "Not Found");
		statusMessages.put(405, "Method Not Allowed");
		
		if(statusMessages.containsKey(getStatus()))
			return statusMessages.get(getStatus());
		else
			return "Unknown code";
	}
}
