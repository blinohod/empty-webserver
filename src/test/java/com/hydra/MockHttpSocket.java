package com.hydra;

public class MockHttpSocket implements HttpSocketAPI {

	public String input;
	public String output;
	
	public void setInput() {
		
	}
	
	@Override
	public String readInput() {
		return input;
	}

	@Override
	public void writeOutput(String output) {
		this.output = output;
	}

	@Override
	public String readRequestLine() {
		// TODO Auto-generated method stub
		return null;
	}

}
