package com.hydra;

public class MockHttpSocket implements HttpSocketAPI {

	public String input;
	public String output;
	
	@Override
	public String readInput() {
		return input;
	}

	@Override
	public void writeOutput(String output) {
		this.output = output;
	}

}
