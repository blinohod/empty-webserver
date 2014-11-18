package com.webdaemon;

import java.io.File;

public class HandlerStatic implements HandlerAPI {

	private static final String docRoot = "/Users/michael.bochkaryov/Documents/workspace/cob_spec/public";

	@Override
	public boolean handleAndStop(Request request, Response response) {

		String filePath = docRoot + request.getPath();
		File file = new File(filePath);

		if (!file.exists())
			return false;

		if (!request.getMethod().equals("GET")
				|| request.getMethod().equals("PATCH")) {
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
			response.setBody("File content here");
			return true;
		}

		return false;

	}

}
