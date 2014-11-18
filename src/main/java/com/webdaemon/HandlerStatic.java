package com.webdaemon;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HandlerStatic implements HandlerAPI {


	@Override
	public boolean handleAndStop(Request request, Response response) {

		String docRoot = System.getenv("PUBLIC_DIR") + "public";

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
			String body = "<html>\n<head></head>\n<body>\n";
			for (String fname : file.list()) {
				body += "<a href=\"" + fname + "\">" + fname + "</a><br>\n"; 
			}
			body += "\n</body></html>\n";
			response.setHeader("Content-type", "text/html");
			response.setBody(body);
			return true;
		}

		if (file.isFile()) {
			response.setStatus(200);
			try {
				char[] content = new char[(int) file.length()];
				FileReader reader = new FileReader(file);
				int len = reader.read(content);
				reader.close();
				response.setHeader("Content-type", getContentType(filePath));
				response.setBody(content);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}

		return false;

	}
	
	private String getContentType(String fname) {
		if (fname.endsWith(".gif"))
			return "image/gif";
		else if (fname.endsWith(".png"))
			return "image/png";
		return "text/plain";
	}

}
