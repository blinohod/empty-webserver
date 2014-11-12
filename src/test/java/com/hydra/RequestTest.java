package com.hydra;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RequestTest {
	
	@Test
	public void shouldParseRequestParts() {
		Request request = new Request("POST /some/path?param=value HTTP/1.0\r\n"
				+ "Content-type: text/plain\r\n"
				+ "Accept: */*\r\n"
				+ "\r\n" 
				+ "POSTDATA LINE 1\r\n"
				+ "POSTDATA LINE 2");
		assertEquals("POST /some/path?param=value HTTP/1.0", request.getRequestLine());
		assertEquals("Content-type: text/plain\r\nAccept: */*", request.getRawHeader());
		assertEquals("POSTDATA LINE 1\r\nPOSTDATA LINE 2", request.getRawBody());
	}
	
	@Test
	public void shouldFetchMethod() {
		Request request = new Request("GET /some/path?param=value HTTP/1.0\r\n"
				+ "Host: test.com\r\n" + "\r\n");
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void shouldFetchPath() {
		Request request = new Request("GET /some/path HTTP/1.0\r\n"
				+ "Host: test.com\r\n" + "\r\n");
		assertEquals("/some/path", request.getPath());
	}

	@Test
	public void shouldFetchQueryString() {
		Request request = new Request("GET /some/path?param=value&another=some+thing HTTP/1.0\r\n"
				+ "Host: test.com\r\n" + "\r\n");
		assertEquals("param=value&another=some+thing", request.getQueryString());
	}

	@Test
	public void shouldParsePostMethod() {
		Request request = new Request("POST /some/path?param=value HTTP/1.0\r\n"
				+ "Content-type: text/plain\r\n" + "\r\n" + "POSTDATA");
		assertEquals("POST", request.getMethod());
	}

}
