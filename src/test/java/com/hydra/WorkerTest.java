package com.hydra;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorkerTest {

    @Test
    public void ItRoutesInputFromTheHttpSocketInputAndWritesItToTheOutput() {
        MockHttpSocket mockHttpSocket = new MockHttpSocket();
        mockHttpSocket.input = "The Input";

        Worker worker = new Worker(mockHttpSocket);
        worker.run();

        assertEquals("HTTP/1.0 404 Not Found\r\n\r\nNoothing here\r\nThe Input",
                mockHttpSocket.output);
    }
}