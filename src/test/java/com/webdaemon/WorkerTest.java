package com.webdaemon;

import static org.junit.Assert.*;

import org.junit.Test;

public class WorkerTest {

	@Test
	public void canProcess200() {
		FakeHTTPSession session = new FakeHTTPSession();
		HandlerStackAPI handler = new FakeHandlerStack();
		session.setRequestLine("GET /file.exists HTTP/1.0");
		Worker worker = new Worker(session, handler);
		worker.run();
		assertEquals("HTTP/1.0 200 OK", session.getResponseStatusLine());
	}
	
	@Test
	public void canProcess404() {
		FakeHTTPSession session = new FakeHTTPSession();
		HandlerStackAPI handler = new FakeHandlerStack();
		session.setRequestLine("GET /notfoundrl HTTP/1.0");
		Worker worker = new Worker(session, handler);
		worker.run();	
		assertEquals("HTTP/1.0 404 Not Found", session.getResponseStatusLine());
	}

	@Test
	public void canProcess401() {
		FakeHTTPSession session = new FakeHTTPSession();
		HandlerStackAPI handler = new FakeHandlerStack();
		session.setRequestLine("GET /logs HTTP/1.0");
		Worker worker = new Worker(session, handler);
		worker.run();
		assertEquals("HTTP/1.0 401 Authentication Required", session.getResponseStatusLine());
	}
	
}
