public class M04P01_EvenOdd {

    public static void main(String[] args) {
        int n;
        String expected;
        String result;

        // test 1
        n = 7;
        expected = "odd";
        // a number is even when dividing by 2 leaves no remainder
        if (n % 2 == 0) {
            result = "even";
        } else {
            result = "odd";
        }
        System.out.println(n + " -> " + result + (result.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 2
        n = 12;
        expected = "even";
        if (n % 2 == 0) {
            result = "even";
        } else {
            result = "odd";
        }
        System.out.println(n + " -> " + result + (result.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 3: zero is even
        n = 0;
        expected = "even";
        if (n % 2 == 0) {
            result = "even";
        } else {
            result = "odd";
        }
        System.out.println(n + " -> " + result + (result.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 4: -3 % 2 is -1, so "== 1" would be wrong; "== 0" is safe
        n = -3;
        expected = "odd";
        if (n % 2 == 0) {
            result = "even";
        } else {
            result = "odd";
        }
        System.out.println(n + " -> " + result + (result.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));
    }
}
