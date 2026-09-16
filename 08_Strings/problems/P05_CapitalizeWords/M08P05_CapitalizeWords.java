public class M08P05_CapitalizeWords {
    public static void main(String[] args) {
        String[] inputs = {"hello world", "hELLO wORLD", "  java  is fun", "a", ""};
        String[] expected = {"Hello World", "Hello World", "  Java  Is Fun", "A", ""};

        for (int t = 0; t < inputs.length; t++) {
            String text = inputs[t];
            StringBuilder sb = new StringBuilder();

            // a letter starts a word when the character before it is a space (or there is none)
            boolean startOfWord = true;
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c == ' ') {
                    sb.append(c);
                    startOfWord = true;
                } else if (startOfWord) {
                    sb.append(Character.toUpperCase(c));
                    startOfWord = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
            String answer = sb.toString();

            String result = answer.equals(expected[t]) ? "PASS" : "FAIL";
            System.out.println("\"" + text + "\" -> \"" + answer + "\"  " + result);
        }
    }
}
