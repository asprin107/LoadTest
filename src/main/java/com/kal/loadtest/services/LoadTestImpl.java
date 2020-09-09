package com.kal.loadtest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoadTestImpl implements LoadTest{

    @Value("${loadtest.init.script}")
    private String runCommand;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void runLoadTest() {
        Thread thread = new LoadTestThread(logger, runCommand);
        thread.start();
        logger.info("Method Requested.");
    }

    @Override
    public void terminateLoadTestServer() {

    }
}
