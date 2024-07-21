package com.example.spring;


import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class MainApplication {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        try {
            List<Runnable> tasks = getProcessors();

            List<Future<?>> futures = tasks.stream()
                    .map(executorService::submit)
                    .collect(Collectors.toList());

            futures.forEach(MainApplication::tryGet);
        } finally {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException ie) {
                executorService.shutdownNow();
            }
        }
    }

    private static List<Runnable> getProcessors() {
        return List.of(
                () -> new CsbProcessor().process(),
                () -> new CsbProcessor().process(),
                () -> new CsbProcessor().process(),
                () -> new CsbProcessor().process());
    }

    private static void tryGet(Future<?> future) {
        try {
            future.get(); // Ensure all tasks complete
        } catch (ExecutionException | InterruptedException e) {
            // Handle exceptions from task execution
            e.printStackTrace();
        }
    }

}