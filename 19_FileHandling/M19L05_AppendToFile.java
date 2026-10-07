import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class M19L05_AppendToFile {

    public static void main(String[] args) throws IOException {
        Path log = Path.of("append-demo.txt");

        // a normal write replaces the file, so the first line is gone
        Files.writeString(log, "first\n");
        Files.writeString(log, "second\n");
        System.out.println("two normal writes: " + Files.readAllLines(log));

        // APPEND adds to the end instead, CREATE makes the file if it is new
        Files.writeString(log, "third\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        System.out.println("after append: " + Files.readAllLines(log));

        // adding lines in a loop, the way a program keeps a log
        Files.delete(log);
        String[] events = {"start", "save", "stop"};
        for (String event : events) {
            Files.writeString(log, event + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
        System.out.println("log: " + Files.readAllLines(log));

        // do not forget the \n, or the next text joins the same line
        Files.writeString(log, "no newline", StandardOpenOption.APPEND);
        Files.writeString(log, " and more", StandardOpenOption.APPEND);
        System.out.println("last line: " + Files.readAllLines(log).get(3));

        Files.delete(log);
    }
}
