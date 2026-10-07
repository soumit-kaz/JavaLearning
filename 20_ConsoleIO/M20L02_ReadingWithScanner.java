import java.util.Scanner;

public class M20L02_ReadingWithScanner {

    public static void main(String[] args) {
        
            Scanner inputTaker = new Scanner(System.in);
            // print the question first, then read the answer.
            // nextLine reads the whole line, spaces and all.
            System.out.print("Name: ");
            String name = inputTaker.nextLine();

            // nextInt reads a whole number
            System.out.print("Age: ");
            int age = inputTaker.nextInt();

            // next reads one word
            System.out.print("Favourite drink: ");
            String drink = inputTaker.next();

            System.out.println("Hello " + name + ", you are " + age + " and you like " + drink);
            inputTaker.close();
    }
}
