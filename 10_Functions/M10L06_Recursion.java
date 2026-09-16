public class M10L06_Recursion {

    static void countDown(int n) {
        // base case: stop calling
        if (n == 0) {
            System.out.println("go!");
            return;
        }
        System.out.println(n);
        // recursive case: call itself with a smaller value
        countDown(n - 1);
    }

    static void countUp(int n) {
        if (n == 0) {
            return;
        }
        // the call happens first, the print happens on the way back
        countUp(n - 1);
        System.out.println(n);
    }

    // n! = n * (n-1)!, and 0! = 1! = 1
    static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // the same idea, printing each call with indentation
    static long trace(int n, String indent) {
        System.out.println(indent + "factorial(" + n + ")");
        if (n <= 1) {
            return 1;
        }
        long result = n * trace(n - 1, indent + "  ");
        System.out.println(indent + "= " + result);
        return result;
    }

    // recursive: short, but every call uses stack memory
    static long sumRecursive(int n) {
        if (n == 0) {
            return 0;
        }
        return n + sumRecursive(n - 1);
    }

    // iterative: a loop needs no extra stack memory
    static long sumIterative(int n) {
        long total = 0;
        for (int i = 1; i <= n; i++) {
            total += i;
        }
        return total;
    }

    public static void main(String[] args) {
        countDown(3);
        countUp(3);

        System.out.println("5! = " + factorial(5));
        // 21! does not fit in a long and silently overflows
        System.out.println("21! = " + factorial(21));
        trace(3, "");

        System.out.println("sumRecursive(1000) = " + sumRecursive(1000));
        // too deep for recursion, easy for a loop
        System.out.println("sumIterative(10000000) = " + sumIterative(10_000_000));
    }
}
