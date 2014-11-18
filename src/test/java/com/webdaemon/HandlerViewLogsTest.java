package com.webdaemon;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class HandlerViewLogsTest {

    Response response;
    Request request;
    HandlerViewLogs viewLogs;

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
        assertArrayEquals("Authorized!!!".toCharArray(), response.getBodyChars());
    }

    @Test
    public void setUnAuthorizedTest() {
        viewLogs.setUnAuthorized(response);
        assertEquals(401, response.getStatus());
        assertArrayEquals("Authentication required".toCharArray(), response.getBodyChars());
        assertEquals("Basic realm=\"empty_server\"", response.getHeader("WWW-Authenticate"));
    }

}