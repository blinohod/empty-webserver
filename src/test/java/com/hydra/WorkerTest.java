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

        assertEquals(mockHttpSocket.output, "HTTP/1.1 404 Not Found\r\n\r\nOoops there is nothing here :(\r\nThe Input");
    }
}