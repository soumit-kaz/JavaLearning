import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class M19P05_ScoreReport {

    // the source file has one "name score" per line.
    // Write a report with one line per student, then the total, the average
    // and the best student. An empty source gives one line: no students.
    static void writeReport(Path source, Path report) throws IOException {
        List<String> lines = Files.readAllLines(source);
        List<String> out = new ArrayList<>();

        int total = 0;
        int best = -1;
        String bestName = "";

        for (String line : lines) {
            String[] parts = line.split(" ");
            String name = parts[0];
            int score = Integer.parseInt(parts[1]);

            out.add(name + ": " + score);
            total += score;
            if (score > best) {
                best = score;
                bestName = name;
            }
        }

        if (lines.isEmpty()) {
            out.add("no students");
        } else {
            out.add("total: " + total);
            out.add("average: " + String.format("%.1f", (double) total / lines.size()));
            out.add("best: " + bestName);
        }
        Files.write(report, out);
    }

    static void test(List<String> input, List<String> expected) throws IOException {
        Path source = Path.of("scores-source.txt");
        Path report = Path.of("scores-report.txt");
        Files.write(source, input);
        writeReport(source, report);
        List<String> got = Files.readAllLines(report);
        Files.delete(source);
        Files.delete(report);

        String label = input + " -> " + got;
        if (!got.equals(expected)) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) throws IOException {
        test(List.of("ann 90", "bob 72", "cy 85"),
                List.of("ann: 90", "bob: 72", "cy: 85", "total: 247", "average: 82.3", "best: ann"));
        test(List.of("solo 50"),
                List.of("solo: 50", "total: 50", "average: 50.0", "best: solo"));
        test(List.of("low 10", "high 100"),
                List.of("low: 10", "high: 100", "total: 110", "average: 55.0", "best: high"));
        test(List.of(), List.of("no students"));
    }
}
