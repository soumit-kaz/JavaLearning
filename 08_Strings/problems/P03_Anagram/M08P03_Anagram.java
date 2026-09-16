public class M08P03_Anagram {
    public static void main(String[] args) {
        String[] firsts = {"listen", "rat", "a", "", "aacc", "anagram"};
        String[] seconds = {"silent", "car", "ab", "", "ccac", "nagaram"};
        boolean[] expected = {true, false, false, true, false, true};

        for (int t = 0; t < firsts.length; t++) {
            String first = firsts[t];
            String second = seconds[t];
            boolean isAnagram = first.length() == second.length();

            if (isAnagram) {
                // +1 for letters of the first word, -1 for letters of the second
                int[] counts = new int[26];
                for (int i = 0; i < first.length(); i++) {
                    counts[first.charAt(i) - 'a']++;
                    counts[second.charAt(i) - 'a']--;
                }
                // every count must be back to zero
                for (int i = 0; i < 26; i++) {
                    if (counts[i] != 0) {
                        isAnagram = false;
                    }
                }
            }

            String result = isAnagram == expected[t] ? "PASS" : "FAIL";
            System.out.println("\"" + first + "\", \"" + second + "\" -> " + isAnagram + "  " + result);
        }
    }
}
