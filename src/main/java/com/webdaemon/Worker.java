package com.webdaemon;

import java.io.IOException;

public class Worker implements Runnable {

	// private HTTPSession session;
	HTTPSessionAPI session;
	HandlerStackAPI handlerStack;

	public Worker(HTTPSessionAPI session, HandlerStackAPI handlerStack) {
		this.session = session;
		this.handlerStack = handlerStack;
	}

	@Override
	public void run() {

		Request request = new Request();

		try {
            String requestLine = session.readRequestLine();
            System.out.println(session.readRequestHeaders() + "/r/n" + session.readRequestBody());
            if (requestLine != null)
			    request.parseRequestLine(session.readRequestLine());
			request.parseRequestHeader(session.readRequestHeaders());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Response response = handlerStack.getResponse(request);

		try {
			session.writeResponseStatus(response.getStatusLine());
			session.writeResponseHeader(response.getHeaderString());
			session.writeResponseBody(response.getBodyChars());
			session.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
