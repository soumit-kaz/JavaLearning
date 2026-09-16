import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class M17L18_StreamsBasics {

    public static void main(String[] args) {
        List<String> names = List.of("ann", "bob", "carla", "dave", "eve");

        // the loop way: filter, transform, collect
        List<String> loopResult = new ArrayList<>();
        for (String name : names) {
            if (name.length() == 3) {
                loopResult.add(name.toUpperCase());
            }
        }
        System.out.println("loop: " + loopResult);

        // the stream way: source -> filter -> map -> toList
        List<String> streamResult = names.stream()
                .filter(name -> name.length() == 3)
                .map(String::toUpperCase)
                .toList();
        System.out.println("stream: " + streamResult);

        // a stream never changes its source (and toList gives a read-only list)
        System.out.println("source: " + names);

        List<Integer> numbers = List.of(5, 3, 8, 3, 1);
        System.out.println("distinct sorted: " + numbers.stream().distinct().sorted().toList());
        System.out.println("count > 2: " + numbers.stream().filter(n -> n > 2).count());
        System.out.println("any > 7: " + numbers.stream().anyMatch(n -> n > 7));

        // reduce combines all elements into one value
        System.out.println("sum: " + numbers.stream().reduce(0, Integer::sum));

        // max and findFirst return an Optional, because the stream might be empty
        System.out.println("longest: " + names.stream().max(Comparator.comparing(String::length)).orElse("none"));
        System.out.println("first of empty: " + List.<String>of().stream().findFirst().orElse("none"));

        // IntStream works with int directly, with no boxing
        System.out.println("range sum: " + IntStream.range(0, 5).sum());
        System.out.println("total length: " + names.stream().mapToInt(String::length).sum());
        int[] scores = {72, 95, 88};
        System.out.println("boxed: " + IntStream.of(scores).boxed().toList());

        // a stream runs only at its terminal operation, and only once
        Stream<String> once = names.stream();
        System.out.println("count: " + once.count());
        try {
            once.count();
        } catch (IllegalStateException e) {
            System.out.println("reuse: IllegalStateException");
        }
    }
}
