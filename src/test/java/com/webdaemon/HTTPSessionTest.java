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
		clientOut = new BufferedWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
		clientIn = new BufferedReader(new BufferedReader(new InputStreamReader(client.getInputStream())));
	}
	
	@Test
	public void canReadRequestLineFromSocket() throws Exception, IOException {
		
		clientOut.write("GET /path HTTP/1.0\r\n\r\n");
		clientOut.flush();
		assertEquals("GET /path HTTP/1.0", session.readRequestLine());
	}

	@Test
	public void respondWithStatusLine() throws IOException {
		clientOut.write("GET /unexistent HTTP/1.0\r\n\r\n");
		clientOut.flush();
		
		session.writeResponseStatus("HTTP/1.0 200 OK");

		String statusLine = clientIn.readLine();
		assertEquals("HTTP/1.0 200 OK", statusLine);
		
	}
	
	@After
	public void shutdown() throws IOException {
		client.close();		
	}
}
