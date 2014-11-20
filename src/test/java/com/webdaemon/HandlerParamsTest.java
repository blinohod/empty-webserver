package com.webdaemon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HandlerParamsTest {

	private Request request;
	private Response response;
	private HandlerAPI handler;

	@Before
	public void setup() {
		request = new Request();
		response = new Response();
		handler = new HandlerParams();
	}

	@Test
	public void shouldReturnQueryParamInBody() throws Exception {
		String[] lines = new String[] {
				"GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1"
				};
		request.parseHeader(lines);

		handler.handleAndStop(request, response);
		assertEquals(200, response.getStatus());

		String body = new String(response.getBytes());
		assertTrue(body.contains("variable_1"));

	}

}
