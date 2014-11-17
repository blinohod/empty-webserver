package com.webdaemon;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

public class HTTPSessionTest {

	
	@Test
	public void canReadRequestLineFromSocket() throws Exception, IOException {
		
		ServerSocket listener = new ServerSocket(54321);
		Socket client = new Socket("127.0.0.1", 54321);
		Socket server = listener.accept();
		
		HTTPSession session = new HTTPSession(server);
		
		BufferedWriter out = new BufferedWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
		out.write("GET /path HTTP/1.0\r\n\r\n");
		out.flush();
		assertEquals("GET /path HTTP/1.0", session.readRequestLine());

		out.close();
	}

}
