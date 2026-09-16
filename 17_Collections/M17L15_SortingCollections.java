import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class M17L15_SortingCollections {

    // Comparable gives a class its natural order
    record Version(int major, int minor) implements Comparable<Version> {
        @Override
        public int compareTo(Version other) {
            // use Integer.compare, never subtraction (it can overflow)
            if (major != other.major) {
                return Integer.compare(major, other.major);
            }
            return Integer.compare(minor, other.minor);
        }
    }

    record Student(String name, int grade) {
    }

    public static void main(String[] args) {
        List<Version> versions = new ArrayList<>();
        versions.add(new Version(1, 10));
        versions.add(new Version(1, 2));
        versions.add(new Version(0, 9));
        // sort(null) uses compareTo
        versions.sort(null);
        System.out.println("versions: " + versions);

        List<Student> students = new ArrayList<>();
        students.add(new Student("zoe", 90));
        students.add(new Student("adam", 85));
        students.add(new Student("mia", 90));
        students.add(new Student("ben", 70));

        // a Comparator gives an order from outside the class
        students.sort(Comparator.comparing(Student::name));
        System.out.println("by name: " + names(students));

        // highest grade first, ties broken by name
        students.sort(Comparator.comparingInt(Student::grade).reversed()
                .thenComparing(Student::name));
        System.out.println("by grade: " + names(students));

        // the Collections class has ready-made helpers for lists
        List<Integer> numbers = new ArrayList<>(List.of(42, 7, 19, 3, 7));
        Collections.sort(numbers);
        System.out.println("sorted: " + numbers);

        // binarySearch needs a sorted list; a negative result means "not found"
        System.out.println("binarySearch 19: " + Collections.binarySearch(numbers, 19));
        System.out.println("binarySearch 20: " + Collections.binarySearch(numbers, 20));

        Collections.reverse(numbers);
        System.out.println("reversed: " + numbers);

        System.out.println("min: " + Collections.min(numbers));
        System.out.println("max: " + Collections.max(numbers));
        System.out.println("frequency of 7: " + Collections.frequency(numbers, 7));

        Collections.swap(numbers, 0, 4);
        System.out.println("swapped: " + numbers);

        // sort a list in descending order
        numbers.sort(Comparator.reverseOrder());
        System.out.println("descending: " + numbers);
    }

    // collect the names so a sorted list prints on one line
    static List<String> names(List<Student> students) {
        List<String> result = new ArrayList<>();
        for (Student s : students) {
            result.add(s.name());
        }
        return result;
    }
}
