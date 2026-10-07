import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class M20L08_BufferedReaderInput {

    public static void main(String[] args) throws IOException {
        // the other way to read the keyboard. It reads whole lines only, and it
        // is faster than Scanner, which matters when there is a lot of input.
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {

            // readLine gives null when there is nothing left to read
            System.out.print("Name: ");
            String name = in.readLine();
            if (name == null) {
                System.out.println();
                System.out.println("No input. Run this lesson yourself and type the answers.");
                return;
            }

            // there is no readInt, so convert the line yourself
            System.out.print("Age: ");
            int age = Integer.parseInt(in.readLine());
            System.out.println(name + " is " + age);

            // read the rest of the lines, stopping at the null
            int count = 0;
            String line = in.readLine();
            while (line != null) {
                count++;
                System.out.println("extra line: " + line);
                line = in.readLine();
            }
            System.out.println(count + " extra lines");
        }

        // readLine throws IOException, which is why main says throws IOException.
        // Scanner does not, which is why beginners usually start with Scanner.
    }
}
