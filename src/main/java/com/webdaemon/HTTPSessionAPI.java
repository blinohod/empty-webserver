package com.webdaemon;

import java.io.IOException;

public interface HTTPSessionAPI {

	String readRequestLine() throws IOException;
	String readRequestHeaders() throws IOException;
	byte[] readRequestBody() throws IOException;
	
	void writeResponseStatus(String status) throws IOException;
	void writeResponseHeader(String headerString) throws IOException;
	void writeResponseBody(char[] body) throws IOException;
	
	void close() throws IOException;
	
}
