public class M03L01_ArithmeticOperators {

    public static void main(String[] args) {
        int a = 17;
        int b = 5;

        // the five arithmetic operators
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("a % b = " + (a % b));

        // unary minus flips the sign
        System.out.println("-a = " + (-a));

        // int / int cuts toward zero, also for negative numbers
        System.out.println("-7 / 2 = " + (-7 / 2));

        // one double side makes the result a double
        System.out.println("7 / 2.0 = " + (7 / 2.0));

        // order matters: 1 / 2 is already 0
        System.out.println("1 / 2 * 10.0 = " + (1 / 2 * 10.0));
        System.out.println("10.0 * 1 / 2 = " + (10.0 * 1 / 2));

        // * / % come before + -, and equal levels go left to right
        System.out.println("2 + 3 * 4 = " + (2 + 3 * 4));
        System.out.println("10 - 4 - 3 = " + (10 - 4 - 3));

        // a cast binds tighter than *
        double d = 7.9;
        System.out.println("(int) d * 2 = " + ((int) d * 2));
        System.out.println("(int) (d * 2) = " + (int) (d * 2));

        // % takes the sign of the LEFT number
        System.out.println("-9 % 2 = " + (-9 % 2));
        System.out.println("9 % -2 = " + (9 % -2));

        // % 10 gives the last digit, / 10 removes it
        int number = 4729;
        System.out.println("last digit = " + (number % 10));
        System.out.println("rest = " + (number / 10));

        // % also works with decimals
        System.out.println("5.5 % 2 = " + (5.5 % 2));

        // a double divided by zero gives Infinity or NaN
        double zero = 0.0;
        System.out.println("1.0 / 0.0 = " + (1.0 / zero));
        System.out.println("5.0 % 0.0 = " + (5.0 % zero));

        // an int divided by the int 0 stops the program with ArithmeticException
    }
}
