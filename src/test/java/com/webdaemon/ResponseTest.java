package com.webdaemon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResponseTest {

	Response response;

	@Before
	public void setup() {
		response = new Response();
	}

	@Test
	public void defaultStatusIs200() {
		assertEquals(200, response.getStatus());
	}

	@Test
	public void canGenerateResponseStatusLine() {
		assertEquals("HTTP/1.0 200 OK", response.getStatusLine());
	}

	@Test
	public void canSet401StatusLine() {
		response.setStatus(401);
		assertEquals("HTTP/1.0 401 Authentication Required", response.getStatusLine());
	}
	
	@Test
	public void canSet404StatusLine() {
		response.setStatus(404);
		assertEquals("HTTP/1.0 404 Not Found", response.getStatusLine());
	}
	
	@Test
	public void canGenerateResponseHeader() {
		response.setHeader("Content-type", "text/plain");
		assertEquals("Content-type: text/plain\r\n", response.getHeaderString());
	}

	@Test
	public void canGenerateResponseMultipleHeaders() {
		response.setHeader("Content-type", "text/plain");
		response.setHeader("Accept", "image/gif");		
		assertTrue(response.getHeaderString().contains("Content-type: text/plain"));
		assertTrue(response.getHeaderString().contains("Accept: image/gif"));
	}

	@Test
	public void canGenerateBodyFromString() {
		String testBody = "TEST BODY";
		response.setBody(testBody);
		assertArrayEquals(testBody.toCharArray(), response.getBodyChars());
	}
	
}
