import java.util.Scanner;

public class M20L03_ScannerPitfalls {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        if (!in.hasNextLine()) {
            System.out.println("No input. Run this lesson yourself and type: an age, a name,");
            System.out.println("then the same age and name again.");
            return;
        }

        // the trap: nextInt stops right before the line end, so the nextLine
        // after it reads the rest of that line, which is empty
        System.out.print("Age: ");
        int age = in.nextInt();
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.println("age " + age + ", name [" + name + "]  <- the name is missing");

        // the name you typed is still waiting to be read
        System.out.println("still waiting: [" + in.nextLine() + "]");

        // the fix: read lines only, and turn the text into a number yourself
        System.out.print("Age again: ");
        int goodAge = Integer.parseInt(in.nextLine());
        System.out.print("Name again: ");
        String goodName = in.nextLine();
        System.out.println("fixed: age " + goodAge + ", name [" + goodName + "]");

        // the other trap: nextInt on something that is not a number crashes.
        // hasNextInt looks first, so you can ask for a word instead.
        System.out.print("A number, or not: ");
        if (in.hasNextInt()) {
            System.out.println("number: " + in.nextInt());
        } else if (in.hasNext()) {
            System.out.println("not a number: " + in.next());
        } else {
            System.out.println("nothing left to read");
        }

        in.close();
    }
}
