public class M09L02_StaticMethods {

    // a method is a named block of code; static means it belongs to the class
    // void: it gives nothing back
    static void sayHello() {
        System.out.println("Hello!");
    }

    // parameters are inputs; return sends a result back
    static int add(int a, int b) {
        return a + b;
    }

    // a static variable keeps its value between calls
    static int calls = 0;

    static int staticCounter() {
        calls++;
        return calls;
    }

    // a local variable is created fresh on every call
    static int localCounter() {
        int count = 0;
        count++;
        return count;
    }

    // a static method can reset static state
    static void reset() {
        calls = 0;
    }

    public static void main(String[] args) {
        // call a method by its name with ()
        sayHello();

        // from another class you write the class name in front
        M09L02_StaticMethods.sayHello();
        System.out.println("add = " + add(3, 4));

        // Java classes have static methods too
        System.out.println("Math.max = " + Math.max(3, 8));

        System.out.println("local: " + localCounter() + " " + localCounter() + " " + localCounter());
        System.out.println("static: " + staticCounter() + " " + staticCounter() + " " + staticCounter());

        reset();
        System.out.println("after reset: " + staticCounter());
    }
}
