import java.util.Arrays;

public class M06L08_ArraysClass {

    public static void main(String[] args) {
        int[] numbers = {5, 3, 9, 1, 7};

        // printing the array itself shows a code like [I@1b6d3586; toString shows the values
        System.out.println("toString = " + Arrays.toString(numbers));

        // sort changes the array itself, smallest first
        Arrays.sort(numbers);
        System.out.println("sorted = " + Arrays.toString(numbers));

        // sort(array, from, to) sorts only indexes from..to-1
        int[] part = {9, 8, 7, 6, 5, 4};
        Arrays.sort(part, 1, 4);
        System.out.println("part sorted = " + Arrays.toString(part));

        // capital letters come before small letters
        String[] words = {"pear", "Apple", "banana", "apple"};
        Arrays.sort(words);
        System.out.println("words = " + Arrays.toString(words));

        // -0.0 comes before 0.0 and NaN goes last
        double[] special = {Double.NaN, 1.5, 0.0, -0.0};
        Arrays.sort(special);
        System.out.println("special = " + Arrays.toString(special));

        // fill puts the same value in every slot, or only in from..to-1
        int[] marks = new int[6];
        Arrays.fill(marks, -1);
        Arrays.fill(marks, 2, 5, 9);
        System.out.println("marks = " + Arrays.toString(marks));

        // a.equals(b) only checks the reference; Arrays.equals compares values
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("Arrays.equals(a, b): " + Arrays.equals(a, b));
        System.out.println("same Arrays.hashCode: " + (Arrays.hashCode(a) == Arrays.hashCode(b)));

        // mismatch gives the first index that differs, or -1
        System.out.println("mismatch = " + Arrays.mismatch(a, new int[] {1, 2, 4}));

        // compare works like dictionary order: negative, zero or positive
        System.out.println("compare = " + Arrays.compare(new int[] {2}, new int[] {1, 9, 9}));

        // binarySearch needs a sorted array and returns the index
        int[] sorted = {10, 20, 30, 40, 50};
        System.out.println("index of 30 = " + Arrays.binarySearch(sorted, 30));

        // a missing value gives -(insertion point) - 1
        int result = Arrays.binarySearch(sorted, 35);
        System.out.println("search 35 = " + result + ", insert at " + (-result - 1));

        // on an unsorted array the answer is meaningless
        System.out.println("unsorted search = " + Arrays.binarySearch(new int[] {30, 10, 20}, 30));
    }
}
