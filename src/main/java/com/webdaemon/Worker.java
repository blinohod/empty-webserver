package com.webdaemon;

import java.io.IOException;

public class Worker implements Runnable {

	// private HTTPSession session;
	HTTPSessionAPI session;
	HandlerAPI handler;

	public Worker(HTTPSessionAPI session, HandlerAPI handler) {
		this.session = session;
		this.handler = handler;
	}

	@Override
	public void run() {

		Request request = new Request();

		try {
			request.parseRequestLine(session.readRequestLine());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Response response = handler.getResponse(request);

		try {
			session.writeResponseStatus(response.getStatusLine());
			session.writeResponseHeader("Content-type: text/plain");
			session.writeResponseBody(new String("TEST").toCharArray());
			session.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
