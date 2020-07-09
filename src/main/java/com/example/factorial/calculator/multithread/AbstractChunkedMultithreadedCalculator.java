package com.example.factorial.calculator.multithread;

import com.example.factorial.calculator.FactorialCalculable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class AbstractChunkedMultithreadedCalculator implements FactorialCalculable {
    protected static class ValueRange {
        private final int from;
        private final int to;

        ValueRange(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }
    }

    protected static class ChunkCalculator implements Callable<BigInteger> {

        private final int from;
        private final int to;

        ChunkCalculator(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public BigInteger call() {
            BigInteger factorial = BigInteger.valueOf(from);
            for (int i = (from + 1); i <= to; i++) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }
            return factorial;
        }
    }

    @Override
    public abstract BigInteger calculateFactorial(int number);

    protected List<ValueRange> splitToRanges(int number, int numberOfRange) {
        int rangeSize = (int) Math.ceil((double) number / numberOfRange);
        List<ValueRange> valueRanges = new ArrayList<>(numberOfRange);

        for (int i = 0; i < numberOfRange; i++) {
            if (i == numberOfRange - 1) {
                valueRanges.add(new ValueRange(rangeSize * i + 1, number));
            }
            valueRanges.add(new ValueRange(rangeSize * i + 1, rangeSize * (i + 1)));
        }
        return valueRanges;
    }

    protected BigInteger calculateFactorialOnSingleThread(int number) {
        BigInteger factorial = BigInteger.valueOf(1);
        for (int i = (1 + 1); i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
