public class M10L09_TryCatchFinally {

    static int divide(int a, int b) {
        // dividing by zero throws ArithmeticException
        return a / b;
    }

    static void work(int value) {
        try {
            System.out.println("try " + value + " = " + (10 / value));
        } catch (ArithmeticException e) {
            System.out.println("catch " + e.getMessage());
        } finally {
            // finally runs whether or not an exception happened
            System.out.println("finally");
        }
    }

    static int valueIsSaved() {
        int x = 1;
        try {
            // the value 1 is saved here, before finally runs
            return x;
        } finally {
            // too late: this does not change the returned value
            x = 2;
        }
    }

    public static void main(String[] args) {
        // the exception jumps out of divide() into this catch
        try {
            System.out.println(divide(10, 0));
            System.out.println("never printed");
        } catch (ArithmeticException e) {
            System.out.println("error: " + e.getMessage());
        }

        // catch the more specific type first: NumberFormatException is an IllegalArgumentException
        String[] inputs = {"42", "4x2"};
        for (String input : inputs) {
            try {
                System.out.println("number = " + Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("not a number: " + input);
            } catch (IllegalArgumentException e) {
                System.out.println("bad argument");
            }
        }

        work(5);
        work(0);
        System.out.println("valueIsSaved = " + valueIsSaved());

        // the program keeps running after a caught exception
        System.out.println("done");
    }
}
