package com.example.zeebeclient.controller;

import com.example.zeebeclient.ProcessStarter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessStarter processStarter;

    @GetMapping("/start")
    public String startProcess(@RequestParam String requestId) {
        log.info("Process started with requestId: " + requestId);
        processStarter.startExampleProcess(requestId);
        return requestId;
    }

}