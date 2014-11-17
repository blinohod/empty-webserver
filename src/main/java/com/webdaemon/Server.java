package com.webdaemon;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static int DEFAULT_LISTEN_PORT = 5000;
	private static String DEFAULT_DOCUMENT_ROOT = "/var/www/htdocs";

	public static void main(String[] args) {

		String publicDir = System.getenv("PUBLIC_DIR");
		if (publicDir == null) {
			publicDir = DEFAULT_DOCUMENT_ROOT;
			System.out.println("Warning: PUBLIC_DIR is not defined.");
		}

		try {
			ServerSocket listener = new ServerSocket(DEFAULT_LISTEN_PORT);

			while (true) {
				Socket socket = listener.accept();

				if (socket.isConnected()) {
					HandlerAPI handler = new Handler();
					HTTPSessionAPI session = new HTTPSession(socket);
					Worker worker = new Worker(session, handler);

					new Thread(worker).start();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
