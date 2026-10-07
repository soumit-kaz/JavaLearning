import java.util.Scanner;

public class M20P01_Greeting {

    // read a name on the first line and an age on the second,
    // then build the greeting
    static String greet(Scanner in) {
        String name = in.nextLine();
        int age = Integer.parseInt(in.nextLine());
        return "Hello " + name + ", you are " + age;
    }

    // a Scanner can read a String, which makes testing easy.
    // The real program would use new Scanner(System.in).
    static void test(String input, String expected) {
        Scanner in = new Scanner(input);
        String got = greet(in);
        in.close();

        String label = "[" + input.replace("\n", "\\n") + "] -> " + got;
        if (!got.equals(expected)) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        test("Ann\n30\n", "Hello Ann, you are 30");
        test("Anna Smith\n7\n", "Hello Anna Smith, you are 7");
        test("Bob\n0\n", "Hello Bob, you are 0");
    }
}
