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

}
