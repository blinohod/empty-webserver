package com.hydra;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Ignore;
import org.junit.Test;

public class HttpSocketTest {
	
	@Test
	public void shouldListenToSocket() throws Exception {
		ServerSocket listener = new ServerSocket(33000);
		Socket client = new Socket(InetAddress.getLocalHost(),33000);
		Socket socket = listener.accept();

		HttpSocket http = new HttpSocket(socket);
		BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		outStream.write("Test Data");
		
		assertEquals("Test Data", http.readInput());
		
		socket.close();
		listener.close();
	}

}
