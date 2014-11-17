package com.webdaemon;

public interface HTTPSessionAPI {

	String readRequestLine();
	String readRequestHeaders();
	byte[] readRequestBody();
	
	void writeResponseStatus(String status);
	
}
