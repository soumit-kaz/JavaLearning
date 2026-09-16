public class M08P04_ReverseWords {
    public static void main(String[] args) {
        String[] inputs = {"the sky is blue", "  hello world  ", "a good   example", "single", "   "};
        String[] expected = {"blue is sky the", "world hello", "example good a", "single", ""};

        for (int t = 0; t < inputs.length; t++) {
            String text = inputs[t];
            String answer = "";

            // strip first; an all-space input becomes empty and has no words
            String trimmed = text.strip();
            if (!trimmed.isEmpty()) {
                // \\s+ treats several spaces as one separator
                String[] words = trimmed.split("\\s+");
                StringBuilder sb = new StringBuilder();
                for (int i = words.length - 1; i >= 0; i--) {
                    sb.append(words[i]);
                    if (i > 0) {
                        sb.append(' ');
                    }
                }
                answer = sb.toString();
            }

            String result = answer.equals(expected[t]) ? "PASS" : "FAIL";
            System.out.println("\"" + text + "\" -> \"" + answer + "\"  " + result);
        }
    }
}
