package com.kal.loadtest.services;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface LoadTest {
    /**
     * Start Load Test.
     */
    public void runLoadTest() throws IOException, InterruptedException, ExecutionException;

    /**
     * Terminate Load Test Server.
     */
    public void terminateLoadTestServer();
}
