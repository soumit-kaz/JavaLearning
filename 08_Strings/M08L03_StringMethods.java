public class M08L03_StringMethods {

    public static void main(String[] args) {
        String word = "Hello, World";

        // charAt reads one character; indexes go from 0 to length() - 1
        System.out.println("first: " + word.charAt(0));
        System.out.println("last: " + word.charAt(word.length() - 1));

        // an index outside that range throws an exception
        try {
            System.out.println(word.charAt(word.length()));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("charAt(length): StringIndexOutOfBoundsException");
        }

        // substring(begin, end) includes begin but NOT end
        System.out.println("substring(0, 5): " + word.substring(0, 5));
        System.out.println("substring(7): " + word.substring(7));
        System.out.println("last 3: " + word.substring(word.length() - 3));

        // begin == end gives ""
        System.out.println("substring(4, 4): [" + word.substring(4, 4) + "]");

        // isEmpty is true only for length 0; isBlank also for whitespace only
        System.out.println("\"   \".isEmpty(): " + "   ".isEmpty());
        System.out.println("\"   \".isBlank(): " + "   ".isBlank());

        // repeat copies a string n times
        System.out.println("-".repeat(10));

        // compareTo: negative, zero or positive, like dictionary order
        System.out.println("apple vs banana: " + "apple".compareTo("banana"));
        System.out.println("app vs apple: " + "app".compareTo("apple"));
        // capitals come before lower case, and digits compare as characters
        System.out.println("Zebra vs apple: " + "Zebra".compareTo("apple"));
        System.out.println("ignore case: " + "Zebra".compareToIgnoreCase("apple"));
        System.out.println("10 vs 9: " + "10".compareTo("9"));

        // switch on a String compares with equals, so case matters
        String day = "sat".toUpperCase();
        String kind = switch (day) {
            case "SAT", "SUN" -> "weekend";
            default -> "weekday";
        };
        System.out.println(day + ": " + kind);

        // String.valueOf turns any value into text
        System.out.println("valueOf: " + String.valueOf(42) + " " + String.valueOf(3.5));

        // parse methods turn text into numbers
        System.out.println("parseInt: " + (Integer.parseInt("-15") + 1));
        System.out.println("parseDouble: " + Double.parseDouble("3.75"));
        // parseBoolean never fails: anything but "true" (any case) is false
        System.out.println("parseBoolean yes: " + Boolean.parseBoolean("yes"));

        // bad number text throws NumberFormatException
        String[] inputs = {"12a", " 12", "3.0", "2147483648"};
        for (String input : inputs) {
            try {
                System.out.println("[" + input + "] -> " + Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("[" + input + "] -> NumberFormatException");
            }
        }
    }
}
