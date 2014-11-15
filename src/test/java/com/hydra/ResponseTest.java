package com.hydra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ResponseTest {

    private Response response;

    @Before
    public void setup() {
        response = new Response();
    }

    @Test
    public void testGetSuccessStatus() throws Exception {
        assertArrayEquals(response.getSuccessStatus(), new String[]{"HTTP/1.1 200 OK", "Hello World"});
    }

    @Test
    public void testGetFailedStatus() throws Exception {
        assertArrayEquals(response.getNotFoundStatus(), new String[]{"HTTP/1.1 404 Not Found", "Ooops there is nothing here :("});
    }

    @Test
    public void testGetNotAuthorizedStatus() throws Exception {
        assertArrayEquals(response.getNotAuthorizedStatus(), new String[]{"HTTP/1.1 401 Unauthorized", "Authentication required", "Nothing here"});
    }

    @Test
    public void testFormatStatus() throws Exception {
        String[] test = new String[] {"a", "b", "c"};
        assertEquals(response.formatStatus(test), String.join("\r\n\r\n", test) + "\r\n");
    }
}