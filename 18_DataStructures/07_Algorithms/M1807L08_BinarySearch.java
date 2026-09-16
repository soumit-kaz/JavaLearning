import java.util.Arrays;
import java.util.Random;

public class M1807L08_BinarySearch {

    // search the sorted range [low, high] inclusive; -1 if missing
    static int binarySearch(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            // overflow-safe middle
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // the same idea written recursively
    static int binarySearchRecursive(int[] a, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (a[mid] == target) {
            return mid;
        }
        if (a[mid] < target) {
            return binarySearchRecursive(a, target, mid + 1, high);
        }
        return binarySearchRecursive(a, target, low, mid - 1);
    }

    public static void main(String[] args) {
        int[] a = {2, 5, 8, 12, 16, 23, 38, 56};
        System.out.println("find 23 -> " + binarySearch(a, 23));
        System.out.println("find 7 -> " + binarySearchRecursive(a, 7, 0, a.length - 1));

        boolean ok = binarySearch(new int[0], 3) == -1 && binarySearch(a, 23) == 5;

        // random sorted arrays of distinct values, compared with Arrays.binarySearch
        Random random = new Random(8);
        for (int t = 0; t < 500; t++) {
            int[] s = new int[random.nextInt(40)];
            int value = -30;
            for (int i = 0; i < s.length; i++) {
                value += 1 + random.nextInt(3);
                s[i] = value;
            }
            int target = random.nextInt(140) - 40;
            int library = Arrays.binarySearch(s, target);
            // the library returns a negative number when the value is missing
            int expected = library >= 0 ? library : -1;
            ok &= binarySearch(s, target) == expected;
            ok &= binarySearchRecursive(s, target, 0, s.length - 1) == expected;
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
