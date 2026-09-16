import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class M1802P02_SubarraySumK {

    static int countSubarrays(int[] nums, int k) {
        // prefix sum -> how many times we have seen it
        Map<Long, Integer> seen = new HashMap<>();
        // the empty prefix (sum 0) lets subarrays start at index 0
        seen.put(0L, 1);
        long prefix = 0;
        int count = 0;
        for (int x : nums) {
            prefix += x;
            // a subarray ending here sums to k when an earlier prefix equals prefix - k
            count += seen.getOrDefault(prefix - k, 0);
            // store after looking up, otherwise k = 0 would count an empty subarray
            seen.merge(prefix, 1, Integer::sum);
        }
        return count;
    }

    static int bruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            long sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    static void test(int[] nums, int k, int expected) {
        int got = countSubarrays(nums, k);
        System.out.println(Arrays.toString(nums) + " k=" + k + " -> " + got + "  " + (got == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[]{1, 1, 1}, 2, 2);
        test(new int[]{1, 2, 3}, 3, 2);
        test(new int[]{1, -1, 5, -2, 3}, 3, 3);
        test(new int[]{0, 0, 0}, 0, 6);
        test(new int[]{5}, 0, 0);
        test(new int[]{}, 0, 0);

        Random rnd = new Random(6);
        boolean match = true;
        for (int t = 0; t < 500; t++) {
            int[] nums = new int[rnd.nextInt(40)];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = rnd.nextInt(9) - 4;
            }
            int k = rnd.nextInt(9) - 4;
            match &= countSubarrays(nums, k) == bruteForce(nums, k);
        }
        System.out.println("random 500 cases -> " + (match ? "match" : "differ") + "  " + (match ? "PASS" : "FAIL"));
    }
}
