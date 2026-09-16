import java.util.Arrays;

public class M06L06_ArrayReferences {

    public static void main(String[] args) {
        // an array variable holds a reference (an arrow) to the array
        int[] original = {1, 2, 3};

        // b = a copies the arrow, so both names share one array
        int[] alias = original;
        alias[0] = 99;
        System.out.println("original = " + Arrays.toString(original));

        // == checks whether two names point to the same array
        System.out.println("original == alias: " + (original == alias));

        // two arrays with equal values are still different arrays
        int[] twin = {99, 2, 3};
        System.out.println("original == twin: " + (original == twin));

        // pointing alias to a new array does not touch original
        alias = new int[] {7, 7, 7};
        System.out.println("original = " + Arrays.toString(original));
        System.out.println("alias = " + Arrays.toString(alias));

        // null means the variable points to no array
        int[] none = null;
        System.out.println("none == null: " + (none == null));
    }
}
