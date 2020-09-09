package com.kal.loadtest.controller;

import com.kal.loadtest.services.LoadTest;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MainController {

    @Autowired
    LoadTest test;

    @GetMapping(path = "/isAlive")
    public String isAlive() throws IOException, InterruptedException {
        return "Server is Alive";
    }

    @GetMapping(path = "/init")
    public String initiateLoadTest() throws IOException, InterruptedException, ExecutionException {
        test.runLoadTest();
        return "Load Test initiated.";
    }
}
