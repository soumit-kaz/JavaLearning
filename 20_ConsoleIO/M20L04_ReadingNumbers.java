import java.util.Scanner;

public class M20L04_ReadingNumbers {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        if (!in.hasNextLine()) {
            System.out.println("No input. Run this lesson yourself and type a number.");
            return;
        }

        // what you type is text, so it has to be turned into a number
        System.out.print("A number: ");
        String line = in.nextLine();
        int number = Integer.parseInt(line);
        System.out.println("number + 1 = " + (number + 1));

        // and text that is not a number throws NumberFormatException,
        // so ask again until the answer is one
        int age = -1;
        while (age < 0 && in.hasNextLine()) {
            System.out.print("Age: ");
            try {
                age = Integer.parseInt(in.nextLine().strip());
            } catch (NumberFormatException e) {
                System.out.println("that is not a number, try again");
            }
        }
        System.out.println("age: " + age);

        in.close();
    }
}
