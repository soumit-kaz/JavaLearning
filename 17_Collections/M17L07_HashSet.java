import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class M17L07_HashSet {

    public static void main(String[] args) {
        // HashSet stores unique elements, with no order
        Set<String> visited = new HashSet<>();
        // add returns false when the element is already there
        System.out.println("add paris: " + visited.add("paris"));
        System.out.println("add paris again: " + visited.add("paris"));
        visited.add("rome");
        System.out.println("size: " + visited.size());
        System.out.println("contains rome: " + visited.contains("rome"));
        System.out.println("remove rome: " + visited.remove("rome"));

        // a set is a quick way to drop duplicates
        List<Integer> withDuplicates = List.of(5, 3, 5, 1, 3, 9);
        Set<Integer> unique = new HashSet<>(withDuplicates);
        System.out.println("unique count: " + unique.size());

        // LinkedHashSet does the same but keeps insertion order
        Set<Integer> firstSeen = new LinkedHashSet<>(withDuplicates);
        System.out.println("LinkedHashSet: " + firstSeen);

        // set operations: copy first, because addAll/retainAll/removeAll change the set
        Set<Integer> a = Set.of(1, 2, 3, 4);
        Set<Integer> b = Set.of(3, 4, 5);

        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);
        System.out.println("union: " + union);

        Set<Integer> intersection = new HashSet<>(a);
        intersection.retainAll(b);
        System.out.println("intersection: " + intersection);

        Set<Integer> difference = new HashSet<>(a);
        difference.removeAll(b);
        System.out.println("difference: " + difference);

        // subset check
        System.out.println("a contains {1, 2}: " + a.containsAll(Set.of(1, 2)));

        // two sets are equal when they hold the same elements, in any order
        System.out.println("equal sets: " + union.equals(Set.of(5, 4, 3, 2, 1)));
    }
}
