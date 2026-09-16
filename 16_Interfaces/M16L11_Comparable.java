import java.util.Arrays;

public class M16L11_Comparable {

    // Comparable<Student> gives Student one "natural order"
    static class Student implements Comparable<Student> {
        private final String name;
        private final int marks;

        Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        // negative: this first, zero: equal, positive: other first
        @Override
        public int compareTo(Student other) {
            // Integer.compare is safe; marks - other.marks can overflow
            int byMarks = Integer.compare(this.marks, other.marks);
            if (byMarks != 0) {
                return byMarks;
            }
            // equal marks: break the tie by name
            return this.name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return name + "(" + marks + ")";
        }
    }

    // works for any array of Students, using only compareTo
    static Student best(Student[] students) {
        Student best = students[0];
        for (Student s : students) {
            if (s.compareTo(best) > 0) {
                best = s;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        Student[] students = {
            new Student("Rina", 82),
            new Student("Karim", 67),
            new Student("Nila", 95),
            new Student("Arif", 82)
        };

        System.out.println("Rina vs Karim: " + students[0].compareTo(students[1]));
        System.out.println("best: " + best(students));

        // Arrays.sort uses compareTo when no comparator is given
        Arrays.sort(students);
        System.out.println("sorted: " + Arrays.toString(students));

        // String and Integer already implement Comparable
        String[] names = {"Nila", "arif", "Karim"};
        Arrays.sort(names);
        // uppercase letters come before lowercase ones
        System.out.println("names: " + Arrays.toString(names));

        // a class without Comparable fails at runtime with ClassCastException
    }
}
