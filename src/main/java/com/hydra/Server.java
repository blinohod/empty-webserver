package com.hydra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static int LISTEN_PORT = 5000;

	private ServerSocket listener;
	private Socket socket;

	public Server() {

	}

	public void run() {

	}

	public static void main(String[] args) throws IOException, Exception {

		/*
		 * String publicDir = System.getenv("PUBLIC_DIR"); if (publicDir ==
		 * null) { System.out.println("Please define PUBLIC_DIR env variable");
		 * System.exit(1); }
		 */

		// API to request processing
		// HandlerAPI handler = new Handler(publicDir);

		ServerSocket listener = new ServerSocket(LISTEN_PORT);

		Socket connection = listener.accept();
		if (connection.isConnected()) {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(
					connection.getOutputStream()));

			Thread.sleep(1000);
			output.write("HTTP/1.0 404 Not Found\r\n\r\nNothing here");
			output.flush();
			output.close();
		}

		// System.out.println("Accepted");
		// socket.close();
		// listener.close();

	}

}
