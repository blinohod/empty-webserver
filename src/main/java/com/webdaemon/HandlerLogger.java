package com.webdaemon;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class HandlerLogger implements HandlerAPI {

    private final Logger logger = Logger.getLogger("logs");
    private FileHandler fh = null;
    private String docRoot = Server.getPublicDir();
    private static int countLog = 0;
    public String logFilePath = docRoot + "logs" + countLog + ".log";

    public HandlerLogger() {

        try {
            fh = new FileHandler(logFilePath);
//
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new Formatter() {
            public String format(LogRecord record) {
                return record.getSourceMethodName()
                        + "(): "
                        + record.getMessage() + "\n";
            }
        });

        logger.addHandler(fh);
        countLog++;
    }




    @Override
	public boolean handleAndStop(Request request, Response response) {

        try {
            doLogging(request.getHeaderString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

		return false;
	}

    public void doLogging(String logText) {
        logger.info(logText);
    }

    public FileHandler getFh() {
        return fh;
    }

}
