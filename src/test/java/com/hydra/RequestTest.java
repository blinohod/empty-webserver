package com.hydra;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RequestTest {

	private Request request;

	@Before
	public void setup() {
	}

	@Test
	public void shouldParseGetMethod() {
		request = new Request("GET /some/path?param=value HTTP/1.0\r\n\r\n");
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void shouldParsePostMethod() {
		request = new Request(
				"POST /some/path?param=value HTTP/1.0\r\nContent-type: text/plain\r\n\r\nPOSTDATA");
		assertEquals("POST", request.getMethod());
	}

}
