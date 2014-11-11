package com.hydra;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Ignore;
import org.junit.Test;

public class ServerTest {

	@Test
	public void fakeTest() {
		assertTrue(true);
		
	}
	
	@Ignore
	public void shouldListenToSocket() throws Exception {
		HttpSocket socket = new HttpSocket(5000);
		Socket client = new Socket(InetAddress.getLocalHost(),5000);
		assertTrue(client.isConnected());
		
		socket.close();
	}

}
