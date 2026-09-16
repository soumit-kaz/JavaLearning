import java.util.Arrays;

public class M08P07_LongestCommonPrefix {
    public static void main(String[] args) {
        String[][] inputs = {
            {"flower", "flow", "flight"},
            {"dog", "racecar", "car"},
            {"interview", "internet", "interval"},
            {"alone"},
            {"", "abc"},
            {"ab", "a"}
        };
        String[] expected = {"fl", "", "inter", "alone", "", "a"};

        for (int t = 0; t < inputs.length; t++) {
            String[] words = inputs[t];

            // start with the first word and cut it until every word starts with it
            String prefix = words[0];
            for (int w = 1; w < words.length; w++) {
                while (!words[w].startsWith(prefix)) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                }
            }

            String result = prefix.equals(expected[t]) ? "PASS" : "FAIL";
            System.out.println(Arrays.toString(words) + " -> \"" + prefix + "\"  " + result);
        }
    }
}
