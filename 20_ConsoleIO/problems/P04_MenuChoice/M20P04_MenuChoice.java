import java.util.Scanner;

public class M20P04_MenuChoice {

    // keep reading lines until one of them is a number from 1 to 3.
    // Answer 0 when the input runs out before that happens.
    static int readChoice(Scanner in) {
        while (in.hasNextLine()) {
            String line = in.nextLine().strip();
            try {
                int choice = Integer.parseInt(line);
                if (choice >= 1 && choice <= 3) {
                    return choice;
                }
                System.out.println("  " + choice + " is not between 1 and 3");
            } catch (NumberFormatException e) {
                System.out.println("  [" + line + "] is not a number");
            }
        }
        return 0;
    }

    static void test(String input, int expected) {
        int got;
        try (Scanner in = new Scanner(input)) {
            got = readChoice(in);
        }

        String label = "[" + input.replace("\n", "\\n") + "] -> " + got;
        if (got != expected) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        test("2\n", 2);
        test("hello\n3\n", 3);
        test("9\n0\n1\n", 1);
        test(" 2 \n", 2);
        test("hello\n", 0);
        test("", 0);
    }
}
