package com.example.factorial.calculator.multithread;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MultithreadedCalculator extends AbstractChunkedMultithreadedCalculator {
    private final int numberOfThread;
    private final int workQueueSize;

    public MultithreadedCalculator(int numberOfThread, int workQueueSize) {
        this.numberOfThread = numberOfThread;
        this.workQueueSize = workQueueSize;
    }

    public MultithreadedCalculator() {
        this.numberOfThread = Runtime.getRuntime().availableProcessors();
        this.workQueueSize = numberOfThread;
    }

    @Override
    public BigInteger calculateFactorial(int number) {
        BigInteger factorial = BigInteger.valueOf(1);
        if (number / workQueueSize > 2) {
            ExecutorService executorService = Executors.newFixedThreadPool(this.numberOfThread);
            List<Future<BigInteger>> futures = this.splitToRanges(number, workQueueSize).stream()
                    .map(valueRange -> new AbstractChunkedMultithreadedCalculator.ChunkCalculator(valueRange.getFrom(), valueRange.getTo()))
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
