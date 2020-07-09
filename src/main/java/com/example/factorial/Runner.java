package com.example.factorial;

import com.example.factorial.calculator.SingleThreadedCalculator;
import com.example.factorial.calculator.multithread.MultithreadedCalculator;
import com.example.factorial.calculator.multithread.MultithreadedFixedWorkQueueSizeCalculator;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public static void singleThread() {
        new SingleThreadedCalculator().calculateFactorial(200000);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public static void multipleThread_singleThread() {
        new MultithreadedFixedWorkQueueSizeCalculator(1).calculateFactorial(200000);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public static void multipleThread_2Threads() {
        new MultithreadedFixedWorkQueueSizeCalculator(2).calculateFactorial(200000);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public static void multipleThread_4Threads() {
        new MultithreadedFixedWorkQueueSizeCalculator(4).calculateFactorial(200000);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public static void multipleThread_10Threads() {
        new MultithreadedFixedWorkQueueSizeCalculator(10).calculateFactorial(200000);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public static void multipleThread_20Threads() {
        new MultithreadedFixedWorkQueueSizeCalculator(20).calculateFactorial(200000);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public static void multipleThread_4Threads_20Chunks() {
        new MultithreadedCalculator(4, 20).calculateFactorial(200000);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public static void multipleThread_4Threads_50Chunks() {
        new MultithreadedCalculator(4, 50).calculateFactorial(200000);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public static void multipleThread_4Threads_100Chunks() {
        new MultithreadedCalculator(4, 100).calculateFactorial(200000);
    }
}
