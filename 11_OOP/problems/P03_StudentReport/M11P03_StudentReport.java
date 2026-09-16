import java.util.Arrays;

public class M11P03_StudentReport {

    static class Student {
        private final String name;
        private final int[] marks;

        Student(String name, int[] marks) {
            this.name = name;
            // Copy so the caller cannot change our marks later
            this.marks = marks.clone();
        }

        String getName() {
            return name;
        }

        int[] getMarks() {
            return marks.clone();
        }

        double average() {
            if (marks.length == 0) {
                return 0;
            }
            int sum = 0;
            for (int m : marks) {
                sum += m;
            }
            return (double) sum / marks.length;
        }

        char grade() {
            double avg = average();
            if (avg >= 90) {
                return 'A';
            }
            if (avg >= 75) {
                return 'B';
            }
            if (avg >= 50) {
                return 'C';
            }
            return 'F';
        }
    }

    static Student top(Student[] students) {
        Student best = students[0];
        for (Student s : students) {
            if (s.average() > best.average()) {
                best = s;
            }
        }
        return best;
    }

    static boolean failed = false;

    // print one PASS or FAIL line and remember any failure
    static void check(String label, Object actual, String expected) {
        boolean ok = String.valueOf(actual).equals(expected);
        if (!ok) {
            failed = true;
        }
        System.out.println(label + " -> " + actual + " " + (ok ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        int[] marks = {90, 95, 100};
        Student ann = new Student("Ann", marks);
        Student bob = new Student("Bob", new int[]{70, 80});
        Student cat = new Student("Cat", new int[]{50, 60});
        Student dan = new Student("Dan", new int[]{});

        check("Ann [90, 95, 100] average", ann.average(), "95.0");
        check("grades Ann Bob Cat Dan", "" + ann.grade() + bob.grade() + cat.grade() + dan.grade(), "ABCF");
        check("top student", top(new Student[]{bob, ann, cat}).getName(), "Ann");

        marks[0] = 0;
        ann.getMarks()[1] = 0;
        check("Ann marks after outside changes", Arrays.toString(ann.getMarks()), "[90, 95, 100]");

        if (failed) {
            System.exit(1);
        }
    }
}
