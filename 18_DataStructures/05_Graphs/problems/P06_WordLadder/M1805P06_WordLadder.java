import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class M1805P06_WordLadder {

    static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);
        if (!dictionary.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        visited.add(beginWord);
        // level = number of words in the sequence so far
        int level = 1;
        while (!queue.isEmpty()) {
            // handle one whole level at a time
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return level;
                }
                char[] letters = word.toCharArray();
                // neighbors: change one letter to any other letter
                for (int pos = 0; pos < letters.length; pos++) {
                    char old = letters[pos];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        letters[pos] = ch;
                        String next = new String(letters);
                        if (dictionary.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            queue.add(next);
                        }
                    }
                    // put the letter back before trying the next position
                    letters[pos] = old;
                }
            }
            level++;
        }
        return 0;
    }

    static void test(String begin, String end, List<String> words, int expected) {
        int got = ladderLength(begin, end, words);
        System.out.println(begin + " -> " + end + " " + words + " -> " + got + "  " + (got == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog"), 5);
        test("hit", "cog", List.of("hot", "dot", "dog", "lot", "log"), 0);
        test("a", "c", List.of("a", "b", "c"), 2);
        test("hot", "dog", List.of("hot", "dog"), 0);
        test("leet", "code", List.of("lest", "leet", "lose", "code", "lode", "robe", "lost"), 6);
        test("same", "same", List.of("same"), 1);
    }
}
