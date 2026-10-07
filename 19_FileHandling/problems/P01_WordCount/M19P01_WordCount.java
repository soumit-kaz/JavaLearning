import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class M19P01_WordCount {

    // how many lines are in the file
    static int countLines(Path file) throws IOException {
        return Files.readAllLines(file).size();
    }

    // how many words are in the file
    static int countWords(Path file) throws IOException {
        int words = 0;
        List<String> lines = Files.readAllLines(file);
        for (String line : lines) {
            for (String word : line.split(" ")) {
                if (!word.isEmpty()) {
                    words++;
                }
            }
        }
        return words;
    }

    // write the text to a file, count it, then remove the file
    static void test(String content, int expectedLines, int expectedWords) throws IOException {
        Path file = Path.of("wordcount-test.txt");
        Files.writeString(file, content);
        int lines = countLines(file);
        int words = countWords(file);
        Files.delete(file);

        String label = "[" + content.replace("\n", "\\n") + "] -> " + lines + " lines, " + words + " words";
        if (lines != expectedLines || words != expectedWords) {
            System.out.println(label + " FAIL, expected " + expectedLines + " lines, " + expectedWords + " words");
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) throws IOException {
        test("hello world\n", 1, 2);
        test("one\ntwo three\n", 2, 3);
        test("", 0, 0);
        test("\n\n", 2, 0);
        test("  spaced   out  \n", 1, 2);
        test("no line end at all", 1, 5);
    }
}
