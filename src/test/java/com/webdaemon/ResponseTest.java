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
	public void canGenerateResponseHeader() {
		response.setHeader("Content-type", "text/plain");
		assertEquals("Content-type: text/plain", response.getHeaderString());
	}
	
	@Test
	public void canGenerateBodyFromString() {
		String testBody = "TEST BODY"; 
		response.setBody(testBody);
		assertArrayEquals(testBody.toCharArray(), response.getBodyChars());
	}
}
