public class M04P05_TriangleType {

    public static void main(String[] args) {
        int a;
        int b;
        int c;
        String expected;
        String type;

        // test 1
        a = 3;
        b = 4;
        c = 5;
        expected = "scalene";
        // every side must be positive and shorter than the other two together
        if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
            type = "invalid";
        } else if (a == b && b == c) {
            type = "equilateral";
        } else if (a == b || b == c || a == c) {
            type = "isosceles";
        } else {
            type = "scalene";
        }
        System.out.println(a + ", " + b + ", " + c + " -> " + type + (type.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 2
        a = 5;
        b = 5;
        c = 8;
        expected = "isosceles";
        if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
            type = "invalid";
        } else if (a == b && b == c) {
            type = "equilateral";
        } else if (a == b || b == c || a == c) {
            type = "isosceles";
        } else {
            type = "scalene";
        }
        System.out.println(a + ", " + b + ", " + c + " -> " + type + (type.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 3
        a = 7;
        b = 7;
        c = 7;
        expected = "equilateral";
        if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
            type = "invalid";
        } else if (a == b && b == c) {
            type = "equilateral";
        } else if (a == b || b == c || a == c) {
            type = "isosceles";
        } else {
            type = "scalene";
        }
        System.out.println(a + ", " + b + ", " + c + " -> " + type + (type.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 4: 1 + 2 is not more than 3, so these sides lie flat
        a = 1;
        b = 2;
        c = 3;
        expected = "invalid";
        if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
            type = "invalid";
        } else if (a == b && b == c) {
            type = "equilateral";
        } else if (a == b || b == c || a == c) {
            type = "isosceles";
        } else {
            type = "scalene";
        }
        System.out.println(a + ", " + b + ", " + c + " -> " + type + (type.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));
    }
}
