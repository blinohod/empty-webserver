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
    private String requestLine;
	private Hashtable<String, String> queryParams;
	private Hashtable<String, String> postParams;
	private Hashtable<String, String> headers;
	private String body;
    public String logFile;

	public Request() {
		this.queryParams = new Hashtable<String, String>();
		this.postParams = new Hashtable<String, String>();
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

    public String getRequestLine() {
        return requestLine;
    }

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

    public String getHeader(String name) {
		return this.headers.get(name.toLowerCase());
	}

	public void setHeader(String name, String value) {
		this.headers.put(name.toLowerCase(), value);
	}

	public void parseHeader(String[] lines) throws Exception {
		parseRequestLine(lines[0]);
		for(int i=1; i<lines.length; i++)
			parseHeaderLine(lines[i]);
	}

	// ============== Private methods after this line

	private void parseRequestLine(String requestLine) throws Exception {
		Pattern p = Pattern
				.compile("^(CONNECT|DELETE|GET|HEAD|OPTIONS|POST|PUT|TRACE|PATCH)\\s+([^\\s]+)\\s+HTTP/(\\d.\\d)$");
		Matcher m = p.matcher(requestLine);
		if (m.find()) {
            this.requestLine = requestLine;
			setMethod(m.group(1));
			parseUrl(m.group(2));
			setHttpVersion(m.group(3));
		} else {
			throw new Exception("Cannot parse request line");
		}
	}

	public String getPostParam(String name) {
		if (postParams.containsKey(name)) {
			return postParams.get(name);
		} else
			return null;
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

	private void parseQueryString(String qstring)
			throws UnsupportedEncodingException {
		String pairs[] = qstring.split("&");
		for (String pair : pairs) {
			String keyVal[] = pair.split("=", 2);
			queryParams.put(URLDecoder.decode(keyVal[0], "UTF-8"),
					URLDecoder.decode(keyVal[1], "UTF-8"));
		}
	}

	public void parseHeaderLine(String header) {
		String parts[] = header.split("\\s*:\\s*", 2);
		if (parts.length == 2)
			this.setHeader(parts[0], parts[1]);
	}

	public String getHeaderString() {
		String headerString = "";
		for (String name : headers.keySet()) {
			headerString += name + ": " + headers.get(name) + "\r\n";
		}
		return headerString;
	}
	
	public void parsePostParams(String post) throws UnsupportedEncodingException {
		if (post == null || post.isEmpty())
			return;
		
		String pairs[] = post.split("&");
		for (String pair : pairs) {
			String keyVal[] = pair.split("=", 2);
			postParams.put(URLDecoder.decode(keyVal[0], "UTF-8"),
					URLDecoder.decode(keyVal[1], "UTF-8"));
		}
	}

	public boolean hasPostParams() {
		return (postParams.size() > 0);
	}
	
	public boolean hasQueryParams() {
		return (queryParams.size() > 0);
	}
	
}
