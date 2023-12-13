package software.ulpgc.kata5;

import java.util.stream.IntStream;

public class FibonacciCommand implements Command{
    @Override
    public Output execute(Input input) {
        try {
            int number = Integer.parseInt(input.get(":number"));
            return isOutOfBound(number) ? outOfBoundOutput() : outputOf(number);
        }
        catch (NumberFormatException e) {
            return nanOutput();
        }
    }

    private Output nanOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 405;
            }

            @Override
            public String result() {
                return "Not A Number";
            }
        };
    }

    private Output outputOf(int number) {
        return new Output() {
            @Override
            public int responseCode() {
                return 200;
            }

            @Override
            public String result() {
                return String.valueOf(fibonacciOf(number));
            }
        };
    }

    private int fibonacciOf(int number) {
        int fib1 = 0;
        int fib2 = 1;

        for (int i = 2; i < number + 1; i++) {
            int temporalVar = fib2;
            fib2 += fib1;
            fib1 = temporalVar;
        }
        return fib2;
    }

    private Output outOfBoundOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 404;
            }

            @Override
            public String result() {
                return "Number out of bounds";
            }
        };
    }

    private boolean isOutOfBound(int number) {
        return number < 0 || number >= 30;
    }
}
