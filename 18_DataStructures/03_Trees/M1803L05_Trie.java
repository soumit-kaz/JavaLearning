import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class M1803L05_Trie {

    // each node has one child slot per letter a-z
    static class Trie {
        static class Node {
            Node[] children = new Node[26];
            boolean isWord;
        }

        Node root = new Node();

        void insert(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                // create the path as we go
                if (node.children[c] == null) {
                    node.children[c] = new Node();
                }
                node = node.children[c];
            }
            node.isWord = true;
        }

        // follow the characters; null if the path does not exist
        Node find(String prefix) {
            Node node = root;
            for (int i = 0; i < prefix.length() && node != null; i++) {
                node = node.children[prefix.charAt(i) - 'a'];
            }
            return node;
        }

        // a full word must end on a node marked as a word
        boolean search(String word) {
            Node node = find(word);
            return node != null && node.isWord;
        }

        boolean startsWith(String prefix) {
            return find(prefix) != null;
        }

        // all stored words with this prefix, in sorted order
        List<String> wordsWithPrefix(String prefix) {
            List<String> out = new ArrayList<>();
            Node node = find(prefix);
            if (node != null) {
                collect(node, new StringBuilder(prefix), out);
            }
            return out;
        }

        // depth-first, children a to z, so the output is sorted
        void collect(Node node, StringBuilder path, List<String> out) {
            if (node.isWord) {
                out.add(path.toString());
            }
            for (int c = 0; c < 26; c++) {
                if (node.children[c] != null) {
                    path.append((char) ('a' + c));
                    collect(node.children[c], path, out);
                    path.deleteCharAt(path.length() - 1);
                }
            }
        }
    }

    static String randomWord(Random random) {
        StringBuilder sb = new StringBuilder();
        int length = 1 + random.nextInt(5);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(3)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        for (String word : new String[]{"car", "card", "care", "cat", "dog", "do"}) {
            trie.insert(word);
        }
        System.out.println("search car: " + trie.search("car"));
        System.out.println("search ca: " + trie.search("ca"));
        System.out.println("startsWith ca: " + trie.startsWith("ca"));
        System.out.println("startsWith x: " + trie.startsWith("x"));
        System.out.println("words with car: " + trie.wordsWithPrefix("car"));
        boolean ok = trie.search("do") && !trie.search("d") && !trie.search("cards")
                && trie.wordsWithPrefix("car").equals(List.of("car", "card", "care"))
                && trie.wordsWithPrefix("z").isEmpty()
                && trie.wordsWithPrefix("").size() == 6
                && !trie.search("");

        // random words compared with a sorted set
        Random random = new Random(18);
        Trie t = new Trie();
        TreeSet<String> reference = new TreeSet<>();
        for (int i = 0; i < 300; i++) {
            String word = randomWord(random);
            t.insert(word);
            reference.add(word);
        }
        for (int i = 0; i < 300; i++) {
            String query = randomWord(random);
            List<String> expected = new ArrayList<>();
            for (String word : reference) {
                if (word.startsWith(query)) {
                    expected.add(word);
                }
            }
            ok &= t.search(query) == reference.contains(query);
            ok &= t.startsWith(query) == !expected.isEmpty();
            ok &= t.wordsWithPrefix(query).equals(expected);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
