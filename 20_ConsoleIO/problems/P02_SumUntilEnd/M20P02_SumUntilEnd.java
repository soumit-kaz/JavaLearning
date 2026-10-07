import java.util.Scanner;

public class M20P02_SumUntilEnd {

    // add up one number per line, and stop at the word "end"
    // or when there is nothing left to read
    static int sum(Scanner in) {
        int total = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("end")) {
                break;
            }
            total += Integer.parseInt(line);
        }
        return total;
    }

    static void test(String input, int expected) {
        Scanner in = new Scanner(input);
        int got = sum(in);
        in.close();

        String label = "[" + input.replace("\n", "\\n") + "] -> " + got;
        if (got != expected) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        test("1\n2\n3\nend\n", 6);
        test("10\n20\n", 30);
        test("end\n", 0);
        test("", 0);
        test("5\nend\n100\n", 5);
        test("-4\n4\nend\n", 0);
    }
}
