import java.util.Locale;

public class M08L06_ChangingStrings {

    public static void main(String[] args) {
        String word = "java";

        // strings are immutable: the result is a NEW string, here it is thrown away
        word.toUpperCase();
        System.out.println("ignored result: " + word);

        // store the result to keep it
        String upper = word.toUpperCase();
        System.out.println("upper: " + upper);

        // another variable keeps pointing at the old object
        String first = "abc";
        String second = first;
        first = first + "d";
        System.out.println("first: " + first + ", second: " + second);

        // replace changes every match of a char or of plain text
        String text = "a.b.c.a";
        System.out.println(text.replace('a', 'x'));
        System.out.println(text.replace(".", "-"));
        // replacing with "" removes text
        System.out.println("1,234,567".replace(",", ""));

        // replaceAll uses a regex, where . means ANY character
        System.out.println(text.replaceAll(".", "-"));
        System.out.println(text.replaceAll("\\.", "-"));
        // replaceFirst changes only the first match
        System.out.println(text.replaceFirst("a", "A"));
        System.out.println("original: " + text);

        // changing case affects letters only
        System.out.println("Hello 123".toUpperCase());
        System.out.println("Hello 123".toLowerCase());

        // capitalize the first letter
        String name = "alice";
        System.out.println(name.substring(0, 1).toUpperCase() + name.substring(1));

        // case rules depend on the language: Turkish lower-cases I to a dotless i
        System.out.println("turkish: " + "TITLE".toLowerCase(Locale.of("tr")).equals("title"));
        // so use Locale.ROOT for program text like commands and keys
        System.out.println("QUIT".toLowerCase(Locale.ROOT));

        // strip removes whitespace at both ends, never in the middle
        String padded = "  \t hi  there \n ";
        System.out.println("[" + padded.strip() + "]");
        System.out.println("[" + "  hi  ".stripLeading() + "]");
        System.out.println("[" + "  hi  ".stripTrailing() + "]");

        // trim is older: it misses Unicode spaces like the em space, strip does not
        String emPadded = (char) 8195 + "hi";
        System.out.println("trim length: " + emPadded.trim().length());
        System.out.println("strip length: " + emPadded.strip().length());
    }
}
