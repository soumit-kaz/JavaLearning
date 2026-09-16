public class M08P06_StringCompression {
    public static void main(String[] args) {
        String[] inputs = {"aabcccccaaa", "abc", "aaaaaaaaaaaa", "", "aabb", "zzzzy"};
        String[] expected = {"a2b1c5a3", "abc", "a12", "", "aabb", "z4y1"};

        for (int t = 0; t < inputs.length; t++) {
            String text = inputs[t];
            StringBuilder sb = new StringBuilder();

            int i = 0;
            while (i < text.length()) {
                // find where the run of the same letter ends
                char letter = text.charAt(i);
                int end = i;
                while (end < text.length() && text.charAt(end) == letter) {
                    end++;
                }
                sb.append(letter).append(end - i);
                i = end;
            }

            // keep the original if compressing does not make it shorter
            String answer = sb.length() < text.length() ? sb.toString() : text;

            String result = answer.equals(expected[t]) ? "PASS" : "FAIL";
            System.out.println("\"" + text + "\" -> \"" + answer + "\"  " + result);
        }
    }
}
