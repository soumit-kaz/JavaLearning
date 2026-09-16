import java.util.Arrays;
import java.util.Comparator;

public class M16L12_Comparator {

    static class Student {
        private final String name;
        private final String dept;
        private final int marks;

        Student(String name, String dept, int marks) {
            this.name = name;
            this.dept = dept;
            this.marks = marks;
        }

        String name() {
            return name;
        }

        String dept() {
            return dept;
        }

        int marks() {
            return marks;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // a Comparator is a separate object that orders two values
    static class ByName implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            return a.name.compareTo(b.name);
        }
    }

    public static void main(String[] args) {
        Student[] students = {
            new Student("Rina", "Eng", 82),
            new Student("Karim", "Ops", 67),
            new Student("Nila", "Eng", 95),
            new Student("Arif", null, 82)
        };

        // one class can have many orders, each in its own comparator
        Arrays.sort(students, new ByName());
        System.out.println("by name: " + Arrays.toString(students));

        // Comparator is a functional interface, so a lambda works too
        Arrays.sort(students, (a, b) -> Integer.compare(a.name.length(), b.name.length()));
        System.out.println("by name length: " + Arrays.toString(students));

        // comparing builds a comparator from a key; reversed flips it
        Comparator<Student> byMarksHighFirst = Comparator.comparing(Student::marks).reversed();
        Arrays.sort(students, byMarksHighFirst);
        System.out.println("marks high first: " + Arrays.toString(students));

        // thenComparing breaks ties with the next rule
        Arrays.sort(students, byMarksHighFirst.thenComparing(Student::name));
        System.out.println("marks, then name: " + Arrays.toString(students));

        // a null key would throw; nullsLast puts those students at the end
        Comparator<Student> byDept = Comparator.comparing(Student::dept,
                Comparator.nullsLast(Comparator.naturalOrder()));
        Arrays.sort(students, byDept.thenComparing(new ByName()));
        System.out.println("dept, nulls last: " + Arrays.toString(students));
    }
}
