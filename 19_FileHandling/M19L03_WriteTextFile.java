import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class M19L03_WriteTextFile {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("write-demo.txt");

        // write one piece of text; the file is made if it is not there
        Files.writeString(file, "Hello, files!\n");
        System.out.println("wrote: " + Files.readString(file).strip());

        // writing again throws away what was in the file before
        Files.writeString(file, "second write\n");
        System.out.println("now: " + Files.readString(file).strip());

        // \n ends a line, so this makes three lines
        Files.writeString(file, "one\ntwo\nthree\n");
        System.out.println("lines: " + Files.readAllLines(file));

        // or give a list and let Java add the line ends
        Files.write(file, List.of("alpha", "beta"));
        System.out.println("lines: " + Files.readAllLines(file));

        Files.delete(file);
    }
}
