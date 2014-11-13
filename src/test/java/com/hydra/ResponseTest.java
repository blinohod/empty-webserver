package com.hydra;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;

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
	
	@Test
	public void responseAsBufferWorks() {
		res.setStatus(404);
		res.setStatusMessage("Not found");
		res.setBody("File is not found");
		
		String expected = "HTTP/1.0 404 Not found\r\n\r\nFile is not found";
		String resString = new String(res.toByteBuffer().array());
		
		assertEquals(expected, resString);
	}

}
