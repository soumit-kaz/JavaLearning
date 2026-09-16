import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class M17L06_Iterator {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("ann", "bob", "cy", "dee"));

        // a for-each loop uses an Iterator behind the scenes
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            String name = it.next();
            // Iterator.remove is the safe way to delete while looping
            if (name.length() == 2) {
                it.remove();
            }
        }
        System.out.println("it.remove: " + names);

        // changing a list inside a for-each loop throws
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        try {
            for (Integer n : numbers) {
                if (n == 2) {
                    numbers.remove(n);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("for-each remove: ConcurrentModificationException");
        }

        // removeIf is the simplest safe way
        List<Integer> values = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        values.removeIf(n -> n % 2 == 0);
        System.out.println("removeIf even: " + values);

        // a backwards index loop is also safe
        List<Integer> more = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        for (int i = more.size() - 1; i >= 0; i--) {
            if (more.get(i) % 2 == 0) {
                more.remove(i);
            }
        }
        System.out.println("backwards loop: " + more);

        // adding while looping also throws, so collect new items first
        List<String> words = new ArrayList<>(List.of("a", "b"));
        List<String> extra = new ArrayList<>();
        for (String w : words) {
            extra.add(w + w);
        }
        words.addAll(extra);
        System.out.println("addAll after loop: " + words);
    }
}
