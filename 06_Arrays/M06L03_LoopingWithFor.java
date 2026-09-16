import java.util.Arrays;

public class M06L03_LoopingWithFor {

    public static void main(String[] args) {
        int[] numbers = {4, 8, 15, 16, 23, 42};

        // front to back: the condition is i < length
        System.out.print("forward: ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // back to front: start at length - 1 and stop after 0
        System.out.print("backward: ");
        for (int i = numbers.length - 1; i >= 0; i--) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // every second element
        System.out.print("even indexes: ");
        for (int i = 0; i < numbers.length; i += 2) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // the index lets you change elements
        int[] data = {1, 2, 3};
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i] * 10;
        }
        System.out.println("data = " + Arrays.toString(data));

        // fill an array from its index
        int[] squares = new int[5];
        for (int i = 0; i < squares.length; i++) {
            squares[i] = i * i;
        }
        System.out.println("squares = " + Arrays.toString(squares));

        // break stops early; i tells where
        int position = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > 15) {
                position = i;
                break;
            }
        }
        System.out.println("first > 15 at " + position);
    }
}
