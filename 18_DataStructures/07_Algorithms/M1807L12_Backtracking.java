import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class M1807L12_Backtracking {

    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        buildSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    static void buildSubsets(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // every partial choice is itself a subset; save a copy, not the live list
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            // choose, explore, un-choose
            current.add(nums[i]);
            buildSubsets(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    static List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        buildPermutations(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    static void buildPermutations(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        // all values placed: one full ordering
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // used[] stops a value from appearing twice
            if (used[i]) {
                continue;
            }
            used[i] = true;
            current.add(nums[i]);
            buildPermutations(nums, used, current, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    static long factorial(int n) {
        long f = 1;
        for (int i = 2; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("subsets: " + subsets(nums));
        System.out.println("permutations: " + permutations(nums));

        boolean ok = true;
        for (int n = 0; n <= 6; n++) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = i;
            }
            // subsets: 2^n of them, all different, each matching a bitmask
            List<List<Integer>> subs = subsets(a);
            Set<List<Integer>> distinctSubs = new HashSet<>(subs);
            ok &= subs.size() == (1 << n) && distinctSubs.size() == subs.size();
            for (int mask = 0; mask < (1 << n); mask++) {
                List<Integer> fromMask = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) {
                        fromMask.add(i);
                    }
                }
                ok &= distinctSubs.contains(fromMask);
            }
            // permutations: n! of them, all different, each using every value once
            List<List<Integer>> perms = permutations(a);
            ok &= perms.size() == factorial(n) && new HashSet<>(perms).size() == perms.size();
            for (List<Integer> p : perms) {
                ok &= new HashSet<>(p).size() == n;
            }
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
