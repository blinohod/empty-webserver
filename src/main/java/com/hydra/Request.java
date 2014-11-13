package com.hydra;

public class Request {

	private String rawRequest; // original request as a string
	
	private String requestLine;
	private String rawHeader;
	private String rawBody;
	
	private String method;	
	private String path;
	private String queryString;

    public Request() {
        this.rawRequest = "GET / HTTP/1.0\r\n"
                + "Host: localhost\r\n" + "\r\n";
        this.splitRawRequest();
        this.splitRequestLine();
    }
	
	public Request(String rawRequest) {
		this.rawRequest = rawRequest;
		this.splitRawRequest();
		this.splitRequestLine();
	}
	
	public String getRequestLine() {
		return requestLine;
	}
	
	public String getRawHeader() {
		return rawHeader;
	}
	
	public String getRawBody() {
		return rawBody;
	}
	
	public Object getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public String getQueryString() {
		return queryString;
	}
	
	private void splitRawRequest() {
		String[] parts = rawRequest.split("\r\n\r\n");
        if (parts.length > 1)
            rawBody = parts[1];
		String buffer = parts[0];
		
		String[] parts2 = buffer.split("\r\n", 2);
        requestLine = parts2[0];
        if (parts2.length > 1)
            rawHeader = parts2[1];

	}
	
	private void splitRequestLine() {
		String[] parts = requestLine.split(" ", 3);
		method = parts[0];
        if(parts.length > 1) {
            String buffer = parts[1];

            String[] bufferParts = buffer.split("\\?", 2);
            path = bufferParts[0];

            if (bufferParts.length == 2)
                buffer = parts[1];

            parts = buffer.split(" ");
            queryString = parts[0];
        }
	}


}
