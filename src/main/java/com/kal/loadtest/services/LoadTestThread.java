package com.kal.loadtest.services;

import lombok.SneakyThrows;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoadTestThread extends Thread {

    private String runCommand;
    Logger logger;

    public LoadTestThread(Logger logger, String runCommand) {
        this.logger = logger;
        this.runCommand = runCommand;
    }

    @SneakyThrows
    @Override
    public void run() {
        StringBuilder stdout = new StringBuilder();
        Process process = Runtime.getRuntime().exec(runCommand);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String temp;
        while ((temp = br.readLine()) != null) {
            stdout.append(temp+"\n");
        }
        logger.info("text: " + stdout);
        process.waitFor();
        logger.info("exit: " + process.exitValue());
        process.destroy();

//        if (process.exitValue() == 0) {
//            return "Test finished successfully.";
//        } else {
//            return "Test failed";
//        }
    }
}
