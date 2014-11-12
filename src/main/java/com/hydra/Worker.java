package com.hydra;

import java.net.Socket;

public class Worker implements Runnable {

	private Socket socket;

	public Worker(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			HttpSocketAPI http = new HttpSocket(socket);
			String request = http.readInput();
			Thread.sleep(1000);
			http.writeOutput("HTTP/1.0 404 Not Found\r\n\r\nNothing here\r\n"
					+ request);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
