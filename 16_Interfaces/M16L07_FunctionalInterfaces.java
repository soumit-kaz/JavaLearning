public class M16L07_FunctionalInterfaces {

    // a functional interface has exactly ONE abstract method
    // @FunctionalInterface asks the compiler to check that rule
    @FunctionalInterface
    interface Calculator {
        int apply(int a, int b);

        // default and static methods do not count toward the one
        default int applyTwice(int a, int b) {
            return apply(apply(a, b), b);
        }

        static Calculator adder() {
            return new Adder();
        }

        // methods that Object already has do not count either
        @Override
        String toString();
    }

    // a second abstract method here would be a compile error:
    // "Calculator is not a functional interface"

    static class Adder implements Calculator {
        @Override
        public int apply(int a, int b) {
            return a + b;
        }
    }

    // code that takes the interface lets the caller choose the behavior
    static int compute(Calculator calculator, int a, int b) {
        return calculator.apply(a, b);
    }

    public static void main(String[] args) {
        // behavior from a named class
        System.out.println("add: " + compute(Calculator.adder(), 3, 4));

        // behavior from an anonymous class
        Calculator multiply = new Calculator() {
            @Override
            public int apply(int a, int b) {
                return a * b;
            }
        };
        System.out.println("multiply: " + compute(multiply, 3, 4));
        System.out.println("applyTwice: " + multiply.applyTwice(3, 4));

        // Runnable (java.lang) is a built-in functional interface: void run()
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable: done");
            }
        };
        task.run();

        // one abstract method means a lambda can replace the anonymous class (next lesson)
    }
}
