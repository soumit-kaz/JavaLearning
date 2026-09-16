import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M17P02_TwoSum {

    static List<Integer> twoSum(int[] nums, int target) {
        // value -> index where we saw it
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer j = seen.get(target - nums[i]);
            // look up before storing, so a number never pairs with itself
            if (j != null) {
                return List.of(j, i);
            }
            seen.putIfAbsent(nums[i], i);
        }
        return List.of();
    }

    // print one PASS line, or stop with a FAIL line
    static void test(int[] nums, int target, List<Integer> expected) {
        List<Integer> got = twoSum(nums, target);
        String label = Arrays.toString(nums) + " target " + target + " -> " + got;
        if (!got.equals(expected)) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        test(new int[]{2, 7, 11, 15}, 9, List.of(0, 1));
        test(new int[]{3, 2, 4}, 6, List.of(1, 2));
        test(new int[]{3, 3}, 6, List.of(0, 1));
        test(new int[]{-1, -2, -3, -4, -5}, -8, List.of(2, 4));
        test(new int[]{1, 2, 3}, 100, List.of());
    }
}
