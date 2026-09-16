import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

public class M1801L06_MonotonicStack {

    static int[] nextGreater(int[] values) {
        int[] result = new int[values.length];
        Arrays.fill(result, -1);
        // indexes still waiting for a bigger value; their values decrease bottom to top
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            // values[i] is the answer for every smaller value on the stack
            while (!stack.isEmpty() && values[stack.peek()] < values[i]) {
                result[stack.pop()] = values[i];
            }
            stack.push(i);
        }
        // indexes left on the stack have no bigger value: they stay -1
        return result;
    }

    // O(n*n) version for the self-check
    static int[] slowNextGreater(int[] values) {
        int[] result = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = -1;
            for (int j = i + 1; j < values.length && result[i] == -1; j++) {
                if (values[j] > values[i]) {
                    result[i] = values[j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] values = {2, 1, 2, 4, 3};
        System.out.println("values: " + Arrays.toString(values));
        System.out.println("next greater: " + Arrays.toString(nextGreater(values)));

        // known answer, then random arrays against the slow version
        boolean ok = Arrays.equals(nextGreater(values), new int[] {4, 2, 4, -1, -1});
        Random rnd = new Random(77);
        for (int t = 0; t < 500; t++) {
            int[] random = new int[rnd.nextInt(30)];
            for (int i = 0; i < random.length; i++) {
                random[i] = rnd.nextInt(10);
            }
            ok &= Arrays.equals(nextGreater(random), slowNextGreater(random));
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
