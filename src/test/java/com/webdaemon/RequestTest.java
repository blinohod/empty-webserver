package com.webdaemon;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class RequestTest {

	private Request request;

	@Before
	public void setupRequest() {
		request = new Request();
	}

	@Test
	public void canParseRequestMethod() throws Exception {
		String[] headerLines = new String[] { "CONNECT /path/here HTTP/1.1" };
		request.parseHeader(headerLines);
		assertEquals("CONNECT", request.getMethod());

		headerLines = new String[] { "DELETE /path/here HTTP/1.1" };
		request.parseHeader(headerLines);
		assertEquals("DELETE", request.getMethod());
	}

	@Test(expected = Exception.class)
	public void throwsOnUnknownMethod() throws Exception {
		String[] headerLines = new String[] { "WHATTHEMETHOD /path/here HTTP/1.1" };
		request.parseHeader(headerLines);
	}

	@Test
	public void canParseHttpVersion() throws Exception {
		String[] headerLines = new String[] { "POST /path/here HTTP/0.9" };
		request.parseHeader(headerLines);
		assertEquals("0.9", request.getHttpVersion());
	}

	@Test
	public void canParsePath() throws Exception {
		String[] headerLines = new String[] { "POST /path/here HTTP/0.9" };
		request.parseHeader(headerLines);
		assertEquals("/path/here", request.getPath());
	}

	@Test
	public void canParsePathWithQueryString() throws Exception {
		String[] headerLines = new String[] { "POST /path/here?param=val HTTP/0.9" };
		request.parseHeader(headerLines);
		assertEquals("/path/here", request.getPath());
	}

	@Test
	public void canParseQueryString() throws Exception {
		String[] headerLines = new String[] { "GET /some/path?foo=bar&foo+1=bar+2 HTTP/1.0" };
		request.parseHeader(headerLines);
		assertEquals("bar", request.getQueryParam("foo"));
		assertEquals("bar 2", request.getQueryParam("foo 1"));
	}

	@Test
	public void canParseHeaders() throws Exception {
		String[] headerLines = new String[] {
				"GET /some/path?foo=bar&foo+1=bar+2 HTTP/1.0",
				"Host: www.webdaemon.com",
				"Accept: text/html",
				"Content-type: text/plain", 
				"Content-length: 1234" };
		request.parseHeader(headerLines);
		assertEquals("www.webdaemon.com", request.getHeader("Host"));
		assertEquals("text/plain", request.getHeader("Content-type"));
	}


	@Test
	public void canFetchBodyAsByteArray() throws Exception {
		String[] headerLines = new String[] {
				"POST /some/path HTTP/1.0",
				"Content-type: text/plain", 
				"Content-length: 1234" };		
		request.parseHeader(headerLines);
		request.setBody("STRING");
		assertEquals("STRING", request.getBody()); }

}
