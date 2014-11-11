package com.hydra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpSocket {

	private int port;
	private Socket socket;
	private BufferedReader input;
	private BufferedWriter output;

	public HttpSocket(int port) {
		this.port = port;

		try {
			ServerSocket listener = new ServerSocket(this.port);
			socket = listener.accept();

			/*i
*/
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void close() throws IOException {
		socket.close();
	}

}
