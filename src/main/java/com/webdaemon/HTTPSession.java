package com.webdaemon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HTTPSession implements HTTPSessionAPI {

	private Socket socket;
	private BufferedReader input;
	private BufferedWriter output;
	
	private String requestLine;

	public HTTPSession(Socket socket) throws Exception {
		this.socket = socket;
		this.input = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		this.output = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
	}

	@Override
	public String readRequestLine() throws IOException {
		if (requestLine != null)
			return requestLine;
		else
			return input.readLine();
	}

	@Override
	public String readRequestHeaders() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] readRequestBody() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeResponseStatus(String statusLine) throws IOException {
		output.write(statusLine);
		output.flush();
	}

}
