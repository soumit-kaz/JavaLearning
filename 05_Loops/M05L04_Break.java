public class M05L04_Break {

    public static void main(String[] args) {
        // break leaves the loop at once
        for (int i = 1; i <= 10; i++) {
            if (i == 4) {
                break;
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // find the first number above 50 divisible by 7
        int found = -1;
        for (int n = 51; n <= 100; n++) {
            if (n % 7 == 0) {
                found = n;
                break;
            }
        }
        System.out.println("found = " + found);

        // while (true) runs until a break
        int power = 1;
        while (true) {
            power *= 3;
            if (power > 100) {
                break;
            }
        }
        System.out.println("power = " + power);

        // in nested loops, break only leaves the inner loop
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    break;
                }
                System.out.print(i + "-" + j + " ");
            }
        }
        System.out.println();
    }
}
