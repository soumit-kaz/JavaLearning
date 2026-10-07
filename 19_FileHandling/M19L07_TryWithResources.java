import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class M19L07_TryWithResources {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("resource-demo.txt");
        Files.writeString(file, "one\ntwo\n");

        // an open file has to be closed again. Put it in the parentheses of try
        // and Java closes it when the block ends.
        try (BufferedReader in = Files.newBufferedReader(file)) {
            System.out.println("first line: " + in.readLine());
        }

        // the old way: close it yourself at the end
        BufferedReader in = Files.newBufferedReader(file);
        System.out.println("old way: " + in.readLine());
        in.close();

        // two files at the same time, separated by a semicolon
        Path copy = Path.of("resource-copy.txt");
        try (BufferedReader source = Files.newBufferedReader(file);
             BufferedWriter target = Files.newBufferedWriter(copy)) {
            String line = source.readLine();
            while (line != null) {
                target.write(line);
                target.newLine();
                line = source.readLine();
            }
        }
        System.out.println("copy: " + Files.readAllLines(copy));

        // the file is closed even when something goes wrong inside the block,
        // which is why this way is better than closing it yourself
        try (BufferedReader bad = Files.newBufferedReader(file)) {
            bad.readLine();
            throw new IOException("something went wrong");
        } catch (IOException e) {
            System.out.println("caught: " + e.getMessage() + " (the file is closed anyway)");
        }

        Files.delete(file);
        Files.delete(copy);
    }
}
