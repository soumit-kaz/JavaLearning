public class M06L02_ArrayLength {

    public static void main(String[] args) {
        int[] numbers = {4, 8, 15, 16, 23, 42};

        // length is a field, so it has no ()
        System.out.println("length = " + numbers.length);

        // indexes go from 0 to length - 1
        System.out.println("first = " + numbers[0]);
        System.out.println("last = " + numbers[numbers.length - 1]);

        // the size can be a variable, but it is fixed once the array exists
        int size = 5;
        int[] slots = new int[size];
        size = 10;
        System.out.println("slots.length = " + slots.length);

        // an empty array is allowed
        int[] empty = {};
        System.out.println("empty.length = " + empty.length);

        // a String uses length() with (), an array uses length without
        String word = "hello";
        System.out.println("word.length() = " + word.length());
    }
}
