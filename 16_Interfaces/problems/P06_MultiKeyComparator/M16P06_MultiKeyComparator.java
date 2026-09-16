import java.util.Arrays;
import java.util.Comparator;

public class M16P06_MultiKeyComparator {

    // natural order: by name
    record Student(String name, int grade, Integer age, double gpa) implements Comparable<Student> {
        @Override
        public int compareTo(Student other) {
            return name.compareTo(other.name);
        }

        int nameLength() {
            return name.length();
        }
    }

    // builds one comparator for a key such as "age desc nullsFirst"
    static Comparator<Student> keyComparator(String key, boolean desc, boolean nullsFirst) {
        if (key.equals("age")) {
            // reverse first, THEN place nulls, so nulls do not move with desc
            Comparator<Integer> order = desc ? Comparator.reverseOrder() : Comparator.naturalOrder();
            Comparator<Integer> withNulls = nullsFirst ? Comparator.nullsFirst(order) : Comparator.nullsLast(order);
            return Comparator.comparing(Student::age, withNulls);
        }
        Comparator<Student> result = switch (key) {
            case "name" -> Comparator.comparing(Student::name);
            case "grade" -> Comparator.comparing(Student::grade);
            case "gpa" -> Comparator.comparing(Student::gpa);
            case "namelen" -> Comparator.comparing(Student::nameLength);
            default -> throw new IllegalArgumentException("unknown key " + key);
        };
        return desc ? result.reversed() : result;
    }

    // "grade, gpa desc" becomes grade, then gpa descending, then natural order
    static Comparator<Student> parse(String spec) {
        Comparator<Student> result = Comparator.naturalOrder();
        String[] parts = spec.split(",");
        // build from the last key backwards: this key first, then the rest
        for (int i = parts.length - 1; i >= 0; i--) {
            String[] words = parts[i].trim().toLowerCase().split("\\s+");
            boolean desc = false;
            boolean nullsFirst = false;
            for (int w = 1; w < words.length; w++) {
                switch (words[w]) {
                    case "asc" -> desc = false;
                    case "desc" -> desc = true;
                    case "nullsfirst" -> nullsFirst = true;
                    case "nullslast" -> nullsFirst = false;
                    default -> throw new IllegalArgumentException("unknown modifier " + words[w]);
                }
            }
            result = keyComparator(words[0], desc, nullsFirst).thenComparing(result);
        }
        return result;
    }

    static final Student[] DATA = {
        new Student("Zara", 10, 16, 3.9),
        new Student("Adam", 11, null, 3.5),
        new Student("Mina", 10, 15, 3.9),
        new Student("Bob", 11, 17, 3.5),
        new Student("Eve", 12, 17, 3.1),
        new Student("Carl", 10, null, 2.8)
    };

    static String sortNames(String spec) {
        try {
            Student[] copy = DATA.clone();
            Arrays.sort(copy, parse(spec));
            String out = "";
            for (Student s : copy) {
                out += (out.isEmpty() ? "" : " ") + s.name();
            }
            return out;
        } catch (IllegalArgumentException e) {
            return "error: " + e.getMessage();
        }
    }

    static int failures = 0;

    static void check(String label, Object actual, Object expected) {
        boolean ok = String.valueOf(actual).equals(String.valueOf(expected));
        if (!ok) {
            failures++;
        }
        System.out.println(label + " -> " + actual + "  " + (ok ? "PASS" : "FAIL expected " + expected));
    }

    public static void main(String[] args) {
        check("\"name\"", sortNames("name"), "Adam Bob Carl Eve Mina Zara");
        check("\"grade desc\"", sortNames("grade desc"), "Eve Adam Bob Carl Mina Zara");
        check("\"grade, gpa desc\"", sortNames("grade, gpa desc"), "Mina Zara Carl Adam Bob Eve");
        check("\"age\"", sortNames("age"), "Mina Zara Bob Eve Adam Carl");
        check("\"age desc nullsFirst\"", sortNames("age desc nullsFirst"), "Adam Carl Bob Eve Zara Mina");
        check("\"namelen, name desc\"", sortNames("namelen, name desc"), "Eve Bob Zara Mina Carl Adam");
        check("\" GPA DESC , age \"", sortNames(" GPA DESC , age "), "Mina Zara Bob Adam Eve Carl");
        check("\"height\"", sortNames("height"), "error: unknown key height");
        check("\"name upward\"", sortNames("name upward"), "error: unknown modifier upward");

        if (failures > 0) {
            System.exit(1);
        }
    }
}
