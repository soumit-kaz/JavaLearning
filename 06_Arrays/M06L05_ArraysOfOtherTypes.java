import java.util.Arrays;

public class M06L05_ArraysOfOtherTypes {

    public static void main(String[] args) {
        // every primitive type has its own array type
        long[] longs = {10_000_000_000L, 1L};
        byte[] bytes = {72, -1, 127};
        boolean[] flags = {true, false};
        System.out.println("longs = " + Arrays.toString(longs));
        System.out.println("bytes = " + Arrays.toString(bytes));
        System.out.println("flags = " + Arrays.toString(flags));

        // a String array can hold null; skip it before calling methods
        String[] fruits = {"apple", "banana", null};
        int letters = 0;
        for (String fruit : fruits) {
            if (fruit != null) {
                letters += fruit.length();
            }
        }
        System.out.println("letters = " + letters);

        // Integer[] starts with null, not 0
        System.out.println("new Integer[2] = " + Arrays.toString(new Integer[2]));

        // check for null before unboxing, or you get NullPointerException
        Integer[] boxed = {5, null, 7};
        int total = 0;
        for (Integer value : boxed) {
            if (value != null) {
                total += value;
            }
        }
        System.out.println("total = " + total);

        // int[] and Integer[] are different types; copy one element at a time
        int[] plain = {5, 6, 7};
        Integer[] wrapped = new Integer[plain.length];
        for (int i = 0; i < plain.length; i++) {
            wrapped[i] = plain[i];
        }
        System.out.println("wrapped = " + Arrays.toString(wrapped));

        // println prints a char[] as text
        char[] word = {'J', 'a', 'v', 'a'};
        System.out.println(word);

        // but "text" + charArray gives a code like [C@1b6d3586
        String joined = "word: " + word;
        System.out.println("starts with [C@: " + joined.startsWith("word: [C@"));

        // new String turns chars (or a part of them) into text
        System.out.println("word: " + new String(word));
        System.out.println("part: " + new String(word, 1, 2));

        // toCharArray gives a copy; sorting it leaves the String alone
        String fruit = "banana";
        char[] chars = fruit.toCharArray();
        Arrays.sort(chars);
        System.out.println("sorted = " + String.valueOf(chars) + ", fruit = " + fruit);
    }
}
