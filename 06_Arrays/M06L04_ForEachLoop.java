import java.util.Arrays;

public class M06L04_ForEachLoop {

    public static void main(String[] args) {
        // for (type name : array) visits every element, front to back
        int[] numbers = {4, 8, 15};
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        System.out.println("sum = " + sum);

        // it works for any element type: String
        String[] words = {"alpha", "beta", "gamma"};
        int letters = 0;
        for (String word : words) {
            letters += word.length();
        }
        System.out.println("letters = " + letters);

        // char
        char[] grade = {'A', 'B', 'A', 'C'};
        int countA = 0;
        for (char c : grade) {
            if (c == 'A') {
                countA++;
            }
        }
        System.out.println("A count = " + countA);

        // break and continue work here too
        for (String word : words) {
            if (word.equals("gamma")) {
                break;
            }
            System.out.println("word = " + word);
        }

        // read-only: n is a copy, so the array does not change
        for (int n : numbers) {
            n = n * 10;
        }
        System.out.println("after for-each = " + Arrays.toString(numbers));

        // need to change elements or know the position? use an index loop
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i] * 10;
        }
        System.out.println("after index loop = " + Arrays.toString(numbers));

        // for-each has no index, so count yourself or use a for loop
        int index = 0;
        for (String word : words) {
            System.out.println(index + ": " + word);
            index++;
        }
    }
}
