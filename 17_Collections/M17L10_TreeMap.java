import java.util.TreeMap;

public class M17L10_TreeMap {

    public static void main(String[] args) {
        // TreeMap keeps keys sorted
        TreeMap<String, Integer> counts = new TreeMap<>();
        for (String word : "the cat and the hat".split(" ")) {
            counts.merge(word, 1, Integer::sum);
        }
        System.out.println("counts: " + counts);
        System.out.println("firstKey: " + counts.firstKey());
        System.out.println("lastKey: " + counts.lastKey());

        // minimum score needed for each grade
        TreeMap<Integer, String> grades = new TreeMap<>();
        grades.put(0, "F");
        grades.put(60, "D");
        grades.put(70, "C");
        grades.put(80, "B");
        grades.put(90, "A");

        // floorEntry finds the largest key <= the score
        for (int score : new int[] {95, 80, 79, 42}) {
            System.out.println(score + ": " + grades.floorEntry(score).getValue());
        }

        // ceilingKey: smallest key >= x, higherKey: smallest key > x
        System.out.println("ceilingKey(71): " + grades.ceilingKey(71));
        System.out.println("higherKey(70): " + grades.higherKey(70));
        // they return null when nothing matches
        System.out.println("floorKey(-1): " + grades.floorKey(-1));

        // headMap excludes the bound, tailMap includes it
        System.out.println("headMap(70): " + grades.headMap(70));
        System.out.println("tailMap(70): " + grades.tailMap(70));

        // descendingMap walks the keys from largest to smallest
        System.out.println("descendingMap: " + grades.descendingMap());
    }
}
