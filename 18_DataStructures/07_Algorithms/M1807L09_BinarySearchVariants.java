import java.util.Arrays;
import java.util.Random;

public class M1807L09_BinarySearchVariants {

    // first index with a[i] >= target, searched on [low, high)
    static int lowerBound(int[] a, int target) {
        int low = 0;
        int high = a.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < target) {
                low = mid + 1;
            } else {
                // mid could be the answer, so keep it in range
                high = mid;
            }
        }
        return low;
    }

    // first index with a[i] > target
    static int upperBound(int[] a, int target) {
        int low = 0;
        int high = a.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // search on the answer: largest r with r * r <= n
    static int intSqrt(int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            // mid is always above low, so low = mid makes progress (and nothing overflows)
            int mid = low + (high - low) / 2 + 1;
            // "fits" flips from true to false once; long avoids overflow
            if ((long) mid * mid <= n) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 2, 2, 5, 8};
        System.out.println("lower(2)=" + lowerBound(a, 2) + " upper(2)=" + upperBound(a, 2));
        System.out.println("count of 2 -> " + (upperBound(a, 2) - lowerBound(a, 2)));
        System.out.println("sqrt(99) -> " + intSqrt(99));

        boolean ok = lowerBound(a, 9) == 6 && intSqrt(Integer.MAX_VALUE) == 46340;

        // bounds compared with a linear scan on random sorted arrays
        Random random = new Random(9);
        for (int t = 0; t < 500; t++) {
            int[] s = new int[random.nextInt(30)];
            for (int i = 0; i < s.length; i++) {
                s[i] = random.nextInt(15);
            }
            Arrays.sort(s);
            int target = random.nextInt(17) - 1;
            int lower = 0;
            while (lower < s.length && s[lower] < target) {
                lower++;
            }
            int upper = lower;
            while (upper < s.length && s[upper] == target) {
                upper++;
            }
            ok &= lowerBound(s, target) == lower && upperBound(s, target) == upper;
        }

        // search on the answer compared with Math.sqrt
        for (int n = 0; n < 2000; n++) {
            ok &= intSqrt(n) == (int) Math.sqrt(n);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
