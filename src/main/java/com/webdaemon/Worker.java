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
            String[] requestHeader = session.readRequestHeaderLines();
            if (requestHeader != null && requestHeader.length > 0) {
			    request.parseHeader(requestHeader);
			    String bodySizeStr = request.getHeader("Content-length"); 
			    String requestBody = session.readRequestBody(bodySizeStr == null ? 0 : Integer.parseInt(bodySizeStr));
			    request.setBody(requestBody);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}

		Response response = handlerStack.getResponse(request);

		try {
			session.writeResponse(response.getBytes());
			session.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
