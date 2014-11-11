package com.hydra;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Ignore;
import org.junit.Test;

public class HttpSocketTest {
	
	@Test
	public void shouldListenToSocket() throws Exception {
		ServerSocket listener = new ServerSocket(5000);
		Socket client = new Socket(InetAddress.getLocalHost(),5000);
		Socket socket = listener.accept();

		assertTrue(client.isConnected());
		
//		socket.close();
	}

}
