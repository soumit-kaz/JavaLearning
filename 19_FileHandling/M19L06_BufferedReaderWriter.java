import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class M19L06_BufferedReaderWriter {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("buffered-demo.txt");

        // a writer lets you add lines one at a time.
        // try (...) closes the file for you when the block ends.
        try (BufferedWriter out = Files.newBufferedWriter(file)) {
            for (int i = 1; i <= 5; i++) {
                out.write("line " + i);
                out.newLine();
            }
        }
        System.out.println("wrote 5 lines");

        // a reader gives one line at a time, and null when the file is finished
        try (BufferedReader in = Files.newBufferedReader(file)) {
            String line = in.readLine();
            while (line != null) {
                System.out.println("read: " + line);
                line = in.readLine();
            }
        }

        // readLine takes the line end off, and a blank line comes back empty
        Files.writeString(file, "a\n\nb\n");
        try (BufferedReader in = Files.newBufferedReader(file)) {
            System.out.println("1: [" + in.readLine() + "]");
            System.out.println("2: [" + in.readLine() + "]");
            System.out.println("3: [" + in.readLine() + "]");
            System.out.println("4: " + in.readLine());
        }

        // use a reader and a writer when the file is big: readAllLines would
        // put the whole file in memory, this keeps only one line at a time
        Files.delete(file);
    }
}
