package com.hydra;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResponseTest {

	private Response res;

	@Before
	public void setup() {
		res = new Response();
	}

	@Test
	public void defaultStatusIs200() {
		assertEquals(200, res.getStatus());
	}

	@Test
	public void defaultStatusMessageIsOK() {
		assertEquals("OK", res.getStatusMessage());
	}

	@Test
	public void defaultStatusLineIs200OK() {
		assertEquals("HTTP/1.0 200 OK", res.getStatusLine());
	}

}
