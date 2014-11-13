package com.hydra;

import java.io.IOException;

public interface HttpSocketAPI {

	public String readInput() throws IOException;
	public void writeOutput(String output) throws IOException;
	
	public String readRequestLine();
	
	
}
