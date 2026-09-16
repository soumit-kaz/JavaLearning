import java.util.Arrays;
import java.util.StringJoiner;

public class M08L05_SplittingStrings {

    public static void main(String[] args) {
        // split cuts text into an array at each separator
        String[] colors = "red,green,blue".split(",");
        System.out.println(Arrays.toString(colors));

        // separators in a row give empty parts, but empty parts at the END are dropped
        System.out.println(Arrays.toString("a,,b".split(",")));
        System.out.println(Arrays.toString("a,b,,".split(",")));

        // a limit of -1 keeps them; a limit of 2 gives at most 2 parts
        System.out.println(Arrays.toString("a,b,,".split(",", -1)));
        System.out.println(Arrays.toString("note=a=b".split("=", 2)));

        // splitting "" gives ONE empty part
        System.out.println("parts of \"\": " + "".split(",").length);

        // split takes a regex: \\s+ means one or more whitespace characters
        System.out.println(Arrays.toString("one two  three".split(" ")));
        System.out.println(Arrays.toString("  one two  three".strip().split("\\s+")));

        // in a regex . means ANY character, so escape it as \\.
        System.out.println("split(\".\") parts: " + "192.168.0.1".split(".").length);
        System.out.println(Arrays.toString("192.168.0.1".split("\\.")));
        System.out.println(Arrays.toString("a|b".split("\\|")));

        // String.join is the opposite of split
        System.out.println(String.join(" + ", colors));

        // StringJoiner adds a separator, a prefix and a suffix
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (String color : colors) {
            joiner.add(color);
        }
        System.out.println(joiner);
    }
}
