package com.webdaemon;

import java.util.Base64;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void shouldReturnBasicAuthRealmOnLogs() {
        request.setMethod("GET");
        request.setPath("/logs");
        response = handlerStack.getResponse(request);
        assertEquals(401, response.getStatus());
        assertEquals("Basic realm=\"empty_server\"", response.getHeader("WWW-Authenticate"));
    }

    @Test
    public void shouldReturn200OnLogsWithValidCredentials() {
        request.setMethod("GET");
        request.setPath("/logs");
        String credentials = Base64.getEncoder().encodeToString("admin:hunter2".getBytes());
        request.setHeader("Authorization", "Basic " + credentials);
        response = handlerStack.getResponse(request);
        assertEquals(200, response.getStatus());
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
		assertTrue(new String(response.getBytes()).contains("Location: http://localhost:5000/"));
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
