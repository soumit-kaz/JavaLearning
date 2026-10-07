import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class M19L09_ScannerOnFiles {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("scanner-demo.txt");
        Files.writeString(file, "10 20 30\n40 50\n");

        // a Scanner can read a file as well as the keyboard.
        // nextInt takes the next number, hasNextInt asks if there is one.
        int total = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextInt()) {
                total += sc.nextInt();
            }
        }
        System.out.println("total: " + total);

        // next() takes the next word
        Files.writeString(file, "ann bob cy\n");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                System.out.println("word: " + sc.next());
            }
        }

        // a name and a number on every line
        Files.writeString(file, "ann 90\nbob 72\n");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String name = sc.next();
                int score = sc.nextInt();
                System.out.println(name + " scored " + score);
            }
        }

        // watch out: nextInt stops right before the line end, so the nextLine
        // after it gives you an empty line
        Files.writeString(file, "42\nAnna Smith\n");
        try (Scanner sc = new Scanner(file)) {
            int id = sc.nextInt();
            String rest = sc.nextLine();
            String name = sc.nextLine();
            System.out.println("id " + id + ", rest [" + rest + "], name " + name);
        }

        // the easy fix: read whole lines and turn the text into a number yourself
        try (Scanner sc = new Scanner(file)) {
            int id = Integer.parseInt(sc.nextLine());
            String name = sc.nextLine();
            System.out.println("id " + id + ", name " + name);
        }

        Files.delete(file);
    }
}
