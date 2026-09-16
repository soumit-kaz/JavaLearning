import java.util.Arrays;
import java.util.Random;

public class M1807L10_TwoPointers {

    // sorted input: indexes of two values that add up to target, or null
    static int[] pairWithSum(int[] a, int target) {
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            // long avoids overflow when adding two big values
            long sum = (long) a[left] + a[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                // need a bigger sum: only moving left can give one
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

    // sorted input: keep one copy of each value in front, return the new length
    static int removeDuplicates(int[] a) {
        if (a.length == 0) {
            return 0;
        }
        // write points at the last kept value; read scans ahead
        int write = 0;
        for (int read = 1; read < a.length; read++) {
            if (a[read] != a[write]) {
                write++;
                a[write] = a[read];
            }
        }
        return write + 1;
    }

    // brute force: try every pair
    static boolean hasPairBrute(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] sorted = {1, 3, 4, 6, 8, 11};
        System.out.println("pair sum 10 -> " + Arrays.toString(pairWithSum(sorted, 10)));
        int[] dups = {1, 1, 2, 3, 3, 3, 7};
        int length = removeDuplicates(dups);
        System.out.println("unique -> " + Arrays.toString(Arrays.copyOf(dups, length)));

        boolean ok = pairWithSum(new int[0], 5) == null && removeDuplicates(new int[0]) == 0;

        // random sorted arrays compared with brute force
        Random random = new Random(10);
        for (int t = 0; t < 500; t++) {
            int[] a = new int[random.nextInt(25)];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(20);
            }
            Arrays.sort(a);
            int target = random.nextInt(40);
            int[] pair = pairWithSum(a, target);
            ok &= (pair != null) == hasPairBrute(a, target);
            if (pair != null) {
                ok &= pair[0] < pair[1] && a[pair[0]] + a[pair[1]] == target;
            }

            int distinct = 0;
            for (int i = 0; i < a.length; i++) {
                if (i == 0 || a[i] != a[i - 1]) {
                    distinct++;
                }
            }
            int[] copy = a.clone();
            int n = removeDuplicates(copy);
            ok &= n == distinct;
            for (int i = 1; i < n; i++) {
                ok &= copy[i - 1] < copy[i];
            }
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
