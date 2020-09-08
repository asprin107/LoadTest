package com.kal.loadtest.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class LoadTestImpl implements LoadTest{

    @Value("${loadtest.init.script}")
    private String INIT_SCRIPT_FILE;

    @Override
    public String runLoadTest() throws IOException, InterruptedException {
        StringBuilder stdout = new StringBuilder();
        Process process = Runtime.getRuntime().exec(INIT_SCRIPT_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String temp;
        while ((temp = br.readLine()) != null) {
            stdout.append(temp+"\n");
        }
        System.out.println ("text: " + stdout);
        process.waitFor();
        System.out.println ("exit: " + process.exitValue());
        process.destroy();

        if (process.exitValue() == 0) {
            return "Test finished successfully.";
        } else {
            return "Test failed";
        }
    }

    @Override
    public void terminateLoadTestServer() {

    }
}
