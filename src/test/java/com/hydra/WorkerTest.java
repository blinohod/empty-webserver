package com.hydra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest {

    private Request request;
    private MockHttpSocket http;
    private Worker worker;

    @Before
    public void setup() {

        request = new Request();
        http = new MockHttpSocket();
        worker = new Worker(http);

    }

    @Test
    public void testIsPathAllowedLogs() throws Exception {

        Request requestLogs = new Request("GET /logs HTTP/1.0\r\n"
                + "Host: test.com\r\n" + "\r\n");

        assertFalse(worker.isPathAllowed(requestLogs));
    }

    @Test
    public void testIsPathAllowedOther() throws Exception{

        Request requestOther = new Request("GET /other HTTP/1.0\r\n"
                + "Host: test.com\r\n" + "\r\n");

        assertTrue(worker.isPathAllowed(requestOther));

    }

    @Test
    public void checkCredsTestPassingCorrectCreds() {

        Request requestLogsWithCreds = new Request("GET /logs?username=admin&password=hunter2 HTTP/1.0\r\n"
                + "Host: test.com\r\n" + "\r\n");
//        assertTrue(worker.checkCreds(requestLogsWithCreds));

    }

    @Test
    public void checkCredsTestPassingNoCreds() {
        Request requestLogs = new Request("GET /logs HTTP/1.0\r\n"
                + "Host: test.com\r\n" + "\r\n");
//        assertFalse(worker.checkCreds(requestLogs));

    }

    @Test
    public void checkCredsTestPassingBadCreds() {
        Request requestLogs = new Request("GET /logs?username=user&password=ppppdpddp HTTP/1.0\r\n"
                + "Host: test.com\r\n" + "\r\n");

//        assertFalse(worker.checkCreds(requestLogs));

    }



}