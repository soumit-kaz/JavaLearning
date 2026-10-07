import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class M19L02_FileInfo {

    public static void main(String[] args) throws IOException {
        // make a file so we have something to look at
        Path file = Path.of("info-demo.txt");
        Files.writeString(file, "hello\n");

        // is the file there?
        System.out.println("exists: " + Files.exists(file));

        // is it a file or a folder?
        System.out.println("isRegularFile: " + Files.isRegularFile(file));
        System.out.println("isDirectory: " + Files.isDirectory(file));

        // how big is it, in bytes
        System.out.println("size: " + Files.size(file));

        // a file that was never made
        Path missing = Path.of("not-here.txt");
        System.out.println("missing exists: " + Files.exists(missing));

        // remove the file again
        Files.delete(file);
        System.out.println("exists after delete: " + Files.exists(file));
    }
}
