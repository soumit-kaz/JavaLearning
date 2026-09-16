import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class M17P01_ContainsDuplicate {

    static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int n : nums) {
            // add returns false if n was already in the set
            if (!seen.add(n)) {
                return true;
            }
        }
        return false;
    }

    // print one PASS line, or stop with a FAIL line
    static void test(int[] nums, boolean expected) {
        boolean got = containsDuplicate(nums);
        String label = Arrays.toString(nums) + " -> " + got;
        if (got != expected) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 1}, true);
        test(new int[]{1, 2, 3, 4}, false);
        test(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true);
        test(new int[]{}, false);
        test(new int[]{7}, false);
        test(new int[]{-1, 0, 1, -1}, true);
    }
}
