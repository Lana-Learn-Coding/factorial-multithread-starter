package com.example.factorial.calculator.multithread;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MultithreadedFixedWorkQueueSizeCalculator extends AbstractChunkedMultithreadedCalculator {
    private final int numberOfThread;

    public MultithreadedFixedWorkQueueSizeCalculator(int numberOfThread) {
        this.numberOfThread = numberOfThread;
    }

    public MultithreadedFixedWorkQueueSizeCalculator() {
        this.numberOfThread = Runtime.getRuntime().availableProcessors();
    }

    @Override
    public BigInteger calculateFactorial(int number) {
        BigInteger factorial = BigInteger.valueOf(1);
        if (number / numberOfThread > 2) {
            ExecutorService executorService = Executors.newFixedThreadPool(this.numberOfThread);
            List<Future<BigInteger>> futures = this.splitToRanges(number, numberOfThread).stream()
                    .map(valueRange -> new ChunkCalculator(valueRange.getFrom(), valueRange.getTo()))
                    .map(executorService::submit)
                    .collect(Collectors.toList());

            for (Future<BigInteger> future : futures) {
                try {
                    factorial = factorial.multiply(future.get());
                } catch (Exception e) {
                    throw new RuntimeException(e.getCause());
                }
            }

            executorService.shutdown();
            return factorial;
        }
        return this.calculateFactorialOnSingleThread(number);
    }
}

