public class M01L04_EscapeSequences {

    public static void main(String[] args) {
        // \n starts a new line inside the text
        System.out.println("Line one\nLine two");

        // \t jumps to the next tab position
        System.out.println("Name\tAge");
        System.out.println("Ada\t36");

        // \" prints a double quote
        System.out.println("She said \"Hello\"");

        // \\ prints one backslash
        System.out.println("C:\\Users\\Ada");

        // a single quote needs no backslash inside double quotes
        System.out.println("It's easy");

        // careful: here \t and \n are escapes, not the letters t and n
        System.out.println("C:\temp\new");
    }
}
