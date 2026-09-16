import java.util.Arrays;
import java.util.Random;

public class M1807L05_QuickSort {

    // a seeded Random keeps every run identical
    static final Random PIVOT_PICKER = new Random(42);

    static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    static void quickSort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int p = partition(a, low, high);
        quickSort(a, low, p - 1);
        quickSort(a, p + 1, high);
    }

    // Lomuto partition: returns the final index of the pivot
    static int partition(int[] a, int low, int high) {
        // a random pivot makes the O(n^2) worst case very unlikely, even on sorted input
        swap(a, low + PIVOT_PICKER.nextInt(high - low + 1), high);
        int pivot = a[high];
        // everything left of store is smaller than the pivot
        int store = low;
        for (int i = low; i < high; i++) {
            if (a[i] < pivot) {
                swap(a, i, store);
                store++;
            }
        }
        // put the pivot between the smaller and the bigger values
        swap(a, store, high);
        return store;
    }

    // three-way quicksort: values equal to the pivot are finished in one pass
    static void quickSort3Way(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = a[low + PIVOT_PICKER.nextInt(high - low + 1)];
        // [low, lt) < pivot, [lt, i) == pivot, (gt, high] > pivot
        int lt = low;
        int i = low;
        int gt = high;
        while (i <= gt) {
            if (a[i] < pivot) {
                swap(a, lt++, i++);
            } else if (a[i] > pivot) {
                // do not move i: the value swapped in is still unchecked
                swap(a, i, gt--);
            } else {
                i++;
            }
        }
        // the equal block is never touched again
        quickSort3Way(a, low, lt - 1);
        quickSort3Way(a, gt + 1, high);
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] data = {10, 80, 30, 90, 40, 50, 70};
        quickSort(data);
        System.out.println("lomuto: " + Arrays.toString(data));

        int[] dups = {3, 1, 3, 3, 2, 1, 3};
        quickSort3Way(dups, 0, dups.length - 1);
        System.out.println("3-way:  " + Arrays.toString(dups));

        // sorted input would be the worst case with a fixed last-element pivot
        int[] sortedInput = new int[5000];
        for (int i = 0; i < sortedInput.length; i++) {
            sortedInput[i] = i;
        }
        quickSort(sortedInput);
        boolean ok = sortedInput[0] == 0 && sortedInput[4999] == 4999;

        // many duplicates: 3-way handles 100000 equal values without deep recursion
        int[] same = new int[100_000];
        Arrays.fill(same, 7);
        quickSort3Way(same, 0, same.length - 1);
        ok &= same[0] == 7 && same[99_999] == 7;

        // random arrays with lots of repeats, compared with Arrays.sort
        Random random = new Random(5);
        for (int t = 0; t < 300; t++) {
            int[] a = new int[random.nextInt(60)];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(10) - 5;
            }
            int[] expected = a.clone();
            Arrays.sort(expected);
            int[] b = a.clone();
            quickSort(a);
            quickSort3Way(b, 0, b.length - 1);
            ok &= Arrays.equals(a, expected) && Arrays.equals(b, expected);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
