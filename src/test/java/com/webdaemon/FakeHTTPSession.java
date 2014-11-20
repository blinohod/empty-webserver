package com.webdaemon;

import java.io.IOException;

public class FakeHTTPSession implements HTTPSessionAPI {

	public String[] requestHeaderLines;
	public String requestBody;

	public byte[] response;

	@Override
	public String[] readRequestHeaderLines() {
		return requestHeaderLines;
	}

	@Override
	public String readRequestBody(int bodySize) {
		return requestBody;
	}

	@Override
	public void close() {
	}

	@Override
	public void writeResponse(byte[] body) throws IOException {
		response = body;		
	}

}
