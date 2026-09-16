public class M05L06_LabeledBreakContinue {

    public static void main(String[] args) {
        // a label names the outer loop; break outer leaves BOTH loops
        outer:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i * j == 4) {
                    break outer;
                }
                System.out.print(i + "*" + j + " ");
            }
        }
        System.out.println();

        // find the first pair that adds up to 7
        int first = 0;
        int second = 0;
        search:
        for (int a = 1; a <= 5; a++) {
            for (int b = a; b <= 5; b++) {
                if (a + b == 7) {
                    first = a;
                    second = b;
                    break search;
                }
            }
        }
        System.out.println("pair = " + first + " + " + second);

        // continue with a label jumps to the next step of the OUTER loop
        rows:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j > i) {
                    System.out.println();
                    continue rows;
                }
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
