import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class M19P02_LineNumbers {

    // copy source to target, with "1: ", "2: " and so on in front of each line
    static void addLineNumbers(Path source, Path target) throws IOException {
        List<String> lines = Files.readAllLines(source);
        List<String> numbered = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            numbered.add((i + 1) + ": " + lines.get(i));
        }
        Files.write(target, numbered);
    }

    static void test(List<String> input, List<String> expected) throws IOException {
        Path source = Path.of("numbers-source.txt");
        Path target = Path.of("numbers-target.txt");
        Files.write(source, input);
        addLineNumbers(source, target);
        List<String> got = Files.readAllLines(target);
        Files.delete(source);
        Files.delete(target);

        String label = input + " -> " + got;
        if (!got.equals(expected)) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) throws IOException {
        test(List.of("alpha", "beta"), List.of("1: alpha", "2: beta"));
        test(List.of("only one"), List.of("1: only one"));
        test(List.of(), List.of());
        test(List.of("a", "", "b"), List.of("1: a", "2: ", "3: b"));
    }
}
