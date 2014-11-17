package com.webdaemon;

public class Response {

	private int status;
	
	public int getStatus() {
		return 200;
	}

	public String getStatusLine() {
		return "HTTP/1.0 200 OK";
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
	
}
