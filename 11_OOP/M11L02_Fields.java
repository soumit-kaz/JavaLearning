public class M11L02_Fields {

    static class Student {
        // instance fields: every object has its own copy
        String name;
        int age;
        double gpa;
        boolean active;

        // a field can have a starting value
        String school = "City School";

        // static field: one copy shared by all objects
        static int count = 0;

        // a static method has no object, so it cannot use name or age
        static int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        // fields start at 0, 0.0, false or null (local variables do not)
        Student s = new Student();
        System.out.println("defaults = " + s.name + ", " + s.age + ", " + s.gpa + ", " + s.active);
        System.out.println("school = " + s.school);

        // each object keeps its own values
        Student t = new Student();
        s.name = "Ann";
        t.name = "Bob";
        System.out.println("s.name = " + s.name + ", t.name = " + t.name);

        // use a static field through the class name
        // count by hand for now; a constructor (next lesson) can do it
        Student.count++;
        Student.count++;
        System.out.println("count = " + Student.getCount());
    }
}
