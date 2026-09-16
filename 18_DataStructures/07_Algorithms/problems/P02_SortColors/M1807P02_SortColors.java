import java.util.Arrays;
import java.util.Random;

public class M1807P02_SortColors {

    static void sortColors(int[] nums) {
        // [0, low) holds 0s, [low, mid) holds 1s, (high, end] holds 2s
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                // the value swapped back from low is a known 1, so mid moves on
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                // do not move mid: the value swapped in from high is unchecked
                swap(nums, mid, high);
                high--;
            }
        }
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static boolean allPassed = true;

    static void check(String label, boolean pass) {
        allPassed &= pass;
        System.out.println(label + " " + (pass ? "PASS" : "FAIL"));
    }

    static void test(int[] nums, int[] expected) {
        String input = Arrays.toString(nums);
        sortColors(nums);
        check(input + " -> " + Arrays.toString(nums), Arrays.equals(nums, expected));
    }

    public static void main(String[] args) {
        test(new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2});
        test(new int[]{2, 0, 1}, new int[]{0, 1, 2});
        test(new int[]{}, new int[]{});
        test(new int[]{1}, new int[]{1});
        test(new int[]{2, 2, 2}, new int[]{2, 2, 2});

        // random arrays compared with Arrays.sort
        Random random = new Random(2);
        boolean match = true;
        for (int t = 0; t < 1000; t++) {
            int[] nums = new int[random.nextInt(50)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(3);
            }
            int[] expected = nums.clone();
            Arrays.sort(expected);
            sortColors(nums);
            match &= Arrays.equals(nums, expected);
        }
        check("random 1000", match);

        if (!allPassed) {
            System.exit(1);
        }
    }
}
