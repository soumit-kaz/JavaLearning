import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class M19P03_FindInFile {

    // the numbers of the lines that contain the text, counting from 1
    static List<Integer> findLines(Path file, String text) throws IOException {
        List<Integer> found = new ArrayList<>();
        List<String> lines = Files.readAllLines(file);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains(text)) {
                found.add(i + 1);
            }
        }
        return found;
    }

    static void test(List<String> input, String text, List<Integer> expected) throws IOException {
        Path file = Path.of("find-test.txt");
        Files.write(file, input);
        List<Integer> got = findLines(file, text);
        Files.delete(file);

        String label = "find [" + text + "] in " + input + " -> " + got;
        if (!got.equals(expected)) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = List.of("the cat sat", "on the mat", "a dog barked", "the end");
        test(lines, "the", List.of(1, 2, 4));
        test(lines, "cat", List.of(1));
        test(lines, "fish", List.of());
        test(lines, "The", List.of());
        test(List.of(), "the", List.of());
    }
}
