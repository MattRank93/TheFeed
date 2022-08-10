package com.interactions.thefeed.controller;

import com.interactions.thefeed.responseobjects.HealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UtilityController {


    public UtilityController() {
    }


    @GetMapping("/health")
    public ResponseEntity<?> healthCheck(){
        Runtime runtime = Runtime.getRuntime();
        HealthResponse hResponse = new HealthResponse(runtime.freeMemory()/1048576, runtime.maxMemory()/1048576, runtime.totalMemory()/1048576);

        return ResponseEntity.ok(hResponse);
    }

}
