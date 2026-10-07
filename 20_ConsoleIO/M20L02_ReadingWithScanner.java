import java.util.Scanner;

public class M20L02_ReadingWithScanner {

    public static void main(String[] args) {
        // a Scanner reads what you type. In the brackets of try it is closed
        // again when the block ends, however the block ends.
        try (Scanner in = new Scanner(System.in)) {

            // hasNextLine is false when there is nothing to read at all,
            // which happens when a script runs this lesson instead of a person
            if (!in.hasNextLine()) {
                System.out.println("No input. Run this lesson yourself and type the answers.");
                return;
            }

            // print the question first, then read the answer.
            // nextLine reads the whole line, spaces and all.
            System.out.print("Name: ");
            String name = in.nextLine();

            // nextInt reads a whole number
            System.out.print("Age: ");
            int age = in.nextInt();

            // next reads one word
            System.out.print("Favourite drink: ");
            String drink = in.next();

            System.out.println("Hello " + name + ", you are " + age + " and you like " + drink);
        }
    }
}
