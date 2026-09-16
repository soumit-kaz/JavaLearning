public class M04P06_SwitchCalculator {

    public static void main(String[] args) {
        int a;
        int b;
        char op;
        String expected;
        String result;

        // test 1
        a = 7;
        b = 3;
        op = '+';
        expected = "10";
        // pick the operation; check b == 0 before dividing, or the program crashes
        result = switch (op) {
            case '+' -> "" + (a + b);
            case '-' -> "" + (a - b);
            case '*' -> "" + (a * b);
            case '/' -> b == 0 ? "division by zero" : "" + (a / b);
            case '%' -> b == 0 ? "division by zero" : "" + (a % b);
            default -> "unknown operator";
        };
        System.out.println(a + " " + op + " " + b + " -> " + result + (result.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 2: integer division
        a = 7;
        b = 2;
        op = '/';
        expected = "3";
        result = switch (op) {
            case '+' -> "" + (a + b);
            case '-' -> "" + (a - b);
            case '*' -> "" + (a * b);
            case '/' -> b == 0 ? "division by zero" : "" + (a / b);
            case '%' -> b == 0 ? "division by zero" : "" + (a % b);
            default -> "unknown operator";
        };
        System.out.println(a + " " + op + " " + b + " -> " + result + (result.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 3: division by zero
        a = 5;
        b = 0;
        op = '/';
        expected = "division by zero";
        result = switch (op) {
            case '+' -> "" + (a + b);
            case '-' -> "" + (a - b);
            case '*' -> "" + (a * b);
            case '/' -> b == 0 ? "division by zero" : "" + (a / b);
            case '%' -> b == 0 ? "division by zero" : "" + (a % b);
            default -> "unknown operator";
        };
        System.out.println(a + " " + op + " " + b + " -> " + result + (result.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 4: unknown operator
        a = 5;
        b = 2;
        op = '^';
        expected = "unknown operator";
        result = switch (op) {
            case '+' -> "" + (a + b);
            case '-' -> "" + (a - b);
            case '*' -> "" + (a * b);
            case '/' -> b == 0 ? "division by zero" : "" + (a / b);
            case '%' -> b == 0 ? "division by zero" : "" + (a % b);
            default -> "unknown operator";
        };
        System.out.println(a + " " + op + " " + b + " -> " + result + (result.equals(expected) ? "  PASS" : "  FAIL (expected " + expected + ")"));
    }
}
