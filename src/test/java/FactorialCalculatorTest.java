import com.example.factorial.calculator.SingleThreadedCalculator;
import com.example.factorial.calculator.multithread.MultithreadedFixedWorkQueueSizeCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialCalculatorTest {
    @Test
    public void singleThreadedCalculatorTest() {
        assertEquals(new BigInteger("2432902008176640000"),
                new SingleThreadedCalculator().calculateFactorial(20));
    }

    @Test
    public void multithreadedFixedWorkQueueSizeCalculatorTest() {
        assertEquals(new BigInteger("2432902008176640000"),
                new MultithreadedFixedWorkQueueSizeCalculator().calculateFactorial(20));
    }

    @Test
    public void multithreadedCalculatorTest() {
        assertEquals(new BigInteger("2432902008176640000"),
                new MultithreadedFixedWorkQueueSizeCalculator().calculateFactorial(20));
    }
}
