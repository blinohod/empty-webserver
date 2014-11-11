package com.hydra;

public class MockHttpRequest implements HttpRequestAPI {

	private String request;
	
	private String method;
	private String uri;
	
	
	public MockHttpRequest(String request) {
		this.request = request;
	}

	@Override
	public void setRequest(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getResponseStatus() {
		// TODO Auto-generated method stub
		return 404;
	}

}
