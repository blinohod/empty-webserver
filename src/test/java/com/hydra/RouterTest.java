package com.hydra;
import org.junit.Test;
import static org.junit.Assert.*;

public class RouterTest {

    @Test
    public void ItRoutesPathsToNotFound() {
        Router router = new Router();

        assertEquals("HTTP/1.0 404 Not Found\r\n\r\nNoothing here\r\nraw request", router.route("raw request"));
    }

    @Test
    public void ItRequiresAuthenticationForTheLogsPath() {
        Router router = new Router();

        assertEquals("HTTP/1.0 401 Unauthorized\r\n\r\nAuthentication required\r\n", router.route("logs"));
    }
}
