public class M03L03_IncrementDecrement {

    public static void main(String[] args) {
        // ++ adds one, -- subtracts one
        int count = 5;
        count++;
        count++;
        count--;
        System.out.println("count = " + count);

        // postfix: use the old value, then add one
        int a = 5;
        int b = a++;
        System.out.println("a = " + a + ", b = " + b);

        // prefix: add one, then use the new value
        int c = 5;
        int d = ++c;
        System.out.println("c = " + c + ", d = " + d);

        // -- works the same way
        int e = 5;
        int f = e--;
        int g = --e;
        System.out.println("e = " + e + ", f = " + f + ", g = " + g);

        // ++ keeps the type, even for char
        char letter = 'a';
        letter++;
        System.out.println(letter);

        // puzzle: x = x++ puts the old value back, so nothing changes
        int y = 5;
        y = y++;
        System.out.println("y = " + y);

        // puzzle: operands are read left to right, so this is 5 + 7
        int i = 5;
        int j = i++ + ++i;
        System.out.println("i = " + i + ", j = " + j);

        // clear code: do the increment on its own line
        int k = 5;
        k++;
        System.out.println("k + k = " + (k + k));
    }
}
