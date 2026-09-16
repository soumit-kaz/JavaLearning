public class M09P02_StudentIds {

    static class Student {
        // shared by all students: the next number to hand out
        static int nextNumber = 1;

        // each student keeps its own name and id
        String name;
        String id;

        Student(String name) {
            this.name = name;
            this.id = "S" + nextNumber;
            nextNumber++;
        }

        static int count() {
            return nextNumber - 1;
        }
    }

    static void check(String input, String actual, String expected) {
        String status = actual.equals(expected) ? "PASS" : "FAIL";
        System.out.println(input + " -> " + actual + "  " + status);
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student bilal = new Student("Bilal");
        Student chen = new Student("Chen");

        check("Asha", asha.id, "S1");
        check("Bilal", bilal.id, "S2");
        check("Chen", chen.id, "S3");
        check("count", String.valueOf(Student.count()), "3");

        Student dana = new Student("Dana");
        check("Dana", dana.id, "S4");
        check("Asha again", asha.id, "S1");
    }
}
