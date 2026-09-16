import java.util.Objects;

public class M08L02_StringEquality {

    public static void main(String[] args) {
        // literals with the same text share one object from the string pool
        String s1 = "java";
        String s2 = "java";

        // new, and text built at run time, make separate objects
        String s3 = new String("java");
        String part = "ja";
        String built = part + "va";

        // == asks "same object?", equals asks "same text?"
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("s1 == s3: " + (s1 == s3));
        System.out.println("s1 == built: " + (s1 == built));
        System.out.println("s1.equals(s3): " + s1.equals(s3));
        System.out.println("s1.equals(built): " + s1.equals(built));

        // intern() returns the pooled object with the same text
        System.out.println("s1 == s3.intern(): " + (s1 == s3.intern()));

        // literals and final constants are joined by the compiler, so they are pooled
        final String constant = "ja";
        System.out.println("\"ja\" + \"va\" == s1: " + ("ja" + "va" == s1));
        System.out.println("constant + \"va\" == s1: " + (constant + "va" == s1));

        // equals is case-sensitive; equalsIgnoreCase is not
        System.out.println("equals JAVA: " + s1.equals("JAVA"));
        System.out.println("equalsIgnoreCase JAVA: " + s1.equalsIgnoreCase("JAVA"));

        // null-safe ways: literal first, or Objects.equals
        String nothing = null;
        System.out.println("\"java\".equals(nothing): " + "java".equals(nothing));
        System.out.println("Objects.equals(nothing, null): " + Objects.equals(nothing, null));
    }
}
