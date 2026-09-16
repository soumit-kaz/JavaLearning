import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class M17L08_TreeSet {

    public static void main(String[] args) {
        // TreeSet keeps unique elements in sorted order
        TreeSet<Integer> sorted = new TreeSet<>(List.of(5, 3, 5, 1, 3, 9));
        System.out.println("TreeSet: " + sorted);
        System.out.println("first: " + sorted.first());
        System.out.println("last: " + sorted.last());

        // floor: largest <= x, ceiling: smallest >= x
        System.out.println("floor(4): " + sorted.floor(4));
        System.out.println("ceiling(4): " + sorted.ceiling(4));
        // lower: largest < x, higher: smallest > x (null if none)
        System.out.println("lower(3): " + sorted.lower(3));
        System.out.println("higher(9): " + sorted.higher(9));

        // headSet excludes the bound, tailSet includes it
        System.out.println("headSet(5): " + sorted.headSet(5));
        System.out.println("tailSet(5): " + sorted.tailSet(5));

        // pollFirst removes and returns the smallest element
        System.out.println("pollFirst: " + sorted.pollFirst());
        System.out.println("descendingSet: " + sorted.descendingSet());

        // a comparator changes the order, and compare == 0 means "same element"
        TreeSet<String> byLength = new TreeSet<>(Comparator.comparing(String::length));
        byLength.addAll(List.of("ccc", "a", "bb", "dd"));
        System.out.println("by length (dd dropped): " + byLength);
    }
}
