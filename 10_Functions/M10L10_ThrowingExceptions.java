public class M10L10_ThrowingExceptions {

    // a custom checked exception; "extends Exception" is explained in module 11
    @SuppressWarnings("serial")
    static class EmptyNameException extends Exception {
        EmptyNameException(String message) {
            super(message);
        }
    }

    // unchecked exception: throw it to reject bad input, no throws needed
    static double squareRoot(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("negative: " + x);
        }
        return Math.sqrt(x);
    }

    // checked exception: the method must declare it with throws
    static String loadFile(String name) throws EmptyNameException {
        if (name.isEmpty()) {
            throw new EmptyNameException("empty file name");
        }
        return "data of " + name;
    }

    // this method does not catch it either, so it also declares throws
    static String loadSettings(String name) throws EmptyNameException {
        return loadFile(name);
    }

    static int depth = 0;

    // no base case: every call adds a stack frame until memory runs out
    static void forever() {
        depth++;
        forever();
    }

    public static void main(String[] args) {
        System.out.println("sqrt(16) = " + squareRoot(16));
        try {
            squareRoot(-4);
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e.getMessage());
        }

        // someone must finally catch a checked exception
        try {
            System.out.println(loadSettings("app.cfg"));
            System.out.println(loadSettings(""));
        } catch (EmptyNameException e) {
            System.out.println("error: " + e.getMessage());
        }

        // StackOverflowError is an Error; catching it is only for this demo
        try {
            forever();
        } catch (StackOverflowError e) {
            // the exact depth depends on the JVM
            System.out.println("StackOverflowError, depth > 1000: " + (depth > 1000));
        }
    }
}
