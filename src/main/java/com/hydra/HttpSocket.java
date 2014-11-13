package com.hydra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HttpSocket implements HttpSocketAPI {

	private Socket socket;
	private BufferedReader input;
	private BufferedWriter output;

	public HttpSocket(Socket socket) {
		this.socket = socket;
		try {
			this.input = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			this.output = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String readInput() throws IOException {
		String buffer = "";
		do {
			buffer = buffer + input.readLine();
		} while (input.ready());
		return buffer;
	}

	public void writeOutput(String buffer) throws IOException {
		output.write(buffer);
		output.flush();
		output.close();
	}

}
