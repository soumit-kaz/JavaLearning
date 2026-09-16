import java.util.Arrays;
import java.util.Random;

public class M1807P01_SearchInsert {

    static int searchInsert(int[] nums, int target) {
        // high = length so "insert at the end" is a possible answer
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                // nums[mid] >= target, so mid could be the answer
                high = mid;
            }
        }
        return low;
    }

    static boolean allPassed = true;

    static void check(String label, boolean pass) {
        allPassed &= pass;
        System.out.println(label + " " + (pass ? "PASS" : "FAIL"));
    }

    static void test(int[] nums, int target, int expected) {
        int got = searchInsert(nums, target);
        check(Arrays.toString(nums) + " " + target + " -> " + got, got == expected);
    }

    public static void main(String[] args) {
        test(new int[]{1, 3, 5, 6}, 5, 2);
        test(new int[]{1, 3, 5, 6}, 2, 1);
        test(new int[]{1, 3, 5, 6}, 7, 4);
        test(new int[]{1, 3, 5, 6}, 0, 0);
        test(new int[]{}, 42, 0);
        test(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, Integer.MAX_VALUE, 2);

        // random sorted arrays compared with a linear scan
        Random random = new Random(1);
        boolean match = true;
        for (int t = 0; t < 500; t++) {
            int[] nums = new int[random.nextInt(40)];
            int value = -50;
            for (int i = 0; i < nums.length; i++) {
                value += 1 + random.nextInt(4);
                nums[i] = value;
            }
            int target = random.nextInt(200) - 60;
            int expected = 0;
            while (expected < nums.length && nums[expected] < target) {
                expected++;
            }
            match &= searchInsert(nums, target) == expected;
        }
        check("random 500", match);

        if (!allPassed) {
            System.exit(1);
        }
    }
}
