import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class M19L08_FileExceptions {

    public static void main(String[] args) throws IOException {
        Path missing = Path.of("not-here.txt");

        // File work can always go wrong, so Java makes you deal with IOException:
        // either catch it, or write throws IOException on your method like main does.
        // NoSuchFileException is the kind you get for a file that is not there.
        try {
            Files.readString(missing);
        } catch (NoSuchFileException e) {
            System.out.println("there is no file called " + e.getFile());
        }

        // instead of stopping, you can carry on with a sensible answer
        System.out.println("size of a missing file: " + sizeOf(missing));

        Path real = Path.of("exception-demo.txt");
        Files.writeString(real, "hello\n");
        System.out.println("size of a real file: " + sizeOf(real));

        // finally runs whether the try worked or not, so cleaning up goes there
        try {
            System.out.println("reading: " + Files.readString(real).strip());
        } finally {
            Files.delete(real);
            System.out.println("deleted in finally");
        }
    }

    // catch the problem here and answer -1, so the caller needs no try block
    static long sizeOf(Path file) {
        try {
            return Files.size(file);
        } catch (IOException e) {
            return -1;
        }
    }
}
