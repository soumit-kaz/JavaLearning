public class M10L08_Memoization {

    static int calls = 0;

    // naive: fib(n) = fib(n-1) + fib(n-2), with fib(0) = 0 and fib(1) = 1
    static long fibSlow(int n) {
        calls++;
        if (n < 2) {
            return n;
        }
        return fibSlow(n - 1) + fibSlow(n - 2);
    }

    // memo[n] holds fib(n) once known; 0 means "not computed yet"
    static long[] memo = new long[91];

    static long fibMemo(int n) {
        calls++;
        if (n < 2) {
            return n;
        }
        // reuse a saved answer instead of computing it again
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = fibMemo(n - 1) + fibMemo(n - 2);
        return memo[n];
    }

    // a loop: fast, simple and no deep stack
    static long fibLoop(int n) {
        long previous = 0;
        long current = 1;
        for (int i = 0; i < n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static void main(String[] args) {
        // the same small values are computed again and again
        calls = 0;
        System.out.println("slow fib(25) = " + fibSlow(25) + ", calls = " + calls);

        calls = 0;
        System.out.println("memo fib(25) = " + fibMemo(25) + ", calls = " + calls);

        // already saved, so just one call
        calls = 0;
        System.out.println("memo fib(25) = " + fibMemo(25) + ", calls = " + calls);

        System.out.println("memo fib(90) = " + fibMemo(90));
        System.out.println("loop fib(90) = " + fibLoop(90));
    }
}
