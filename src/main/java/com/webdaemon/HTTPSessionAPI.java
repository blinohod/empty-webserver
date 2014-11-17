package com.webdaemon;

import java.io.IOException;

public interface HTTPSessionAPI {

	String readRequestLine() throws IOException;
	String readRequestHeaders();
	byte[] readRequestBody();
	
	void writeResponseStatus(String status) throws IOException;
	
}
