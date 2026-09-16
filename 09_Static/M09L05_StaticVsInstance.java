public class M09L05_StaticVsInstance {

    // a class is a blueprint for making objects
    static class Student {
        // static: one copy shared by all students
        static String school = "Green Valley";
        static int total = 0;

        // instance: every student object has its own copy
        String name;
        int id;

        // constructor: runs every time we write new Student(...)
        Student(String studentName) {
            total++;
            name = studentName;
            id = total;
        }

        // instance method: works on one object and can use static fields too
        String describe() {
            return name + " #" + id + " @ " + school;
        }

        // static method: has no object, so using plain name here would not compile
        static int count() {
            return total;
        }

        // a static method needs an object passed in to reach instance fields
        static String nameOf(Student s) {
            return s.name;
        }
    }

    public static void main(String[] args) {
        // the static counter exists before any object
        System.out.println("count = " + Student.count());

        // new creates an object and runs the constructor
        Student a = new Student("Asha");
        Student b = new Student("Bilal");
        System.out.println(a.describe());
        System.out.println(b.describe());
        System.out.println("count = " + Student.count());

        // changing an instance field affects one object only
        a.name = "Asha K";
        System.out.println(Student.nameOf(a) + ", " + Student.nameOf(b));

        // changing a static field is seen by every object
        Student.school = "Blue River";
        System.out.println(a.describe());
        System.out.println(b.describe());
    }
}
