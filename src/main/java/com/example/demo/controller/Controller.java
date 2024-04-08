package com.example.demo.controller;


import com.example.demo.buisness.CacheList;
import com.example.demo.models.Task;
import com.example.demo.services.Simulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    Simulation simulation;

    @Autowired
    CacheList cacheList;

    @GetMapping("/simulacion/")
    public ResponseEntity<List<Task>> index(@RequestParam(value = "numSimulations", defaultValue = "10") int numSimulations) {
       return ResponseEntity.ok(cacheList.simulateRequestsAsync(numSimulations));
    }
}
