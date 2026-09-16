public class M05L08_LoopPatterns {

    public static void main(String[] args) {
        // a sum starts at 0, and lives OUTSIDE the loop
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += i;
        }
        System.out.println("sum = " + sum);

        // an average needs a double division at the end
        System.out.println("average = " + (double) sum / 10);

        // a product starts at 1; an int overflows after 12!
        int intFactorial = 1;
        long longFactorial = 1;
        for (int i = 1; i <= 13; i++) {
            intFactorial *= i;
            longFactorial *= i;
        }
        System.out.println("13! int = " + intFactorial + ", long = " + longFactorial);

        // % 10 gives the last digit, / 10 removes it
        int n = 40721;
        int count = 0;
        int digitSum = 0;
        while (n > 0) {
            digitSum += n % 10;
            count++;
            n /= 10;
        }
        System.out.println("digits = " + count + ", digit sum = " + digitSum);

        // reverse: append each last digit to the result
        int reversed = 0;
        n = 1200;
        while (n > 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        System.out.println("1200 reversed = " + reversed);

        // count the values that match a rule
        int divisors = 0;
        for (int d = 1; d <= 36; d++) {
            if (36 % d == 0) {
                divisors++;
            }
        }
        System.out.println("divisors of 36 = " + divisors);

        // min/max: start from the extremes and keep the best so far
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 8; i++) {
            // the values come from a formula, since arrays come later
            int value = (i * 37) % 11 - 5;
            System.out.print(value + " ");
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        System.out.println();
        System.out.println("min = " + min + ", max = " + max);
    }
}
