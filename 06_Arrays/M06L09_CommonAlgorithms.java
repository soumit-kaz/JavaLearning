import java.util.Arrays;

public class M06L09_CommonAlgorithms {

    public static void main(String[] args) {
        int[] values = {7, -3, 12, 0, 12, 5};

        // sum: add every element to a running total
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        // cast to double, otherwise integer division drops the fraction
        double average = (double) sum / values.length;
        System.out.println("sum = " + sum + ", average = " + average);

        // min and max: start from the first element, not from 0
        int min = values[0];
        int max = values[0];
        for (int value : values) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
        System.out.println("min = " + min + ", max = " + max);

        // linear search: check each element; -1 means not found
        int found = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == 12) {
                found = i;
                break;
            }
        }
        System.out.println("first 12 at " + found);

        // reverse in place: swap the ends and move toward the middle
        int[] numbers = {1, 2, 3, 4, 5};
        for (int left = 0, right = numbers.length - 1; left < right; left++, right--) {
            int temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;
        }
        System.out.println("reversed = " + Arrays.toString(numbers));

        // counting: counts[v] holds how many times v appears
        int[] rolls = {3, 1, 4, 1, 5, 6, 5, 5};
        int[] counts = new int[7];
        for (int roll : rolls) {
            counts[roll]++;
        }
        System.out.println("counts = " + Arrays.toString(counts));

        // insert at index 1: the array has spare room, size counts the used slots
        int[] items = {10, 20, 30, 0, 0};
        int size = 3;
        // shift the tail right, starting from the end
        for (int i = size; i > 1; i--) {
            items[i] = items[i - 1];
        }
        items[1] = 15;
        size++;
        System.out.println("after insert = " + Arrays.toString(Arrays.copyOf(items, size)));

        // delete index 2: shift the tail left, starting from the front
        for (int i = 2; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        size--;
        System.out.println("after delete = " + Arrays.toString(Arrays.copyOf(items, size)));
    }
}
