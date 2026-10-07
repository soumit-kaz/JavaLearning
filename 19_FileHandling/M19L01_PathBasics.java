import java.nio.file.Path;

public class M19L01_PathBasics {

    public static void main(String[] args) {
        // a Path is the name of a file; the file does not have to exist yet
        Path file = Path.of("notes.txt");
        System.out.println("file: " + file);

        // a file inside a folder
        Path inFolder = Path.of("data/notes.txt");
        System.out.println("inFolder: " + inFolder);
        System.out.println("fileName: " + inFolder.getFileName());
        System.out.println("parent: " + inFolder.getParent());

        // resolve adds a name to a folder path
        Path folder = Path.of("data");
        System.out.println("resolve: " + folder.resolve("notes.txt"));

        // "notes.txt" means the folder the program runs in
        System.out.println("full path: " + file.toAbsolutePath());

        // write paths with / and Java fixes the separator on Windows
        System.out.println("works everywhere: " + Path.of("a/b/c.txt"));
    }
}
