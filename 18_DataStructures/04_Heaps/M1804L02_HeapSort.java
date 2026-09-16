import java.util.Arrays;
import java.util.Random;

public class M1804L02_HeapSort {

    // in place: build a max-heap, then move the max to the end one by one
    static void heapSort(int[] a) {
        // heapify: sift down every parent, last one first
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            siftDown(a, i, a.length);
        }
        for (int end = a.length - 1; end > 0; end--) {
            // the max goes to its final place; the heap shrinks by one
            swap(a, 0, end);
            siftDown(a, 0, end);
        }
    }

    // max-heap sift down inside a[0..n-1]
    static void siftDown(int[] a, int i, int n) {
        while (2 * i + 1 < n) {
            int child = 2 * i + 1;
            if (child + 1 < n && a[child + 1] > a[child]) {
                child++;
            }
            if (a[child] <= a[i]) {
                return;
            }
            swap(a, i, child);
            i = child;
        }
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = {4, 10, 3, 5, 1, 8, 7, 2, 2};
        heapSort(arr);
        System.out.println("sorted: " + Arrays.toString(arr));

        boolean ok = Arrays.equals(arr, new int[]{1, 2, 2, 3, 4, 5, 7, 8, 10});
        // random arrays compared with Arrays.sort
        Random rnd = new Random(1);
        for (int t = 0; t < 500; t++) {
            int[] a = new int[rnd.nextInt(40)];
            for (int i = 0; i < a.length; i++) {
                a[i] = rnd.nextInt(200) - 100;
            }
            int[] expected = a.clone();
            Arrays.sort(expected);
            heapSort(a);
            ok &= Arrays.equals(a, expected);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
