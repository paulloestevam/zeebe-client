package com.example.zeebeclient.worker;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class WorkerHandler {

    @Autowired
    private ZeebeClient zeebeClient;

    @PostConstruct
    public void registerWorker() {
        log.info("Register worker: start_task");
        JobWorker worker = zeebeClient.newWorker()
                .jobType("start_task")
                .handler(this::handleJobStartTask)
                .open();

        log.info("Register worker: validate_otp");
        worker = zeebeClient.newWorker()
                .jobType("validate_otp")
                .handler(this::handleJobValidateOtp)
                .open();

        log.info("Register worker: end_process");

        worker = zeebeClient.newWorker()
                .jobType("end_process") // Nome do jobType definido no BPMN
                .handler(this::handleEndProcess)
                .open();


    }

    private void handleJobStartTask(JobClient client, ActivatedJob job) {
        log.info("Task in execution: " + job.getType() + "   Variables: " + job.getVariables());
        client.newCompleteCommand(job.getKey()).send().join();
        log.info("Task completed: " + job.getType());
    }

    private void handleJobValidateOtp(JobClient client, ActivatedJob job) {
        log.info("Task in execution: " + job.getType() + "   Variables: " + job.getVariables());
        client.newCompleteCommand(job.getKey()).send().join();
        log.info("Task completed: " + job.getType());
    }

    private void handleEndProcess(final JobClient client, final ActivatedJob job) {
        log.info("Process completed!"
                + " instanceId: " + job.getProcessInstanceKey()
                + " Variables: " + job.getVariables());
        client.newCompleteCommand(job.getKey()).send().join();
    }


}
