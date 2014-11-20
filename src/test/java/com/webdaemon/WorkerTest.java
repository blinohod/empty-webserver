package com.webdaemon;

import static org.junit.Assert.*;

import org.junit.Test;

public class WorkerTest {

	@Test
	public void canProcess200() {
		FakeHTTPSession session = new FakeHTTPSession();
		HandlerStackAPI handler = new FakeHandlerStack();
		session.requestHeaderLines = new String[]{
				"GET /file.exists HTTP/1.0"
				};

		Worker worker = new Worker(session, handler);
		worker.run();
		assertTrue(new String(session.response).contains("HTTP/1.0 200 OK"));
	}
	
}
