package com.webdaemon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class HandlerStackTest {

	private Request request;	
	private Response response;
	private HandlerStack handlerStack;
	
	@Before
	public void setup() {
		request = new Request();	
		handlerStack = new HandlerStack();		
	}

	@Test
	public void shouldReturn404OnUnknownRequest() {
		request.setMethod("GET");
		request.setPath("/letthinkthisurlisnotexists");
		response = handlerStack.getResponse(request);
		assertEquals(404, response.getStatus());
	}
	
	@Ignore
	public void shouldReturn401OnLogs() {
		request.setMethod("GET");
		request.setPath("/logs");
		response = handlerStack.getResponse(request);
		assertEquals(401, response.getStatus());
	}
	
	@Ignore
	public void shouldReturn200OnRoot() {
		request.setMethod("GET");
		request.setPath("/");
		response = handlerStack.getResponse(request);
		assertEquals(200, response.getStatus());
	}

	@Test
	public void shouldReturn200OnOptions() {
		request.setMethod("OPTIONS");
		request.setPath("/method_options");
		response = handlerStack.getResponse(request);
		assertEquals(200, response.getStatus());
	}


	@Test
	public void shouldReturn302Redirect() {
		request.setMethod("GET");
		request.setPath("/redirect");
		response = handlerStack.getResponse(request);
		assertEquals(302, response.getStatus());
		assertTrue(response.getHeaderString().contains("Location: http://localhost:5000/"));
	}

	@Test
	public void shouldReturn200OnSimplePost() {
		request.setMethod("POST");
		request.setPath("/form");
		response = handlerStack.getResponse(request);
		assertEquals(200, response.getStatus());
	}

	@Test
	public void shouldReturn200OnSimplePut() {
		request.setMethod("PUT");
		request.setPath("/form");
		response = handlerStack.getResponse(request);
		assertEquals(200, response.getStatus());
	}

}
