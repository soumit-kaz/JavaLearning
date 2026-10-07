import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class M19P04_RemoveBlankLines {

    // rewrite the file without its blank lines, and with no spaces
    // left at the start or the end of a line
    static int clean(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        List<String> kept = new ArrayList<>();
        for (String line : lines) {
            if (!line.isBlank()) {
                kept.add(line.strip());
            }
        }
        Files.write(file, kept);
        return lines.size() - kept.size();
    }

    static void test(List<String> input, List<String> expected, int expectedRemoved) throws IOException {
        Path file = Path.of("clean-test.txt");
        Files.write(file, input);
        int removed = clean(file);
        List<String> got = Files.readAllLines(file);
        Files.delete(file);

        String label = input + " -> " + got + ", removed " + removed;
        if (!got.equals(expected) || removed != expectedRemoved) {
            System.out.println(label + " FAIL, expected " + expected + ", removed " + expectedRemoved);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) throws IOException {
        test(List.of("a", "", "b"), List.of("a", "b"), 1);
        test(List.of("  padded  ", "   ", "x"), List.of("padded", "x"), 1);
        test(List.of("a", "b"), List.of("a", "b"), 0);
        test(List.of("", "", ""), List.of(), 3);
        test(List.of(), List.of(), 0);
    }
}
