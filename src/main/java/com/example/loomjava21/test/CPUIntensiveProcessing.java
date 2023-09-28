package com.example.loomjava21.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CPUIntensiveProcessing {
    public static void main(String[] args) throws InterruptedException {

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 200; i++) {
                int threadNumber = i;
                executorService.submit(() -> {
                    if (threadNumber < 8) {
                        double result = CPUIntensiveCalculation();
                        System.out.println("End: CPU Intensive Thread " + threadNumber + " result " + result);
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("End: sleep Thread " + threadNumber);
                    }

                });
            }
        }
        Thread.sleep(100000);  //executorService.shutdown();
    }

    private static double CPUIntensiveCalculation() {
        double result = 0;
        for (int j = 0; j < 10000; j++) {
            for (int k = 0; k < 10000; k++) {
                result += Math.pow(k, j);
            }
        }
        return result;
    }
}
