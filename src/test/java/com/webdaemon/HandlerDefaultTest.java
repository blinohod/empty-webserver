package com.webdaemon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HandlerDefaultTest {

	private Request request;
	private Response response;
	private HandlerAPI handler;
	
	@Before
	public void setup() {
		request = new Request();
		response = new Response();
		handler = new HandlerDefault();
	}
	
	@Test
	public void shouldReturnTrue() {
		assertTrue(handler.handleAndStop(request, response));
	}

}
