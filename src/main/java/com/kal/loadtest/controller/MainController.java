package com.kal.loadtest.controller;

import com.kal.loadtest.services.LoadTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MainController {

    @Autowired
    LoadTest test;

    @GetMapping(path = "/init")
    public String initiateLoadTest() throws IOException, InterruptedException {
        return test.runLoadTest();
    }

//    @GetMapping(path = "/shutdown")
//    public void shutdownTestServer() {
//        test.terminateLoadTestServer();
//    }
}
