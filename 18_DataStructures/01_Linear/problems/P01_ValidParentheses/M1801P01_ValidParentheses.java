import java.util.ArrayDeque;
import java.util.Deque;

public class M1801P01_ValidParentheses {

    static boolean isValid(String s) {
        Deque<Character> expected = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // for each opener, remember which closer we need later
            if (c == '(') {
                expected.push(')');
            } else if (c == '[') {
                expected.push(']');
            } else if (c == '{') {
                expected.push('}');
            } else if (expected.isEmpty() || expected.pop() != c) {
                // a closer must match the most recent opener
                return false;
            }
        }
        // every opener must have been closed
        return expected.isEmpty();
    }

    static void test(String s, boolean expected) {
        boolean result = isValid(s);
        System.out.println("\"" + s + "\" -> " + result + "  " + (result == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test("()", true);
        test("()[]{}", true);
        test("(]", false);
        test("([)]", false);
        test("{[]}", true);
        test("", true);
        test("(", false);
        test("]", false);
        test("{[()()]}[]", true);
    }
}
