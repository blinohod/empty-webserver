package com.hydra;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static int LISTEN_PORT = 5000;

	private ServerSocket listener;
	private Socket socket;
	
	public Server() {
		
	}
	
	public static void main(String[] args) throws IOException {

		
		/*
		String publicDir = System.getenv("PUBLIC_DIR");
		if (publicDir == null) {
			System.out.println("Please define PUBLIC_DIR env variable");
			System.exit(1);
		}
		*/
		
		// API to request processing
		//HandlerAPI handler = new Handler(publicDir);
		
		ServerSocket listener = new ServerSocket(LISTEN_PORT);
		
		while (true) {
			Socket socket = listener.accept();

			System.out.println("Accepted");
		}
		// listener.close();


		
	}
}
