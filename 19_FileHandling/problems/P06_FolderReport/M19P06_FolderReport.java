import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class M19P06_FolderReport {

    // what is inside the folder, sorted by name
    static List<Path> filesIn(Path folder) throws IOException {
        try (Stream<Path> list = Files.list(folder)) {
            return list.sorted().toList();
        }
    }

    // how many bytes all the files hold together
    static long totalBytes(Path folder) throws IOException {
        long bytes = 0;
        for (Path file : filesIn(folder)) {
            bytes += Files.size(file);
        }
        return bytes;
    }

    // the name of the biggest file, or "none" for an empty folder
    static String biggestFile(Path folder) throws IOException {
        String biggest = "none";
        long biggestSize = -1;
        for (Path file : filesIn(folder)) {
            long size = Files.size(file);
            if (size > biggestSize) {
                biggestSize = size;
                biggest = file.getFileName().toString();
            }
        }
        return biggest;
    }

    static void test(List<String> names, List<String> contents,
                     int expectedCount, long expectedBytes, String expectedBiggest) throws IOException {
        Path folder = Path.of("report-test");
        Files.createDirectory(folder);
        for (int i = 0; i < names.size(); i++) {
            Files.writeString(folder.resolve(names.get(i)), contents.get(i));
        }

        int count = filesIn(folder).size();
        long bytes = totalBytes(folder);
        String biggest = biggestFile(folder);

        for (Path file : filesIn(folder)) {
            Files.delete(file);
        }
        Files.delete(folder);

        String label = names + " -> " + count + " files, " + bytes + " bytes, biggest " + biggest;
        if (count != expectedCount || bytes != expectedBytes || !biggest.equals(expectedBiggest)) {
            System.out.println(label + " FAIL, expected " + expectedCount + " files, "
                    + expectedBytes + " bytes, biggest " + expectedBiggest);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) throws IOException {
        test(List.of("a.txt", "b.txt"), List.of("12345", "1"), 2, 6, "a.txt");
        test(List.of("small.txt", "big.txt"), List.of("ab", "abcd"), 2, 6, "big.txt");
        test(List.of("only.txt"), List.of(""), 1, 0, "only.txt");
        test(List.of(), List.of(), 0, 0, "none");
    }
}
