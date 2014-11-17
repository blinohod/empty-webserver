package com.webdaemon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RequestTest {

	private Request request;
	
	@Before
	public void setupRequest() {
		request = new Request();  
	}
	
	@Test
	public void canParseConnectMethodLine() throws Exception {
		request.parseRequestLine("CONNECT /path/here HTTP/1.1");
		assertEquals("CONNECT", request.getMethod());
	}

	@Test
	public void canParseDeleteMethodLine() throws Exception {
		request.parseRequestLine("DELETE /path/here HTTP/1.1");
		assertEquals("DELETE", request.getMethod());
	}

	@Test
	public void canParseGetMethodLine() throws Exception {
		request.parseRequestLine("GET /path/here HTTP/1.1");
		assertEquals("GET", request.getMethod());
	}

	@Test
	public void canParseHeadMethodLine() throws Exception {
		request.parseRequestLine("HEAD /path/here HTTP/1.1");
		assertEquals("HEAD", request.getMethod());
	}
	
	@Test
	public void canParsePostMethodLine() throws Exception {
		request.parseRequestLine("POST /path/here HTTP/1.1");
		assertEquals("POST", request.getMethod());
	}

	@Test(expected = Exception.class)
	public void throwsOnUnknownMethod() throws Exception {
		request.parseRequestLine("STRANGE /path/here HTTP/1.1");
	}
	
	@Test
	public void canParseHttpVersion() throws Exception {
		request.parseRequestLine("POST /path/here HTTP/0.9");
		assertEquals("0.9", request.getHttpVersion());
		request.parseRequestLine("POST /new/path/here HTTP/1.1");
		assertEquals("1.1", request.getHttpVersion());
	}
	
	@Test
	public void canParsePath() throws Exception {
		request.parseRequestLine("GET /some/path HTTP/1.0");
		assertEquals("/some/path", request.getPath());
	}

	@Test
	public void canParsePathWithQueryString() throws Exception {
		request.parseRequestLine("GET /some/path?param=val HTTP/1.0");
		assertEquals("/some/path", request.getPath());
	}

	@Test
	public void canParseQueryString() throws Exception {
		request.parseRequestLine("GET /some/path?foo=bar&foo+1=bar+2 HTTP/1.0");
		assertEquals("bar", request.getQueryParam("foo"));		
		assertEquals("bar 2", request.getQueryParam("foo 1"));
	}

	@Test
	public void canParseHeaders() throws Exception {
		request.parseRequestLine("POST /some/form HTTP/1.1");
		request.parseRequestHeader("Host: www.webdaemon.com\r\n"
				+ "Accept: text/html\r\n"
				+ "Content-type: text/plain\r\n"
				+ "Content-length: 1234");		
		assertEquals("www.webdaemon.com", request.getHeader("Host"));
		assertEquals("text/plain", request.getHeader("Content-type"));
	}
	
	@Test
	public void canFetchNotEncodedBody() throws Exception {
		request.parseRequestLine("POST /poster HTTP/1.0");
		request.parseRequestHeader("Host: ooops.com\r\n"
				+ "Content-type: text/plain\r\n"
				+ "Content-length: 4");
		
		byte[] rawBody = new String("TEST").getBytes();
		request.parseBody(rawBody);
		
		assertArrayEquals(rawBody, request.getBody());
	}

}
