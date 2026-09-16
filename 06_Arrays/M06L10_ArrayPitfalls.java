import java.util.Arrays;

public class M06L10_ArrayPitfalls {

    public static void main(String[] args) {
        int[] numbers = {10, 20, 30};

        // try/catch keeps the program running; exceptions get more detail in 10
        // a bad index fails only when the program runs
        try {
            System.out.println(numbers[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("bad index: " + e.getMessage());
        }

        // <= instead of < goes one step too far
        int sum = 0;
        try {
            for (int i = 0; i <= numbers.length; i++) {
                sum += numbers[i];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("sum before crash = " + sum);
        }

        // a negative size compiles but fails when it runs
        int size = -5;
        try {
            int[] bad = new int[size];
            System.out.println(bad.length);
        } catch (NegativeArraySizeException e) {
            System.out.println("negative size: " + e.getMessage());
        }

        // a null variable has no array behind it
        int[] missing = null;
        try {
            System.out.println(missing[0]);
        } catch (NullPointerException e) {
            System.out.println("null array: NullPointerException");
        }

        // starting max at 0 is wrong when every value is negative
        int[] negatives = {-5, -2, -9};
        int wrongMax = 0;
        int rightMax = negatives[0];
        for (int value : negatives) {
            wrongMax = Math.max(wrongMax, value);
            rightMax = Math.max(rightMax, value);
        }
        System.out.println("wrongMax = " + wrongMax + ", rightMax = " + rightMax);

        // an int total can overflow; use long
        int[] big = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int intSum = 0;
        long longSum = 0;
        for (int value : big) {
            intSum += value;
            longSum += value;
        }
        System.out.println("intSum = " + intSum + ", longSum = " + longSum);

        // swapping over the whole array reverses it twice, so nothing changes
        int[] twice = {1, 2, 3, 4};
        for (int i = 0; i < twice.length; i++) {
            int j = twice.length - 1 - i;
            int temp = twice[i];
            twice[i] = twice[j];
            twice[j] = temp;
        }
        System.out.println("swapped twice = " + Arrays.toString(twice));
    }
}
