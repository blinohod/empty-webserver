package com.webdaemon;

import java.util.ArrayList;

public class HandlerStack implements HandlerStackAPI {
	
	private ArrayList<HandlerAPI> handlers;
	
	public HandlerStack() {
		handlers = new ArrayList<HandlerAPI>();
		handlers.add(new HandlerViewLogs());
		handlers.add(new HandlerForm());
		handlers.add(new HandlerRedirect());
		handlers.add(new HandlerOptions());
		handlers.add(new HandlerStatic());		
		handlers.add(new HandlerDefault()); // return final 404 if no handlers interested		
	}

	@Override
	public Response getResponse(Request request) {

		Response response = new Response();

		for (HandlerAPI handler : handlers)
			if(handler.handleAndStop(request, response))
				break;

		return response;
	}

}
