import java.util.ArrayList;
import java.util.List;

public class M17L02_ArrayListPitfalls {

    public static void main(String[] args) {
        // collections hold objects, so write Integer, not int
        List<Integer> numbers = new ArrayList<>();
        // each int is boxed to an Integer automatically
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(1);

        // each Integer is unboxed back to an int automatically
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        System.out.println("total: " + total);

        // pitfall 1: remove(int) removes by INDEX, not by value
        numbers.remove(1);
        System.out.println("remove(1): " + numbers);

        // wrap the value in an Integer to remove by value
        numbers.remove(Integer.valueOf(1));
        System.out.println("remove(Integer 1): " + numbers);

        // pitfall 2: == compares references, so use equals for Integer
        Integer a = 1000;
        Integer b = 1000;
        System.out.println("a == b: " + (a == b));
        System.out.println("a.equals(b): " + a.equals(b));

        // pitfall 3: get checks bounds, so a bad index throws
        try {
            numbers.get(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("get(10): IndexOutOfBoundsException");
        }

        // pitfall 4: a null Integer cannot be unboxed to an int
        numbers.add(null);
        try {
            int last = numbers.get(numbers.size() - 1);
            System.out.println("last: " + last);
        } catch (NullPointerException e) {
            System.out.println("unbox null: NullPointerException");
        }
    }
}
