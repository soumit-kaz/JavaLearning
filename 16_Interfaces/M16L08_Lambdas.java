import java.io.IOException;

public class M16L08_Lambdas {

    @FunctionalInterface
    interface Calculator {
        int apply(int a, int b);
    }

    interface Action {
        String run();
    }

    interface Task {
        String run();
    }

    // our own functional interface may declare a checked exception
    @FunctionalInterface
    interface FileReader {
        String read(String path) throws IOException;
    }

    static int compute(Calculator calculator, int a, int b) {
        return calculator.apply(a, b);
    }

    private final String name = "outer";

    void thisDemo() {
        // inside a lambda, "this" is the surrounding object
        Action lambda = () -> this.name;

        // inside an anonymous class, "this" is the anonymous object
        Action anonymous = new Action() {
            private final String name = "anonymous";

            @Override
            public String run() {
                return this.name;
            }
        };
        System.out.println("lambda this: " + lambda.run());
        System.out.println("anonymous this: " + anonymous.run());
    }

    public static void main(String[] args) {
        // a lambda is a short way to implement a functional interface
        // read it as: "given a and b, return a * b"
        Calculator multiply = (a, b) -> a * b;
        System.out.println("multiply: " + compute(multiply, 3, 4));

        // a lambda can be passed straight into a method
        System.out.println("minus: " + compute((a, b) -> a - b, 3, 4));

        // types may be written out; one expression needs no braces or return
        Calculator max = (int a, int b) -> a > b ? a : b;
        System.out.println("max: " + max.apply(3, 4));

        // several statements need braces and an explicit return
        Calculator power = (base, exp) -> {
            int result = 1;
            for (int i = 0; i < exp; i++) {
                result *= base;
            }
            return result;
        };
        System.out.println("power: " + power.apply(2, 5));

        // no parameters: empty brackets
        Action hello = () -> "hello";
        System.out.println("hello: " + hello.run());

        new M16L08_Lambdas().thisDemo();

        // a lambda may use a local variable only if it never changes
        String greeting = "hi";
        Action sayHi = () -> greeting;
        // greeting = "bye"; here would make the lambda a compile error
        System.out.println("sayHi: " + sayHi.run());

        // same shape is not same type: Task task = sayHi; does not compile
        Task task = () -> sayHi.run();
        System.out.println("task: " + task.run());

        // Object o = () -> "x"; does not compile: the target must be a functional interface

        // the body may throw what the interface method declares
        FileReader reader = path -> {
            if (path.isEmpty()) {
                throw new IOException("empty path");
            }
            return "contents of " + path;
        };
        try {
            System.out.println("read: " + reader.read("notes.txt"));
            reader.read("");
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
