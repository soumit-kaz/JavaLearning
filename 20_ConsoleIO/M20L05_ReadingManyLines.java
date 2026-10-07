import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class M20L05_ReadingManyLines {

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();

        try (Scanner in = new Scanner(System.in)) {

            if (!in.hasNextLine()) {
                System.out.println("No input. Run this lesson yourself and type a few lines.");
                System.out.println("Finish the input with Ctrl+D on macOS and Linux, or Ctrl+Z on Windows.");
                return;
            }

            // read line after line until the input is finished.
            // hasNextLine turns false when there is nothing left.
            while (in.hasNextLine()) {
                String line = in.nextLine();

                // stop early when the person types "end"
                if (line.equals("end")) {
                    System.out.println("stopping at 'end'");
                    break;
                }
                lines.add(line);
                System.out.println("line " + lines.size() + ": " + line);
            }
        }

        System.out.println("read " + lines.size() + " lines");

        // add up the lines that are numbers
        int total = 0;
        int numbers = 0;
        for (String line : lines) {
            try {
                total += Integer.parseInt(line.strip());
                numbers++;
            } catch (NumberFormatException e) {
                // not a number, so skip this line
            }
        }
        System.out.println(numbers + " of them were numbers, and they add up to " + total);
    }
}
