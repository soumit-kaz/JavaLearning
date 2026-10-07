import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class M19L03_WriteTextFile {

    public static void main(String[] args) throws IOException {
        Path file1 = Path.of("D:\\JavaLearning\\FilesOutput\\write-demo.txt");

        // write a line to the file
        Files.writeString(file1, "Line 1\n", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        Files.writeString(file1, "Line 2\n", StandardOpenOption.APPEND);
        Files.writeString(file1, "Line 3\nLine 4\n", StandardOpenOption.APPEND);

        // read all the lines back from the file

        List <String> lines = Files.readAllLines(file1);
        System.out.println("lines: " + lines);

        Path file2 = Path.of("D:\\JavaLearning\\FilesOutput\\write-demo2.txt");

        Files.copy(file1, file2);

        // Files.delete(file1);
    }
}
