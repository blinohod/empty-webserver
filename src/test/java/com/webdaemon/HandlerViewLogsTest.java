package com.webdaemon;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class HandlerViewLogsTest {

	private Response response;
    private Request request;
    private HandlerViewLogs viewLogs;

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
        assertTrue(new String(response.getBytes()).contains("Authorized!!!"));
    }

    @Test
    public void setUnAuthorizedTest() {
        viewLogs.setUnAuthorized(response);
        assertEquals(401, response.getStatus());
        assertTrue(new String(response.getBytes()).contains("Authentication required"));
        assertEquals("Basic realm=\"empty_server\"", response.getHeader("WWW-Authenticate"));
    }

}