import java.util.Arrays;
import java.util.Random;

public class M1807L04_MergeSort {

    static void mergeSort(int[] a) {
        // one shared buffer instead of a new array on every merge
        int[] buffer = new int[a.length];
        sort(a, buffer, 0, a.length);
    }

    // sorts the half-open range [low, high)
    static void sort(int[] a, int[] buffer, int low, int high) {
        if (high - low < 2) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(a, buffer, low, mid);
        sort(a, buffer, mid, high);
        // halves already in order: skip the merge (makes sorted input O(n))
        if (a[mid - 1] <= a[mid]) {
            return;
        }
        merge(a, buffer, low, mid, high);
    }

    static void merge(int[] a, int[] buffer, int low, int mid, int high) {
        int i = low;
        int j = mid;
        int k = low;
        while (i < mid && j < high) {
            // <= takes from the left half on ties, which keeps the sort stable
            if (a[i] <= a[j]) {
                buffer[k++] = a[i++];
            } else {
                buffer[k++] = a[j++];
            }
        }
        // copy whatever is left in either half
        while (i < mid) {
            buffer[k++] = a[i++];
        }
        while (j < high) {
            buffer[k++] = a[j++];
        }
        System.arraycopy(buffer, low, a, low, high - low);
    }

    // bottom-up version: merge runs of width 1, 2, 4, ... with no recursion
    static void mergeSortBottomUp(int[] a) {
        int n = a.length;
        int[] buffer = new int[n];
        for (int width = 1; width < n; width *= 2) {
            for (int low = 0; low < n - width; low += 2 * width) {
                int mid = low + width;
                int high = Math.min(low + 2 * width, n);
                merge(a, buffer, low, mid, high);
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(data);
        System.out.println("top-down:  " + Arrays.toString(data));

        int[] data2 = {5, -1, 5, 0, 2, -1};
        mergeSortBottomUp(data2);
        System.out.println("bottom-up: " + Arrays.toString(data2));

        boolean ok = true;
        // edge cases: empty, one element, all equal, reversed
        int[][] edges = {{}, {1}, {4, 4, 4}, {5, 4, 3, 2, 1}};
        for (int[] e : edges) {
            int[] expected = e.clone();
            Arrays.sort(expected);
            int[] a = e.clone();
            mergeSort(a);
            ok &= Arrays.equals(a, expected);
        }

        // random arrays compared with Arrays.sort
        Random random = new Random(15);
        for (int t = 0; t < 300; t++) {
            int[] a = new int[random.nextInt(80)];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(100) - 50;
            }
            int[] expected = a.clone();
            Arrays.sort(expected);
            int[] b = a.clone();
            mergeSort(a);
            mergeSortBottomUp(b);
            ok &= Arrays.equals(a, expected) && Arrays.equals(b, expected);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
