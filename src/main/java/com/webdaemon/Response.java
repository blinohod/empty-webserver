package com.webdaemon;

import java.util.Hashtable;

public class Response {

	private int status = 200;
	private Hashtable<String, String> headers = new Hashtable<String, String>();

    private StringBuffer body = new StringBuffer();
		
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}

	public String getStatusLine() {
		return "HTTP/1.0 " + this.getStatus() + " " + getStatusMessage();
	}

	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public String getHeaderString() {
		String headerString = "";
		for ( String name : headers.keySet()) {
			headerString += name + ": " + headers.get(name) + "\r\n";
		}			
		return headerString;
	}

	public void setBody(String testBody) {
		this.body.append(testBody);
	}

	public char[] getBodyChars() {
        if (this.body.length() > 0)
            return this.body.toString().toCharArray();
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
