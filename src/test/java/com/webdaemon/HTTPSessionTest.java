package com.webdaemon;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HTTPSessionTest {

	private ServerSocket listener;
	private Socket client;
	private Socket server;
	private HTTPSession session;
	private BufferedWriter clientOut;
	private BufferedReader clientIn;

	@Before
	public void setup() throws Exception, IOException {
		listener = new ServerSocket(54321);
		client = new Socket("127.0.0.1", 54321);
		server = listener.accept();
		listener.close();

		session = new HTTPSession(server);
		clientOut = new BufferedWriter(new BufferedWriter(
				new OutputStreamWriter(client.getOutputStream())));
		clientIn = new BufferedReader(new BufferedReader(new InputStreamReader(
				client.getInputStream())));
	}

	@After
	public void shutdown() throws IOException {
		client.close();
		server.close();
	}

	@Test
	public void canConnect() {
		assertTrue(client.isConnected());
	}
	
	@Test
	public void canReadRequestHeaderWithRequestStringOnly() throws IOException {
		clientOut.write("REQUESTLINE\r\n\r\n");
		clientOut.flush();
		assertEquals("REQUESTLINE", session.readRequestHeaderLines()[0]);
	}
	
	@Test
	public void readHeaderMayBeCalledMoreThanOnce() throws IOException {
		clientOut.write("REQUESTLINE\r\n\r\n");
		clientOut.flush();
		assertEquals("REQUESTLINE", session.readRequestHeaderLines()[0]);
		assertEquals("REQUESTLINE", session.readRequestHeaderLines()[0]);
	}
	
	@Test
	public void canReadRequestHeaderWithMultipleLines() throws IOException {	
		clientOut.write("LINE 1\r\nLINE 2\r\nLINE 3\r\n\r\n");
		clientOut.flush();
		assertEquals("LINE 1", session.readRequestHeaderLines()[0]);
		assertEquals("LINE 2", session.readRequestHeaderLines()[1]);
		assertEquals("LINE 3", session.readRequestHeaderLines()[2]);
	}

	@Test
	public void readRequestHeaderWithoutBody() throws IOException {	
		clientOut.write("HEADER LINE 1\r\nHEADER LINE 2\r\n\r\nBODY LINE\r\n");
		clientOut.flush();
		assertEquals("HEADER LINE 1", session.readRequestHeaderLines()[0]);
		assertEquals("HEADER LINE 2", session.readRequestHeaderLines()[1]);
		assertEquals(2, session.readRequestHeaderLines().length);
	}

	@Test
	public void canReadStringBodyAfterHeader() throws IOException {
		clientOut.write("HEADER LINE\r\n\r\nBODY LINE\r\n");
		clientOut.flush();
		assertEquals("HEADER LINE", session.readRequestHeaderLines()[0]);
		assertEquals(1,session.readRequestHeaderLines().length);
		assertEquals("BODY", session.readRequestBody(4));
	}
	
	@Test
	public void canWriteBody() throws IOException {
		session.writeResponse("BODY\r\n".getBytes());
		assertEquals("BODY", clientIn.readLine());
	}

}
