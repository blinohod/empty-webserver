package com.webdaemon;

public class Worker implements Runnable {

	//private HTTPSession session;
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

		String response = handler.processRequest(request);

		session.writeResponseStatus(response);
		
	}
	
	
}
