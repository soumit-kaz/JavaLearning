public class M20L01_PrintingOutput {

    public static void main(String[] args) {
        // println writes the text and ends the line
        System.out.println("first line");
        System.out.println("second line");

        // print stays on the same line
        System.out.print("no");
        System.out.print(" line end");
        System.out.println(" until here");

        // + joins text and values together
        String name = "Ann";
        int age = 30;
        System.out.println(name + " is " + age);

        // careful: + on two numbers adds them, so use brackets
        System.out.println("sum: " + 2 + 3);
        System.out.println("sum: " + (2 + 3));

        // System.err is the second output, meant for error messages
        System.err.println("this is an error message");
    }
}
