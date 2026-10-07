import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class M19L10_CopyMoveDelete {

    public static void main(String[] args) throws IOException {
        Path source = Path.of("copy-source.txt");
        Path copy = Path.of("copy-target.txt");
        Files.writeString(source, "hello\n");

        // copy makes a second file; the first one stays
        Files.copy(source, copy);
        System.out.println("copy exists: " + Files.exists(copy));
        System.out.println("source exists: " + Files.exists(source));

        // copying onto a file that is already there fails
        try {
            Files.copy(source, copy);
        } catch (IOException e) {
            System.out.println("second copy failed: " + e.getClass().getSimpleName());
        }

        // unless you say that replacing it is fine
        Files.copy(source, copy, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("copy with REPLACE_EXISTING worked");

        // move is used both for renaming and for moving to another folder
        Path newName = Path.of("copy-renamed.txt");
        Files.move(copy, newName);
        System.out.println("old name gone: " + Files.exists(copy));
        System.out.println("new name there: " + Files.exists(newName));

        // delete removes the file
        Files.delete(newName);
        System.out.println("deleted: " + Files.exists(newName));

        // deleting it again fails, because it is not there any more
        try {
            Files.delete(newName);
        } catch (IOException e) {
            System.out.println("second delete failed: " + e.getClass().getSimpleName());
        }

        // deleteIfExists answers true or false instead of failing
        System.out.println("deleteIfExists missing file: " + Files.deleteIfExists(newName));
        System.out.println("deleteIfExists real file: " + Files.deleteIfExists(source));
    }
}
