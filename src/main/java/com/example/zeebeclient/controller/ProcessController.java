package com.example.zeebeclient.controller;

import com.example.zeebeclient.ProcessStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessStarter processStarter;

    @GetMapping("/start")
    public String startProcess(@RequestParam String nome) {
        processStarter.startExampleProcess(nome);
        return "Proceso iniciado con nombre: " + nome;
    }
}