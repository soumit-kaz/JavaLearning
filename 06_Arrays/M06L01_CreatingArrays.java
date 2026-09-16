import java.util.Arrays;

public class M06L01_CreatingArrays {

    public static void main(String[] args) {
        // declare first, create later with new; the size can come from a variable
        int size = 3;
        int[] scores;
        scores = new int[size];
        System.out.println("scores = " + Arrays.toString(scores));

        // new arrays start with default values
        System.out.println("double: " + Arrays.toString(new double[2]));
        System.out.println("boolean: " + Arrays.toString(new boolean[2]));
        System.out.println("String: " + Arrays.toString(new String[2]));

        // a char array starts with the character whose code is 0, not a space
        char[] blank = new char[2];
        System.out.println("char code: " + (int) blank[0]);

        // the short {...} form works only in a declaration
        int[] primes = {2, 3, 5, 7};
        System.out.println("primes = " + Arrays.toString(primes));

        // assigning later needs the long form new int[] {...}
        int[] later;
        later = new int[] {10, 20};
        System.out.println("later = " + Arrays.toString(later));

        // smaller values are widened to the element type
        double[] widened = {1, 2.5f, 'A'};
        System.out.println("widened = " + Arrays.toString(widened));

        // with int[] a, b both are arrays; with int c[], d only c is an array
        int[] a, b;
        int c[], d;
        a = new int[] {1};
        b = new int[] {2};
        c = new int[] {3};
        d = 4;
        System.out.println("a b c d = " + a[0] + " " + b[0] + " " + c[0] + " " + d);

        // read an element with array[index]; the first index is 0
        System.out.println("first prime = " + primes[0]);

        // write with array[index] = value; ++ and += work on elements too
        int[] temps = new int[4];
        temps[0] = 21;
        temps[1] = temps[0] + 2;
        temps[2]++;
        temps[3] += 10;
        System.out.println("temps = " + Arrays.toString(temps));

        // the index can be any int expression
        int i = 2;
        temps[i + 1] = temps[i] * 50;
        System.out.println("temps[3] = " + temps[3]);
    }
}
