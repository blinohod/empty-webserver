package com.hydra;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static int LISTEN_PORT = 5000;

	public static void main(String[] args) throws IOException, Exception {

		/*
		 * String publicDir = System.getenv("PUBLIC_DIR"); if (publicDir ==
		 * null) { System.out.println("Please define PUBLIC_DIR env variable");
		 * System.exit(1); }
		 */

		ServerSocket listener = new ServerSocket(LISTEN_PORT);

		while (true) {
			Socket socket = listener.accept();
			if (socket.isConnected()) {
				HttpSocketAPI http = new HttpSocket(socket);
				Worker worker = new Worker(http);

				new Thread(worker).start();


			}
		}
		
	}
}
