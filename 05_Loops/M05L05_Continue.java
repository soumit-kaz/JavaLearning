public class M05L05_Continue {

    public static void main(String[] args) {
        // continue skips the rest of this step and goes to the next one
        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // add only the odd numbers
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue;
            }
            sum += i;
        }
        System.out.println("odd sum = " + sum);

        // in a while loop, update the counter BEFORE continue
        int n = 0;
        while (n < 6) {
            n++;
            if (n == 2) {
                continue;
            }
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
