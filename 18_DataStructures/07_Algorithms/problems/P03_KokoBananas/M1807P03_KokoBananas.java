import java.util.Arrays;
import java.util.Random;

public class M1807P03_KokoBananas {

    // assumes hours >= piles.length, so an answer always exists
    static int minEatingSpeed(int[] piles, long hours) {
        // speed 1 is the slowest; the biggest pile is always fast enough
        int low = 1;
        int high = 1;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            // a faster speed never needs more hours, so "fits in time" flips only once
            if (hoursNeeded(piles, mid) <= hours) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    static long hoursNeeded(int[] piles, int speed) {
        long total = 0;
        for (int pile : piles) {
            // ceiling division in long, because pile + speed can overflow int
            total += (pile + (long) speed - 1) / speed;
        }
        return total;
    }

    static boolean allPassed = true;

    static void check(String label, boolean pass) {
        allPassed &= pass;
        System.out.println(label + " " + (pass ? "PASS" : "FAIL"));
    }

    static void test(int[] piles, long hours, int expected) {
        int got = minEatingSpeed(piles, hours);
        check(Arrays.toString(piles) + " h=" + hours + " -> " + got, got == expected);
    }

    public static void main(String[] args) {
        test(new int[]{3, 6, 7, 11}, 8, 4);
        test(new int[]{30, 11, 23, 4, 20}, 5, 30);
        test(new int[]{30, 11, 23, 4, 20}, 6, 23);
        test(new int[]{1}, 1, 1);
        test(new int[]{1_000_000_000}, 2, 500_000_000);
        test(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, 4, 1_073_741_824);

        // random cases compared with trying speeds 1, 2, 3, ...
        Random random = new Random(3);
        boolean match = true;
        for (int t = 0; t < 300; t++) {
            int[] piles = new int[1 + random.nextInt(8)];
            for (int i = 0; i < piles.length; i++) {
                piles[i] = 1 + random.nextInt(60);
            }
            long hours = piles.length + random.nextInt(40);
            int speed = 1;
            while (hoursNeeded(piles, speed) > hours) {
                speed++;
            }
            match &= minEatingSpeed(piles, hours) == speed;
        }
        check("random 300", match);

        if (!allPassed) {
            System.exit(1);
        }
    }
}
