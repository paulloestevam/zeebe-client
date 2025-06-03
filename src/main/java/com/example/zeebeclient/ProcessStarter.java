package com.example.zeebeclient;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProcessStarter {

    @Autowired
    private ZeebeClient zeebeClient;

    public void startExampleProcess(String nome) {
        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("Process_qzqw2uj")
                .latestVersion()
                .variables(Map.of("22222", nome))
                .send()
                .join();
    }
}
