import java.util.Arrays;

public class M06L07_CopyingArrays {

    public static void main(String[] args) {
        int[] source = {1, 2, 3, 4, 5};

        // copy by hand with a loop
        int[] manual = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            manual[i] = source[i];
        }
        System.out.println("manual = " + Arrays.toString(manual));

        // clone makes a new array with the same values
        int[] cloned = source.clone();
        cloned[0] = -1;
        System.out.println("source = " + Arrays.toString(source));
        System.out.println("cloned = " + Arrays.toString(cloned));

        // arraycopy(src, srcPos, dest, destPos, count) fills an existing array
        int[] dest = new int[7];
        System.arraycopy(source, 1, dest, 2, 3);
        System.out.println("dest = " + Arrays.toString(dest));

        // arraycopy works inside one array even when the ranges overlap
        int[] shift = {1, 2, 3, 4, 5, 0};
        System.arraycopy(shift, 1, shift, 2, 4);
        System.out.println("shift = " + Arrays.toString(shift));

        // copyOf cuts the end or pads with default values
        System.out.println("copyOf 3 = " + Arrays.toString(Arrays.copyOf(source, 3)));
        System.out.println("copyOf 7 = " + Arrays.toString(Arrays.copyOf(source, 7)));

        // copyOfRange(from, to): from is included, to is not
        System.out.println("copyOfRange 1..4 = " + Arrays.toString(Arrays.copyOfRange(source, 1, 4)));

        // an array cannot grow: make a bigger copy and use the new one
        int[] list = {10, 20};
        list = Arrays.copyOf(list, list.length * 2);
        list[2] = 30;
        System.out.println("list = " + Arrays.toString(list));

        // a copied String[] holds the same String objects (a shallow copy)
        String[] names = {"Ann", "Bob"};
        String[] namesCopy = names.clone();
        System.out.println("same array: " + (names == namesCopy));
        System.out.println("same first String: " + (names[0] == namesCopy[0]));
    }
}
