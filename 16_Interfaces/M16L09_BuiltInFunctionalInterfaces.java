import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class M16L09_BuiltInFunctionalInterfaces {

    // <T> is a placeholder for a type chosen later (generics)
    interface Box<T> {
        T get();
    }

    // here T becomes String, so get() returns a String
    static class NameBox implements Box<String> {
        @Override
        public String get() {
            return "Rina";
        }
    }

    // the caller decides the rule
    static int countMatches(int[] numbers, Predicate<Integer> rule) {
        int count = 0;
        for (int n : numbers) {
            if (rule.test(n)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Box<String> box = new NameBox();
        // no cast needed: the compiler knows get() gives a String
        System.out.println("box: " + box.get().toUpperCase());

        // java.util.function has ready-made generic functional interfaces
        // use wrapper types like Integer inside < >, not int

        // Supplier<T>: nothing in, a T out
        Supplier<String> today = () -> "Monday";
        // Consumer<T>: a T in, nothing out
        Consumer<String> printer = text -> System.out.println("consumer: " + text);
        // Predicate<T>: a T in, true or false out
        Predicate<Integer> isEven = n -> n % 2 == 0;
        // Function<T, R>: a T in, an R out
        Function<Integer, String> stars = n -> "*".repeat(n);
        // BiFunction<T, U, R>: two values in, an R out
        BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);
        // UnaryOperator<T>: a T in, a T out
        UnaryOperator<String> upper = s -> s.toUpperCase();

        printer.accept(today.get());
        System.out.println("isEven(4): " + isEven.test(4));
        System.out.println("stars(3): " + stars.apply(3));
        System.out.println("repeat: " + repeat.apply("ab", 3));
        System.out.println("upper: " + upper.apply("hi"));

        // default methods combine predicates: negate, and, or
        int[] numbers = {-4, -1, 0, 3, 8, 11};
        Predicate<Integer> isPositive = n -> n > 0;
        System.out.println("odd: " + countMatches(numbers, isEven.negate()));
        System.out.println("even and positive: " + countMatches(numbers, isEven.and(isPositive)));
        System.out.println("even or positive: " + countMatches(numbers, isEven.or(isPositive)));

        // andThen runs this function first; compose runs the other first
        Function<Integer, Integer> addOne = n -> n + 1;
        Function<Integer, Integer> square = n -> n * n;
        System.out.println("addOne.andThen(square): " + addOne.andThen(square).apply(4));
        System.out.println("addOne.compose(square): " + addOne.compose(square).apply(4));

        // these methods declare no checked exceptions, so a lambda that
        // throws one must catch it (or use your own interface)
    }
}
