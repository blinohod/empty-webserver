package com.hydra;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RequestTest {

	private Request request;

	@Before
	public void setup() {
		request = new Request();
	}

	@Test
	public void shouldFetchMethod() {
		request.parse("GET /some/path?param=value HTTP/1.0\r\n"
				+ "Host: test.com\r\n" + "\r\n");
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void shouldFetchPath() {
		request.parse("GET /some/path?param=value HTTP/1.0\r\n"
				+ "Host: test.com\r\n" + "\r\n");
		assertEquals("/some/path", request.getPath());
	}

	@Test
	public void shouldFetchQueryString() {
		request.parse("GET /some/path?param=value&another=some+thing HTTP/1.0\r\n"
				+ "Host: test.com\r\n" + "\r\n");
		assertEquals("param=value&another=some+thing", request.getQueryString());
	}

	@Test
	public void shouldParsePostMethod() {
		request.parse("POST /some/path?param=value HTTP/1.0\r\n"
				+ "Content-type: text/plain\r\n" + "\r\n" + "POSTDATA");
		assertEquals("POST", request.getMethod());
	}

}
