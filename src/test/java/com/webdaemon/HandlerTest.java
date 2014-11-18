package com.webdaemon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HandlerTest {

	private Request request;	
	private Response response;
	private Handler handler;
	
	@Before
	public void setup() {
		request = new Request();	
		handler = new Handler();		
	}
	
	@Test
	public void shouldReturn401OnLogs() {
		request.setMethod("GET");
		request.setPath("/logs");
		response = handler.getResponse(request);
		assertEquals(401, response.getStatus());
	}
	
	@Test
	public void shouldReturn200OnRoot() {
		request.setMethod("GET");
		request.setPath("/");
		response = handler.getResponse(request);
		assertEquals(200, response.getStatus());
	}

	@Test
	public void shouldReturn200OnOptions() {
		request.setMethod("OPTIONS");
		request.setPath("/method_options");
		response = handler.getResponse(request);
		assertEquals(200, response.getStatus());
	}


	@Test
	public void shouldReturn302Redirect() {
		request.setMethod("GET");
		request.setPath("/redirect");
		response = handler.getResponse(request);
		assertEquals(302, response.getStatus());
		assertTrue(response.getHeaderString().contains("Location: http://localhost:5000/"));
	}
	
	@Test
	public void shouldReturn200OnSimplePost() {
		request.setMethod("POST");
		request.setPath("/form");
		response = handler.getResponse(request);
		assertEquals(200, response.getStatus());
	}
	
	

}
