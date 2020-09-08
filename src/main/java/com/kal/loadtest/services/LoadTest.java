package com.kal.loadtest.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface LoadTest {
    /**
     * Start Load Test.
     * @return
     */
    public String runLoadTest() throws IOException, InterruptedException;

    /**
     * Terminate Load Test Server.
     */
    public void terminateLoadTestServer();
}
