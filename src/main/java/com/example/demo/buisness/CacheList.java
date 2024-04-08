package com.example.demo.buisness;

import com.example.demo.models.Task;
import com.example.demo.services.Simulation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Slf4j
public class CacheList {
    @Autowired
    private Simulation simulation;

    @Cacheable(value = "tasks" , key = "#numSimulations", condition="#numSimulations < 500")
    public List<Task> simulateRequestsAsync(int numSimulations) {
        log.info("Simulating {} requests", numSimulations);
        return simulation.simulateRequestsAsync(numSimulations).block();
    }

}
