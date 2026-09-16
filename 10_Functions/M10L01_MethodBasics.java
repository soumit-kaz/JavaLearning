public class M10L01_MethodBasics {

    // void: the method does a job but returns nothing
    static void sayHello() {
        System.out.println("Hello");
    }

    // a parameter receives a value from the caller
    static void printStars(int count) {
        System.out.println("*".repeat(count));
    }

    // several parameters are separated by commas
    static void printLine(char symbol, int count) {
        System.out.println(String.valueOf(symbol).repeat(count));
    }

    // a method can call other methods
    static void printBox(int size) {
        for (int i = 0; i < size; i++) {
            printStars(size);
        }
    }

    // run with: java M10L01_MethodBasics.java Asha
    // main is a method too; args holds the command-line words (empty, never null)
    public static void main(String[] args) {
        // call a method by writing its name and ()
        sayHello();

        // arguments must match the parameters in number, type and order
        printStars(5);
        printLine('=', 5);
        printBox(3);

        // the same method can be called again and again
        for (int i = 1; i <= 3; i++) {
            printStars(i);
        }

        String name = "guest";
        if (args.length >= 1) {
            name = args[0];
        }
        System.out.println("args = " + args.length + ", name = " + name);
    }
}
