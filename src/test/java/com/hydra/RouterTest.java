package com.hydra;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouterTest {

    Router router;

    @Before
    public void setUp() throws Exception {
        router = new Router();
    }

    @Test
    public void ItRoutesPathsToNotFound() {

        assertEquals("HTTP/1.1 404 Not Found\r\n\r\nOoops there is nothing here :(\r\nraw request", router.route("raw request"));
    }

    @Test
    public void ItRequiresAuthenticationForTheLogsPath() {

        assertEquals("HTTP/1.1 401 Unauthorized\r\n\r\nAuthentication required\r\n\r\nNothing here\r\nGET /logs HTTP/1.1",
                router.route("GET /logs HTTP/1.1"));
    }



    @Test
    public void ItReturnsASuccessStatusForHome() {

        assertEquals("HTTP/1.1 200 OK\r\n\r\nHello World\r\nGET / HTTP/1.1\nHost: localhost",
                router.route("GET / HTTP/1.1\nHost: localhost"));
    }
}
