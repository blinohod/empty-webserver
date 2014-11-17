package com.webdaemon;

public class FakeHTTPSession implements HTTPSessionAPI {

	private String requestLine;
	private String requestHeaders;
	private byte[] requestBody;
	
	private String responseStatusLine;
	private String responseHeaders;
	private byte[] responseBody;
	
	@Override
	public String readRequestLine() {
		return requestLine;
	}

	@Override
	public String readRequestHeaders() {
		return requestHeaders;
	}

	@Override
	public byte[] readRequestBody() {
		return requestBody;
	}

	@Override
	public void writeResponseStatus(String statusLine) {
		this.responseStatusLine = statusLine;
	}

	// Support methods for handling fake request/response data
	public String getResponseStatusLine() {
		return responseStatusLine;
	}

	public String getResponseHeaders() {
		return responseHeaders;
	}

	public byte[] getResponseBody() {
		return responseBody;
	}

	public void setRequestLine(String requestLine) {
		this.requestLine = requestLine;
	}

	public void setRequestHeaders(String requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public void setRequestBody(byte[] requestBody) {
		this.requestBody = requestBody;
	}
	
}
