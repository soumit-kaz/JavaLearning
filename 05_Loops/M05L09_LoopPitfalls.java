public class M05L09_LoopPitfalls {

    // the stray semicolon below is on purpose, so its compiler warning is switched off
    @SuppressWarnings("empty")
    public static void main(String[] args) {
        // off-by-one: < 5 runs 5 times from 0, <= 5 runs 6 times
        int runs = 0;
        for (int i = 0; i < 5; i++) {
            runs++;
        }
        System.out.println("0 to < 5: " + runs);
        runs = 0;
        for (int i = 0; i <= 5; i++) {
            runs++;
        }
        System.out.println("0 to <= 5: " + runs);

        // the last index of a String is length() - 1
        String word = "loop";
        System.out.println("last char = " + word.charAt(word.length() - 1));

        // a semicolon after the header makes an EMPTY body
        int i;
        for (i = 0; i < 3; i++);
        {
            // this block runs only once, after the loop
            System.out.println("i = " + i);
        }

        // 0.1 is not exact, so x never becomes exactly 1.0
        int steps = 0;
        for (double x = 0.0; x != 1.0; x += 0.1) {
            steps++;
            // a safety stop, or this loop would never end
            if (steps == 20) {
                System.out.println("stopped, x = " + x);
                break;
            }
        }

        // better: count with an int and compute the double from it
        for (int k = 0; k <= 10; k += 5) {
            System.out.println("x = " + k / 10.0);
        }

        // a counter that jumps over the end never stops by itself
        int safety = 0;
        for (int k = 0; k != 10; k += 3) {
            safety++;
            if (safety > 20) {
                System.out.println("stopped at k = " + k);
                break;
            }
        }

        // bug: starting max at 0 fails when all values are negative
        int buggyMax = 0;
        for (int v = -3; v <= -1; v++) {
            if (v > buggyMax) {
                buggyMax = v;
            }
        }
        System.out.println("buggyMax = " + buggyMax);
    }
}
