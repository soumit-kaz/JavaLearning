public class M01L05_PrintingNumbers {

    public static void main(String[] args) {
        // numbers have no quotes; with quotes it is text
        System.out.println(42);
        System.out.println(3.14);
        System.out.println("42");

        // true/false and a single character in single quotes
        System.out.println(true);
        System.out.println('A');

        // Java calculates first, then prints
        System.out.println(6 * 7);

        // dividing two whole numbers drops the decimal part
        System.out.println(7 / 2);
        System.out.println(7.0 / 2);

        // % gives the remainder of a division
        System.out.println(7 % 2);

        // * and / happen before + and -; parentheses change the order
        System.out.println(2 + 3 * 4);
        System.out.println((2 + 3) * 4);

        // some decimal results are not exact
        System.out.println(0.1 + 0.2);

        // + joins text, and a number after text becomes text
        System.out.println("Age: " + 36);

        // after text, + keeps joining, so 2 and 3 become "23"
        System.out.println("2 + 3 = " + 2 + 3);

        // parentheses make Java add the numbers first
        System.out.println("2 + 3 = " + (2 + 3));

        // numbers before the text are added first
        System.out.println(2 + 3 + " apples");

        // a long line can be split across several lines of code
        System.out.println("This text is joined "
                + "from two parts");
    }
}
