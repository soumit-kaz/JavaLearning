public class M04L02_IfElse {

    public static void main(String[] args) {
        // exactly one of the two blocks runs
        int number = 7;
        if (number % 2 == 0) {
            System.out.println(number + " is even");
        } else {
            System.out.println(number + " is odd");
        }

        // a variable can get its value from either branch
        String result;
        int score = 45;
        if (score >= 50) {
            result = "pass";
        } else {
            result = "fail";
        }
        System.out.println(result);

        // pick the bigger of two numbers
        int a = 4;
        int b = 9;
        int max;
        if (a > b) {
            max = a;
        } else {
            max = b;
        }
        System.out.println("max = " + max);
    }
}
