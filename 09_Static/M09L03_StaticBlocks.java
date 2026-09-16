import java.util.Arrays;

public class M09L03_StaticBlocks {

    static int[] squares = new int[5];

    // a static final can get its value inside a static block
    static final int MAX_SCORE;

    // static block: runs once, before main starts; good for setup that needs a loop
    static {
        System.out.println("static block 1");
        for (int i = 0; i < squares.length; i++) {
            squares[i] = i * i;
        }
    }

    // several static blocks run from top to bottom
    static {
        System.out.println("static block 2");
        MAX_SCORE = squares[4] * 10;
    }

    public static void main(String[] args) {
        System.out.println("main");
        System.out.println("squares = " + Arrays.toString(squares));
        System.out.println("MAX_SCORE = " + MAX_SCORE);
    }

    // a static block placed after main still runs before main
    static {
        System.out.println("static block 3");
    }
}
