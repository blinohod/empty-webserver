package com.webdaemon;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {

	private String method;
	private String path;
	private String httpVersion;
	private Hashtable<String, String> queryParams;
	private Hashtable<String, String> postParams;	
	private Hashtable<String, String> headers;
	private byte[] body;

	public Request() {
		this.queryParams = new Hashtable<String, String>();
		this.headers = new Hashtable<String, String>();
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getHttpVersion() {
		return httpVersion;
	}
	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}

	public byte[] getBody() {
		return body;
	}
	public void setBody(byte[] body) {
		this.body = body;
	}
	
	public void parseRequestLine(String requestLine) throws Exception {
        if (requestLine == null)
            return;
		Pattern p = Pattern.compile("^(CONNECT|DELETE|GET|HEAD|OPTIONS|POST|PUT|TRACE|PATCH)\\s+([^\\s]+)\\s+HTTP/(\\d.\\d)$");
		Matcher m = p.matcher(requestLine);
		if(m.find()) {
			setMethod(m.group(1));
			parseUrl(m.group(2));
			setHttpVersion(m.group(3));
		} else {
			throw new Exception("Cannot parse request line");
		}
	}

	public String getQueryParam(String name) {
		if (queryParams.containsKey(name)) {
			return queryParams.get(name);
		} else
			return null;
	}
	
	private void parseUrl(String url) throws UnsupportedEncodingException {
		String[] parts = url.split("\\?", 2);
		setPath(parts[0]);
		if (parts.length > 1)
			parseQueryString(parts[1]);
	}
	
	private void parseQueryString(String qstring) throws UnsupportedEncodingException{
		String pairs[] = qstring.split("&");
		for(String pair : pairs) {
			String keyVal[] = pair.split("=", 2);
			queryParams.put(URLDecoder.decode(keyVal[0],"UTF-8"), URLDecoder.decode(keyVal[1],"UTF-8"));
		}
	}
	
	public void parseRequestHeader(String header) {
		if (header == null)
            return;
        String headers[] = new String[2];
        headers = header.split("\\r\\n");


		for (String headerLine : headers) {
            if (headerLine!=null) {

                String parts[] = headerLine.split("\\s*:\\s*", 2);
                if (parts[1] == null)
                    parts[1] = "WhatIsTHisValue";
                if (parts[0] == null)
                    parts[0] = "WhatIsTHisKey";
                this.headers.put(parts[0], parts[1]);
            }
		}
	}

    public String getHeaderString() {
        String headerString = "";
        for ( String name : headers.keySet()) {
            headerString += name + ": " + headers.get(name) + "\r\n";
        }
        return headerString;
    }
	
	public String getHeader(String name) {
		return this.headers.get(name);
	}

    public void setHeader(String name, String value) {
        this.headers.put(name, value);
    }
	
	public void parseBody(byte[] rawBody) {
		setBody(rawBody);
	}
	
}
