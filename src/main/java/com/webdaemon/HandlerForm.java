package com.webdaemon;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HandlerForm implements HandlerAPI {

	@Override
	public boolean handleAndStop(Request request, Response response) {

		if (!request.getPath().equals("/form"))
			return false;
		response.setStatus(200);

		String filePath = "/tmp/.formdata";

		try {
			request.parsePostParams(request.getBody().trim());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Write updated data
		if (request.hasQueryParams()) {
			writeFormData("data=" + request.getQueryParam("data"), filePath);
		}
		if (request.hasPostParams()) {
			writeFormData("data=" + request.getPostParam("data"), filePath);
		}
		if (request.getMethod().equals("DELETE"))
			try {
				Files.deleteIfExists(Paths.get(filePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		response.setBody(readFormData(filePath));
		
		
		return true;
	}

	private void writeFormData(String formData, String filePath) {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(formData);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String readFormData(String filePath) {
		String ret = ""; 
		try {
			byte[] buf = Files.readAllBytes(Paths.get(filePath));
			ret = new String(buf); 
		} catch (Exception e) {
			ret = "";
		}
		return ret;
	}
}
