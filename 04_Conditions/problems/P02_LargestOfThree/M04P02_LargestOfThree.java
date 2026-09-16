public class M04P02_LargestOfThree {

    public static void main(String[] args) {
        int a;
        int b;
        int c;
        int expected;
        int largest;

        // test 1
        a = 3;
        b = 9;
        c = 5;
        expected = 9;
        // start with a, then replace it whenever b or c is bigger
        largest = a;
        if (b > largest) {
            largest = b;
        }
        if (c > largest) {
            largest = c;
        }
        System.out.println(a + ", " + b + ", " + c + " -> " + largest + (largest == expected ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 2: equal values
        a = 7;
        b = 7;
        c = 2;
        expected = 7;
        largest = a;
        if (b > largest) {
            largest = b;
        }
        if (c > largest) {
            largest = c;
        }
        System.out.println(a + ", " + b + ", " + c + " -> " + largest + (largest == expected ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 3: all negative
        a = -4;
        b = -8;
        c = -1;
        expected = -1;
        largest = a;
        if (b > largest) {
            largest = b;
        }
        if (c > largest) {
            largest = c;
        }
        System.out.println(a + ", " + b + ", " + c + " -> " + largest + (largest == expected ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 4: the same answer with && conditions
        a = 10;
        b = 4;
        c = 10;
        expected = 10;
        if (a >= b && a >= c) {
            largest = a;
        } else if (b >= c) {
            largest = b;
        } else {
            largest = c;
        }
        System.out.println(a + ", " + b + ", " + c + " -> " + largest + (largest == expected ? "  PASS" : "  FAIL (expected " + expected + ")"));
    }
}
