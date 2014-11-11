package com.hydra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	private static int LISTEN_PORT = 5000;

	private ServerSocket listener;
	private Socket socket;
	private Thread worker;

	public Server(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		try {

			// HttpSocketAPI http = new HttpSocket(socket);

			// String request = http.readInput();

			// BufferedReader input = new BufferedReader(new InputStreamReader(
			// socket.getInputStream()));

			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));

			Thread.sleep(1000);
			output.write("HTTP/1.0 404 Not Found\r\n\r\nNothing here");
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		if (worker == null) {
			worker = new Thread(this, "Processor");
			worker.start();
		}
	}

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

				Server server = new Server(socket);
				server.start();
			}

		}

	}

}
