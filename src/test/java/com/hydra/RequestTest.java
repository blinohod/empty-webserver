package com.hydra;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RequestTest {

	private Request request;

	@Before
	public void setup() {
		request = new Request();
	}

	@Test
	public void shouldParseGetMethod() {
		request.parse("GET /some/path?param=value HTTP/1.0\r\n"
				+ "Host: test.com\r\n"
				+ "Accept: text/plain\r\n"
				+ "\r\n");
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void shouldParsePostMethod() {
		request.parse("POST /some/path?param=value HTTP/1.0\r\n"
				+ "Content-type: text/plain\r\n"
				+ "\r\n"
				+ "POSTDATA");
		assertEquals("POST", request.getMethod());
	}

}
