import java.util.Arrays;

public class M10P02_PassByValueReverse {

    // changes the caller's array (same object)
    static void reverseInPlace(int[] a) {
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            left++;
            right--;
        }
    }

    // leaves the input alone and returns a new array
    static int[] reversedCopy(int[] a) {
        int[] copy = Arrays.copyOf(a, a.length);
        reverseInPlace(copy);
        return copy;
    }

    // broken on purpose: only the local reference changes
    static void brokenReverse(int[] a) {
        a = reversedCopy(a);
    }

    static boolean allPassed = true;

    // print one PASS or FAIL line and remember any failure
    static void check(String label, Object actual, String expected) {
        boolean ok = String.valueOf(actual).equals(expected);
        if (!ok) {
            allPassed = false;
        }
        System.out.println(label + " -> " + actual + " " + (ok ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        reverseInPlace(a);
        check("reverseInPlace [1, 2, 3, 4, 5]", Arrays.toString(a), "[5, 4, 3, 2, 1]");

        int[] empty = {};
        reverseInPlace(empty);
        check("reverseInPlace []", Arrays.toString(empty), "[]");

        int[] b = {7, 8, 9};
        int[] c = reversedCopy(b);
        check("reversedCopy [7, 8, 9]", Arrays.toString(c), "[9, 8, 7]");
        check("original after reversedCopy", Arrays.toString(b), "[7, 8, 9]");

        int[] d = {1, 2, 3};
        brokenReverse(d);
        check("brokenReverse [1, 2, 3]", Arrays.toString(d), "[1, 2, 3]");
        if (!allPassed) {
            System.exit(1);
        }
    }
}
