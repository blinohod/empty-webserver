package com.hydra;

import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;

public class WorkerTest {

    private Request request;

    @Before
    public void setup() {

        request = new Request();

    }

    @Test
    public void testIsPathAllowed() throws Exception{
        ServerSocket listener = new ServerSocket(33000);
        Socket client = new Socket(InetAddress.getLocalHost(),33000);
        Socket socket = listener.accept();

        HttpSocket http = new HttpSocket(socket);
        Worker worker = new Worker(http);

        assertFalse(worker.isPathAllowed("logs"));

    }
}