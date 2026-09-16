public class M08P01_CountVowels {
    public static void main(String[] args) {
        String[] inputs = {"hello", "AEIOU", "rhythm", "", "Programming in Java"};
        int[] expected = {2, 5, 0, 0, 6};

        for (int t = 0; t < inputs.length; t++) {
            // lower-case once so 'A' and 'a' are both found in "aeiou"
            int count = 0;
            for (char c : inputs[t].toLowerCase().toCharArray()) {
                if ("aeiou".indexOf(c) >= 0) {
                    count++;
                }
            }

            String result = count == expected[t] ? "PASS" : "FAIL";
            System.out.println("\"" + inputs[t] + "\" -> " + count + "  " + result);
        }
    }
}
