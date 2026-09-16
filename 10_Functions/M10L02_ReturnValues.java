public class M10L02_ReturnValues {

    // int before the name: this method gives back an int
    static int add(int a, int b) {
        return a + b;
    }

    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    static String grade(int score) {
        // leave early when the input is invalid
        if (score < 0 || score > 100) {
            return "invalid";
        }
        if (score >= 80) {
            return "A";
        }
        if (score >= 60) {
            return "B";
        }
        // every path must end with a return
        return "F";
    }

    static void printIfPositive(int n) {
        // a plain return; exits a void method early
        if (n <= 0) {
            return;
        }
        System.out.println("positive " + n);
    }

    // a method returns one value, so pack two numbers into an array
    static int[] minAndMax(int[] values) {
        int min = values[0];
        int max = values[0];
        for (int v : values) {
            min = Math.min(min, v);
            max = Math.max(max, v);
        }
        return new int[] {min, max};
    }

    public static void main(String[] args) {
        // store the result in a variable
        int sum = add(2, 3);
        System.out.println("sum = " + sum);

        // use a result directly, even inside another call
        System.out.println("isEven(7) = " + isEven(7));
        System.out.println("total = " + add(add(1, 2), 10));

        // a returned value may also be ignored
        add(100, 200);

        System.out.println("grade(95) = " + grade(95));
        System.out.println("grade(150) = " + grade(150));
        printIfPositive(5);
        printIfPositive(-5);

        int[] result = minAndMax(new int[] {7, -3, 12, 5});
        System.out.println("min = " + result[0] + ", max = " + result[1]);
    }
}
