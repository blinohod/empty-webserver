package com.hydra;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static int LISTEN_PORT = 5000;
	private static String DEFAULT_PUBLIC_DIR = "/tmp";

	public static void main(String[] args) throws IOException, Exception {

		String publicDir = System.getenv("PUBLIC_DIR");
		if (publicDir == null) {
			publicDir = DEFAULT_PUBLIC_DIR;
			System.out.println("Warning: PUBLIC_DIR is not defined.");
		}

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
