package com.example.factorial.calculator;

import java.math.BigInteger;

public class SingleThreadedCalculator implements FactorialCalculable {
    @Override
    public BigInteger calculateFactorial(int number) {
        BigInteger factorial = BigInteger.valueOf(1);
        for (int i = 2; i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
