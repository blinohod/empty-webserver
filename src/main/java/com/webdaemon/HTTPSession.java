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
		if (requestLine == null)
			requestLine = input.readLine();
		return requestLine;
	}

	@Override
	public String readRequestHeaders() throws IOException {
		String headers = "";
		String ln;
		do {
			ln = input.readLine();
			headers = headers + ln + "\r\n";
		} while (ln != null && !ln.isEmpty());
		return headers;
	}

	@Override
	public byte[] readRequestBody() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeResponseStatus(String statusLine) throws IOException {
		output.write(statusLine + "\r\n");
		output.flush();
	}

	@Override
	public void writeResponseHeader(String headerString) throws IOException {
		if (headerString.isEmpty())
			output.write("\r\n");
		else
			output.write(headerString + "\r\n");
		output.flush();
	}

	@Override
	public void close() throws IOException {
		socket.close();	
	}

	@Override
	public void writeResponseBody(char[] body) throws IOException {
		if (body != null)
			output.write(body);
		output.flush();
	}

}
