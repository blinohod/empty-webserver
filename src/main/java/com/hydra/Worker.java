package com.hydra;

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

            if(isPathAllowed(request)){
                http.writeOutput("HTTP/1.0 404 Not Found\r\n\r\nNoothing here\r\n"
                        + request);
            } else {
                http.writeOutput("HTTP/1.0 401 Unauthorized\r\n\r\nAuthentication required\r\n");
            }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public boolean isPathAllowed(String request) {
        return !request.contains("logs");
    }

}
