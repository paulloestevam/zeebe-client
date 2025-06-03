package com.example.zeebeclient.worker;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WorkerHandler {

    @Autowired
    private ZeebeClient zeebeClient;

    @PostConstruct
    public void registerWorker() {
        System.out.println("Registrar un proceso");
        JobWorker worker = zeebeClient.newWorker()
                .jobType("start_task")
                .handler(this::handleJob)
                .open();
    }

    private void handleJob(JobClient client, ActivatedJob job) {
        // Exemplo de lógica para envio de e-mail
        System.out.println("Tarea en ejecución: " + job.getType());
        System.out.println("Variables recibidas: " + job.getVariables());
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
