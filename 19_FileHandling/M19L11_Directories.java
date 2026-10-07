import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class M19L11_Directories {

    public static void main(String[] args) throws IOException {
        Path folder = Path.of("dir-demo");

        // make the folder
        Files.createDirectory(folder);
        System.out.println("folder made: " + Files.isDirectory(folder));

        // put three files in it
        Files.writeString(folder.resolve("notes.txt"), "notes\n");
        Files.writeString(folder.resolve("todo.txt"), "todo\n");
        Files.writeString(folder.resolve("cat.png"), "pretend picture\n");

        // Files.list gives what is inside the folder. It opens the folder, so it
        // goes in a try(...) to be closed, like a reader.
        List<Path> children;
        try (Stream<Path> list = Files.list(folder)) {
            children = list.sorted().toList();
        }

        for (Path child : children) {
            System.out.println("found: " + child.getFileName() + ", " + Files.size(child) + " bytes");
        }

        // only the .txt files
        for (Path child : children) {
            if (child.getFileName().toString().endsWith(".txt")) {
                System.out.println("txt file: " + child.getFileName());
            }
        }

        // add up the sizes
        long bytes = 0;
        for (Path child : children) {
            bytes += Files.size(child);
        }
        System.out.println(children.size() + " files, " + bytes + " bytes");

        // a folder must be empty before it can be deleted,
        // so remove the files first
        for (Path child : children) {
            Files.delete(child);
        }
        Files.delete(folder);
        System.out.println("folder gone: " + Files.exists(folder));
    }
}
