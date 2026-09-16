import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class M17L05_ImmutableLists {

    public static void main(String[] args) {
        // List.of makes a list that cannot change
        List<String> colors = List.of("red", "green");
        try {
            colors.add("blue");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.of add: UnsupportedOperationException");
        }

        // List.of does not allow null
        try {
            List.of("a", null);
        } catch (NullPointerException e) {
            System.out.println("List.of null: NullPointerException");
        }

        // copy into a new ArrayList to get a list you can change
        List<String> mutable = new ArrayList<>(colors);
        mutable.add("blue");
        System.out.println("mutable copy: " + mutable);

        // unmodifiableList is a read-only VIEW: changes to the original show through
        List<String> readOnly = Collections.unmodifiableList(mutable);
        mutable.add("pink");
        System.out.println("read-only view: " + readOnly);

        // Arrays.asList has a fixed size and is backed by the array
        String[] array = {"x", "y", "z"};
        List<String> fixed = Arrays.asList(array);
        // set works and writes through to the array
        fixed.set(0, "X");
        System.out.println("array: " + Arrays.toString(array));
        // add and remove are not allowed
        try {
            fixed.add("w");
        } catch (UnsupportedOperationException e) {
            System.out.println("asList add: UnsupportedOperationException");
        }

        // subList(from, to) includes from and excludes to
        List<Integer> numbers = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6));
        List<Integer> middle = numbers.subList(2, 5);
        System.out.println("subList: " + middle);

        // a subList is a view: changes go through to the original list
        middle.set(0, 99);
        System.out.println("after set: " + numbers);

        // clearing the view removes that range from the original
        middle.clear();
        System.out.println("after clear: " + numbers);

        // copy the view if it must live on its own
        List<Integer> copy = new ArrayList<>(numbers.subList(0, 2));
        numbers.clear();
        System.out.println("copy: " + copy);
    }
}
