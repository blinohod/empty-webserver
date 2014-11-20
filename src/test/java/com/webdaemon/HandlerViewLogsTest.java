package com.webdaemon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class HandlerViewLogsTest {

	private Response response;
    private Request request;
    private HandlerViewLogs viewLogs;
    private BufferedReader reader;

    @Before
    public void setup() {
        response = new Response();
        request = new Request();
        viewLogs = new HandlerViewLogs();
    }

    @Test
    public void handleAndStopTestTrue() {
        request.setPath("/logs");
        request.setMethod("GET");
        boolean canView = viewLogs.handleAndStop(request, response);
        assertTrue(canView);
    }

    @Test
    public void handleAndStopTestFalse() {
        request.setPath("/logger");
        request.setMethod("GET");
        boolean canView = viewLogs.handleAndStop(request, response);
        assertFalse(canView);
    }

    @Test
    public void setAuthorizedTest() {
        viewLogs.setAuthorized(response);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void setUnAuthorizedTest() {
        viewLogs.setUnAuthorized(response);
        assertEquals(401, response.getStatus());
        assertTrue(new String(response.getBytes()).contains("Authentication required"));
        assertEquals("Basic realm=\"empty_server\"", response.getHeader("WWW-Authenticate"));
    }

    @Test
    public void shouldWriteSingleStringToFile() throws IOException {
        reader = new BufferedReader(new FileReader(viewLogs.getLogFilePath()));
        String testLineToWrite = "Test Line";
        viewLogs.doLogging(testLineToWrite);
        String line;
        while ((line = reader.readLine()) != null) {
            Assert.assertEquals(testLineToWrite, line);
        }

    }

    @Test
    public void shouldWriteMultipleStringToLogFile() throws IOException {
        String testLineToWrite = "Test Line";
        String nextLineToWrite = "Test Line 22";
        int count = 0;
        reader = new BufferedReader(new FileReader(viewLogs.getLogFilePath()));
        viewLogs.doLogging(nextLineToWrite);
        String line;
        while ((line = reader.readLine()) != null) {
            if (count == 0)
                Assert.assertEquals(nextLineToWrite, line);
            else if (count == 1)
                Assert.assertEquals(testLineToWrite, line);
            count++;
        }
    }

}