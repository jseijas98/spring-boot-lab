package com.example.demo.services;

import com.example.demo.models.Task;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class Simulation {

    public Mono<Task> simulateRequests(int numRequests) {
        Task task = new Task();
        task.setNum_tasks("Task " + (numRequests + 1) + " completed");

        long startTime = System.currentTimeMillis(); // Marca de tiempo antes de la simulación

        return Mono.just(task)
                .delayElement(Duration.ofMillis(generateRandomNumber()))
                .map(result -> {
                    long endTime = System.currentTimeMillis(); // Marca de tiempo después de la simulación
                    long duration = endTime - startTime; // Cálculo de la duración de la simulación en milisegundos
                    result.setTask_end("Task " + (numRequests + 1) + " end time, Duration: " + duration + " ms");
                    return result;
                });
    }

    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(101) + 500;
    }

    public Mono<List<Task>> simulateRequestsAsync(int numSimulations) {
        List<Mono<Task>> monoList = new ArrayList<>();

        for (int i = 0; i < numSimulations; i++) {
            Mono<Task> monoTask = simulateRequests(i);
            monoList.add(monoTask);
        }

        return Flux.fromIterable(monoList)
                .flatMap(mono -> mono)
                .collectList();
    }
}

