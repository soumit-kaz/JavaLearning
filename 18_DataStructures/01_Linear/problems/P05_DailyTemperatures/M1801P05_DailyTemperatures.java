import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class M1801P05_DailyTemperatures {

    static int[] dailyTemperatures(int[] temps) {
        int[] answer = new int[temps.length];
        // days that are still waiting for a warmer day
        Deque<Integer> waiting = new ArrayDeque<>();
        for (int today = 0; today < temps.length; today++) {
            // today is the answer for every colder day on the stack
            while (!waiting.isEmpty() && temps[waiting.peek()] < temps[today]) {
                int day = waiting.pop();
                answer[day] = today - day;
            }
            waiting.push(today);
        }
        // days left on the stack never get warmer, and their answer stays 0
        return answer;
    }

    static void test(int[] temps, int[] expected) {
        int[] result = dailyTemperatures(temps);
        boolean pass = Arrays.equals(result, expected);
        System.out.println(Arrays.toString(temps) + " -> " + Arrays.toString(result) + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[] {73, 74, 75, 71, 69, 72, 76, 73}, new int[] {1, 1, 4, 2, 1, 1, 0, 0});
        test(new int[] {30, 40, 50, 60}, new int[] {1, 1, 1, 0});
        test(new int[] {90, 80, 70}, new int[] {0, 0, 0});
        test(new int[] {50}, new int[] {0});
        test(new int[] {70, 70, 70, 71}, new int[] {3, 2, 1, 0});
        test(new int[] {}, new int[] {});
    }
}
