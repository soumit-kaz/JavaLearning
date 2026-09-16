public class M08L01_StringBasics {

    public static void main(String[] args) {
        // a literal is text in double quotes; length() counts characters, spaces too
        String greeting = "Hi, Bob!";
        System.out.println("length: " + greeting.length());

        // escapes put quotes, backslashes and line breaks inside a literal
        System.out.println("She said \"hi\" in C:\\temp");
        System.out.println("line 1\nline 2");

        // an empty string is a real object; null means no object at all
        String empty = "";
        String nothing = null;
        System.out.println("empty length: " + empty.length());
        System.out.println("nothing: " + nothing);

        // calling a method on null throws NullPointerException
        try {
            System.out.println(nothing.length());
        } catch (NullPointerException e) {
            System.out.println("nothing.length(): NullPointerException");
        }

        // + works left to right: once a String appears, the rest is joined as text
        System.out.println("x" + 1 + 2);
        System.out.println(1 + 2 + "x");
        System.out.println("x" + (1 + 2));

        // char + char is number addition, not joining
        System.out.println('a' + 'b');
        System.out.println("" + 'a' + 'b');

        // += adds text to the end
        String name = "Ann";
        name += " Lee";
        System.out.println("name: " + name);

        // null joined with + becomes the text "null", even with +=
        String result = null;
        result += "!";
        System.out.println(result);
    }
}
