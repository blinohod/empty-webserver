package com.webdaemon;

import java.util.Hashtable;

public class Response {

	private int status = 200;
		
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}

	public String getStatusLine() {
		return "HTTP/1.0 " + this.getStatus() + " " + getStatusMessage();
	}

	public void setHeader(String string, String string2) {
		// TODO Auto-generated method stub
	}

	public Object getHeaderString() {
		return "Content-type: text/plain";
	}

	public void setBody(String testBody) {
		// TODO Auto-generated method stub
	}

	public char[] getBodyChars() {
		return new String("TEST BODY").toCharArray();
	}
	
	// ================= Private Methods After This Line !!!
	private String getStatusMessage() {
		Hashtable<Integer,String> statusMessages = new Hashtable<Integer, String>();
		statusMessages.put(200, "OK");
		statusMessages.put(401, "Authentication Required");
		statusMessages.put(404, "Not Found");
		
		if(statusMessages.containsKey(getStatus()))
			return statusMessages.get(getStatus());
		else
			return "Unknown code";
	}
}
