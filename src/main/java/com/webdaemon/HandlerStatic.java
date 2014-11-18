package com.webdaemon;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HandlerStatic implements HandlerAPI {


	@Override
	public boolean handleAndStop(Request request, Response response) {

		String docRoot = System.getenv("PUBLIC_DIR");

		String filePath = docRoot + request.getPath();
		File file = new File(filePath);

		if (!file.exists())
			return false;

		if (!request.getMethod().equals("GET")
				&& !request.getMethod().equals("PATCH")) {
			response.setStatus(405);
			return true;
		}

		if (file.isDirectory()) {
			response.setStatus(200);
			response.setBody("Directory here");
			return true;
		}

		if (file.isFile()) {
			response.setStatus(200);
			try {
				char[] content = new char[(int) file.length()];
				FileReader reader = new FileReader(file);
				int len = reader.read(content);
				reader.close();
				response.setBody(content);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}

		return false;

	}

}