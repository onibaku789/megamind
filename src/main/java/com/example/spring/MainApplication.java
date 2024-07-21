package com.example.spring;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainApplication {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> new DoSomething().main();
        Runnable runnable2 = () -> new DoSomething().main();
        Runnable runnable3 = () -> new DoSomething().main();
        Runnable runnable4 = () -> new DoSomething().main();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<?> submit = executorService.submit(runnable);
        Future<?> submit2 = executorService.submit(runnable2);
        Future<?> submit3 = executorService.submit(runnable3);
        Future<?> submit4 = executorService.submit(runnable4);
        submit.get();
        submit4.get();
        submit2.get();
        submit3.get();
        executorService.shutdown();

    }

}