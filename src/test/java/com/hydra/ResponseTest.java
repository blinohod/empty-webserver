package com.hydra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseTest {

    private Response response;
    private Request request;

    @Before
    public void setup() {
        request = new Request();
        response = new Response(request);
    }

    @Test
    public void testGetSuccessStatus() throws Exception {
        assertEquals(response.getSuccessStatus(), "HTTP/1.1 200 OK");
    }

    @Test
    public void testGetFailedStatus() throws Exception {
        assertEquals(response.getFailedStatus(), "HTTP/1.1 404 Not Found");
    }
}