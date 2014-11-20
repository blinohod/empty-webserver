package com.webdaemon;

import java.util.Base64;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class HandlerViewLogs implements HandlerAPI {

    private String credentials = "Basic " + Base64.getEncoder().encodeToString("admin:hunter2".getBytes());
    private String docRoot = Server.getPublicDir();
    private final Logger logger = Logger.getLogger("logs");
    private FileHandler fh = null;
    private static int countLog = 0;
    private String logFilePath = docRoot + "logs" + countLog + ".log";
    private static String bodyText = "";

    public HandlerViewLogs() {
        try {
            fh = new FileHandler(logFilePath);
            fh.setFormatter(new Formatter() {
                public String format(LogRecord record) {
                    return record.getMessage() + "\n";
                }
            });
            logger.addHandler(fh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        countLog++;
    }

	@Override
	public boolean handleAndStop(Request request, Response response) {

        doLogging(request.getRequestLine());

		if(request.getPath().equals("/logs") && request.getMethod().equals("GET")) {

            if(request.getHeader("Authorization") != null && request.getHeader("Authorization").contains(credentials)) {
                setAuthorized(response);
            } else {
                setUnAuthorized(response);
            }
			return true;
		} else 
			return false;
	}

    public void doLogging(String logText) {
        logger.info(logText);
        bodyText += logText + "\n";
    }
     public String getLogFilePath() {
         return this.logFilePath;
     }

    public void setAuthorized(Response response) {
        response.setStatus(200);
        response.setBody(bodyText);
    }

    public void setUnAuthorized(Response response) {
        response.setStatus(401);
        response.setBody("Authentication required");
        response.setHeader("WWW-Authenticate", "Basic realm=\"empty_server\"");
    }

}
