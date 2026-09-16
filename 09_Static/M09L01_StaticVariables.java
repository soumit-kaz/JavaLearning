public class M09L01_StaticVariables {

    // static variable: belongs to the class, lives outside main
    static int score = 10;

    // static variables get a default value: 0, false, null
    static int level;
    static boolean gameOver;
    static String playerName;

    // static final: a constant that can never change, written in UPPER_CASE
    static final double TAX_RATE = 0.15;
    static final int MAX_PLAYERS = 4;

    // a constant can be built from other constants
    static final int DAYS_IN_WEEK = 7;
    static final int HOURS_IN_WEEK = DAYS_IN_WEEK * 24;

    public static void main(String[] args) {
        // change it like any variable
        score = score + 5;
        System.out.println("score = " + score);

        // the class name in front also works
        M09L01_StaticVariables.score++;
        System.out.println("score = " + M09L01_StaticVariables.score);

        System.out.println("level = " + level + ", gameOver = " + gameOver + ", playerName = " + playerName);

        // a local variable has NO default value; it must be assigned before use
        int lives = 3;
        System.out.println("lives = " + lives);

        // a local variable with the same name hides the static one
        int score = 99;
        System.out.println("local score = " + score);
        System.out.println("static score = " + M09L01_StaticVariables.score);

        System.out.println("tax = " + 200.0 * TAX_RATE);
        System.out.println("HOURS_IN_WEEK = " + HOURS_IN_WEEK);
        // MAX_PLAYERS = 5;  would not compile: a final variable cannot be changed

        // a constant can be a switch case label
        int players = 4;
        String room = switch (players) {
            case MAX_PLAYERS -> "full";
            default -> "open";
        };
        System.out.println("room = " + room);

        // Java classes have their own static constants
        System.out.println("Math.PI = " + Math.PI);
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
    }
}
