import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class M08L11_RegexBasics {

    public static void main(String[] args) {
        // matches checks the WHOLE string; \\d+ means one or more digits
        System.out.println("12345: " + "12345".matches("\\d+"));
        System.out.println("abc123: " + "abc123".matches("\\d+"));

        // [a-z] is one lower-case letter, {3} means exactly three times
        System.out.println("abc: " + "abc".matches("[a-z]{3}"));

        // a date shape: 4 digits, dash, 2 digits, dash, 2 digits
        System.out.println("date: " + "2024-03-09".matches("\\d{4}-\\d{2}-\\d{2}"));

        // (?i) at the start ignores case
        System.out.println("JAVA: " + "JAVA".matches("(?i)java"));

        // replaceAll: squeeze runs of spaces, remove everything that is not a letter
        System.out.println("a   b    c".replaceAll(" +", " "));
        System.out.println("R2-D2 & C-3PO".replaceAll("[^A-Za-z]", ""));

        // $1 and $2 in the replacement are the (groups)
        System.out.println("Smith John".replaceAll("(\\w+) (\\w+)", "$2 $1"));

        // square brackets list several separators for split
        System.out.println(Arrays.toString("a,b;c d".split("[,; ]")));

        // Pattern.quote makes any text safe to use literally
        System.out.println(Arrays.toString("1$2$3".split(Pattern.quote("$"))));

        // Pattern and Matcher find each match inside a longer text
        Matcher number = Pattern.compile("\\d+").matcher("I have 3 cats and 12 fish");
        while (number.find()) {
            System.out.println(number.group() + " at " + number.start());
        }

        // groups pull out parts of a match
        Matcher date = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})").matcher("Due 2024-03-09");
        if (date.find()) {
            System.out.println("year " + date.group(1) + ", month " + date.group(2) + ", day " + date.group(3));
        }

        // an invalid pattern throws PatternSyntaxException when the program runs
        try {
            System.out.println("a*b".split("*").length);
        } catch (PatternSyntaxException e) {
            System.out.println("PatternSyntaxException");
        }
    }
}
