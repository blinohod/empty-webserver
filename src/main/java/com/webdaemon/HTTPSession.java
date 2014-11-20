package com.webdaemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class HTTPSession implements HTTPSessionAPI {

	private Socket socket;
	private BufferedReader input;
	ArrayList<String> headerLines = new ArrayList<String>();

	public HTTPSession(Socket socket) throws Exception {
		this.socket = socket;
		this.input = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
	}

	@Override
	public String[] readRequestHeaderLines() throws IOException {
		if (headerLines.isEmpty()) {
			String line;
			do {
				line = input.readLine();
				if (line != null && !line.isEmpty())
					headerLines.add(line);
			} while (line != null && !line.isEmpty());
		}
		return headerLines.toArray(new String[headerLines.size()]);
	}

	@Override
	public String readRequestBody(int bodySize) throws IOException {
		char[] buf = new char[bodySize];
		int size = input.read(buf);
		return new String(Arrays.copyOf(buf, size));
	}

	@Override
	public void close() throws IOException {
		socket.close();
	}

	@Override
	public void writeResponse(byte[] response) throws IOException {
		if (response != null)
			socket.getOutputStream().write(response);
		socket.getOutputStream().flush();
	}

}
