package com.hydra;

public class Worker implements Runnable {

	private HttpSocketAPI http;

	public Worker(HttpSocketAPI http) {
		this.http = http;
	}

	public void run() {

		try {
			String request = http.readInput();
			Thread.sleep(1000);

            Router router = new Router();
            http.writeOutput(router.route(request));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
