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

            Request req = new Request(request);

            Response reponse = new Response(req);

            if(isPathAllowed(req)){
                http.writeOutput("HTTP/1.0 404 Not Found\r\n\r\nNothing here\r\n"
                        + request);
            } else {
                http.writeOutput("HTTP/1.0 401 Unauthorized\r\n\r\n" +
//                        "Authentication required\r\n" + request +
                        "WWW-Authenticate: Basic realm=\"empty_server\"\r\n");
            }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public boolean isPathAllowed(Request request) {
        return !request.getPath().contains("logs");
    }

}
