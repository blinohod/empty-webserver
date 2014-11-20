package com.webdaemon;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HandlerLoggerTest {

    private HandlerLogger logWriter;
    private Request request;
    private Response response;
    private BufferedReader reader;
    private String testLineToWrite;

    @Before
    public void setUp() throws IOException {

        request = new Request();
        request.setMethod("GET");
        request.setPath("/");
        response = new Response();
        logWriter=new HandlerLogger();
        reader = new BufferedReader(new FileReader(logWriter.logFilePath));
        testLineToWrite = "Test Line";
    }

    @Test
    public void shouldWriteSingleStringToFile() throws IOException {

        logWriter.doLogging(testLineToWrite);
        String line;
        while ((line = reader.readLine()) != null) {
            assertEquals("doLogging(): "+ testLineToWrite, line);
        }

    }

    @Test
    public void shouldWriteMultipleStringToLogFile() throws IOException {
        String nextLineToWrite = "Test Line 22";
        int count = 0;
        logWriter.doLogging(nextLineToWrite);
        String line;
        while ((line = reader.readLine()) != null) {
            if (count == 0)
                assertEquals("doLogging(): "+ nextLineToWrite, line);
            else if (count == 1)
                assertEquals("doLogging(): "+ testLineToWrite, line);
            count++;
        }
    }
}