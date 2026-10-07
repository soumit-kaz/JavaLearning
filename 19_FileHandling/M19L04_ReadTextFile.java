import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class M19L04_ReadTextFile {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("read-demo.txt");
        Files.write(file, List.of("the cat sat", "on the mat", "the end"));

        // the whole file as one piece of text
        String text = Files.readString(file);
        System.out.println("characters: " + text.length());

        // the whole file as a list of lines, with no line ends
        List<String> lines = Files.readAllLines(file);
        System.out.println("lines: " + lines.size());
        System.out.println("first line: " + lines.get(0));
        System.out.println("last line: " + lines.get(lines.size() - 1));

        // go through the lines one by one
        for (String line : lines) {
            System.out.println("line: " + line);
        }

        // look for something
        for (String line : lines) {
            if (line.contains("cat")) {
                System.out.println("found it: " + line);
            }
        }

        // count the words of every line
        int words = 0;
        for (String line : lines) {
            words += line.split(" ").length;
        }
        System.out.println("words: " + words);

        Files.delete(file);
    }
}
