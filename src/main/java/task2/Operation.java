package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Operation {
    private static Operation instance;
    private static final List<Long> numbers = new ArrayList<>();

    private Operation(){}

    public static Operation getInstance() {
        if (instance == null) {
            instance = new Operation();
        }
        return instance;
    }

    public void addToList() {
        numbers.add(new Random().nextLong());
    }

    public void sumNumbers() {
        long sum = 0L;
        for (Long value : numbers) {
            sum += value;
        }
        System.out.println(sum);
    }

    public void sumAndPrintSquareRoots() {
        long sum = 0L;
        long sumOfSquares = 0L;
        for (Long value : numbers) {
            sum += value*value;
            sumOfSquares = (sum + sum) - 2 * sum * sum;
        }

        if(sum > 0) {
            System.out.println("Square root is: " + Math.sqrt(sumOfSquares));
        }
    }
}
