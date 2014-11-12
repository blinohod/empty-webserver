package com.hydra;

import java.net.Socket;

public class Worker implements Runnable {

	private HttpSocketAPI http;

	public Worker(HttpSocketAPI http) {
		this.http = http;
	}

	@Override
	public void run() {

		try {
			
			String request = http.readInput();
			Thread.sleep(1000);
			http.writeOutput("HTTP/1.0 404 Not Found\r\n\r\nNothing here\r\n"
					+ request);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
