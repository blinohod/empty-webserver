package com.webdaemon;

import java.io.IOException;

public interface HTTPSessionAPI {

	String[] readRequestHeaderLines() throws IOException;
	String readRequestBody(int bodySize) throws IOException;
	void writeResponse(byte[] response) throws IOException;
	void close() throws IOException;
	
}
