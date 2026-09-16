import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class M1802P01_LongestConsecutive {

    static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        int best = 0;
        // loop over the set (not the array) so duplicates are not walked again
        for (int x : set) {
            // only start counting at the first number of a run
            if (x != Integer.MIN_VALUE && set.contains(x - 1)) {
                continue;
            }
            int length = 1;
            int current = x;
            // the MAX_VALUE check stops current + 1 from wrapping around to MIN_VALUE
            while (current != Integer.MAX_VALUE && set.contains(current + 1)) {
                current++;
                length++;
            }
            best = Math.max(best, length);
        }
        return best;
    }

    // slow answer used to cross-check: sort, then count runs
    static int bySorting(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] a = nums.clone();
        Arrays.sort(a);
        int best = 1;
        int run = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1]) {
                continue;
            }
            run = (a[i] == a[i - 1] + 1) ? run + 1 : 1;
            best = Math.max(best, run);
        }
        return best;
    }

    static void test(int[] nums, int expected) {
        int got = longestConsecutive(nums);
        System.out.println(Arrays.toString(nums) + " -> " + got + "  " + (got == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[]{100, 4, 200, 1, 3, 2}, 4);
        test(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9);
        test(new int[]{1, 2, 0, 1}, 3);
        test(new int[]{}, 0);
        test(new int[]{5}, 1);
        test(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}, 1);

        Random rnd = new Random(5);
        boolean match = true;
        for (int t = 0; t < 500; t++) {
            int[] nums = new int[rnd.nextInt(60)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = rnd.nextInt(100) - 50;
            }
            match &= longestConsecutive(nums) == bySorting(nums);
        }
        System.out.println("random 500 cases -> " + (match ? "match" : "differ") + "  " + (match ? "PASS" : "FAIL"));
    }
}
