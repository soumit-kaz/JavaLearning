import java.util.Scanner;

public class M20P03_Calculator {

    // read a number, a sign and a second number, each on its own line,
    // then answer with the whole sum as text
    static String calculate(Scanner in) {
        int a = Integer.parseInt(in.nextLine());
        String sign = in.nextLine();
        int b = Integer.parseInt(in.nextLine());

        if (sign.equals("+")) {
            return a + " + " + b + " = " + (a + b);
        }
        if (sign.equals("-")) {
            return a + " - " + b + " = " + (a - b);
        }
        if (sign.equals("*")) {
            return a + " * " + b + " = " + (a * b);
        }
        if (sign.equals("/")) {
            if (b == 0) {
                return "cannot divide by zero";
            }
            return a + " / " + b + " = " + (a / b);
        }
        return "unknown sign " + sign;
    }

    static void test(String input, String expected) {
        Scanner in = new Scanner(input);
        String got = calculate(in);
        in.close();

        String label = "[" + input.replace("\n", " ") + "] -> " + got;
        if (!got.equals(expected)) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        test("2\n+\n3\n", "2 + 3 = 5");
        test("10\n-\n4\n", "10 - 4 = 6");
        test("6\n*\n7\n", "6 * 7 = 42");
        test("9\n/\n2\n", "9 / 2 = 4");
        test("9\n/\n0\n", "cannot divide by zero");
        test("1\n?\n2\n", "unknown sign ?");
    }
}
