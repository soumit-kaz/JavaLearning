import java.util.ArrayList;
import java.util.List;

public class M17L03_Generics {

    // T is a type parameter: the real type is chosen when a Box is created
    static class Box<T> {
        private T value;

        Box(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }

        void set(T value) {
            this.value = value;
        }
    }

    // <T> before the return type makes this a generic method
    static <T> T lastOrDefault(List<T> items, T fallback) {
        if (items.isEmpty()) {
            return fallback;
        }
        return items.get(items.size() - 1);
    }

    // bounded type: T must be Comparable, so compareTo is allowed
    static <T extends Comparable<T>> T max(List<T> items) {
        T best = items.get(0);
        for (T item : items) {
            if (item.compareTo(best) > 0) {
                best = item;
            }
        }
        return best;
    }

    // wildcard: accepts a List<Integer>, a List<Double>, and so on
    static double total(List<? extends Number> numbers) {
        double sum = 0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        Box<String> nameBox = new Box<>("Alice");
        // no cast needed: get already returns a String
        String name = nameBox.get();
        System.out.println("name: " + name);

        // nameBox.set(42) would not compile: 42 is not a String
        nameBox.set("Bob");
        System.out.println("name: " + nameBox.get());

        Box<Integer> scoreBox = new Box<>(95);
        System.out.println("score + 5: " + (scoreBox.get() + 5));

        // the compiler works out T from the arguments
        List<String> names = new ArrayList<>(List.of("ann", "bob"));
        System.out.println("last: " + lastOrDefault(names, "nobody"));
        names.clear();
        System.out.println("last of empty: " + lastOrDefault(names, "nobody"));

        System.out.println("max int: " + max(List.of(3, 9, 4)));
        System.out.println("max word: " + max(List.of("pear", "apple", "fig")));

        // a List<Integer> is NOT a List<Number>, so the wildcard is needed
        System.out.println("total ints: " + total(List.of(1, 2, 3)));
        System.out.println("total doubles: " + total(List.of(0.5, 0.25)));
    }
}
