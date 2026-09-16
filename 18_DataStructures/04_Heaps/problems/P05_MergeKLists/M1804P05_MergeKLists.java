import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class M1804P05_MergeKLists {

    static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    static ListNode mergeKLists(ListNode[] lists) {
        // the heap holds the current head of each list, smallest value on top
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));
        for (ListNode head : lists) {
            if (head != null) {
                heap.offer(head);
            }
        }
        // a dummy node avoids a special case for the first node
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode smallest = heap.poll();
            // reuse the node itself: just link it to the end of the result
            tail.next = smallest;
            tail = smallest;
            if (smallest.next != null) {
                heap.offer(smallest.next);
            }
        }
        return dummy.next;
    }

    static ListNode build(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    static List<Integer> toList(ListNode head) {
        List<Integer> out = new ArrayList<>();
        for (ListNode n = head; n != null; n = n.next) {
            out.add(n.value);
        }
        return out;
    }

    static List<Integer> merge(int[][] arrays) {
        ListNode[] lists = new ListNode[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            lists[i] = build(arrays[i]);
        }
        return toList(mergeKLists(lists));
    }

    static void test(int[][] arrays, List<Integer> expected) {
        List<Integer> got = merge(arrays);
        System.out.println(Arrays.deepToString(arrays) + " -> " + got + "  " + (got.equals(expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}}, List.of(1, 1, 2, 3, 4, 4, 5, 6));
        test(new int[][]{}, List.of());
        test(new int[][]{{}}, List.of());
        test(new int[][]{{}, {1}, {}}, List.of(1));
        test(new int[][]{{5, 5}, {5}}, List.of(5, 5, 5));

        Random rnd = new Random(10);
        boolean match = true;
        for (int t = 0; t < 300; t++) {
            int[][] arrays = new int[rnd.nextInt(8)][];
            List<Integer> expected = new ArrayList<>();
            for (int i = 0; i < arrays.length; i++) {
                arrays[i] = new int[rnd.nextInt(8)];
                for (int j = 0; j < arrays[i].length; j++) {
                    arrays[i][j] = rnd.nextInt(40) - 20;
                    expected.add(arrays[i][j]);
                }
                Arrays.sort(arrays[i]);
            }
            Collections.sort(expected);
            match &= merge(arrays).equals(expected);
        }
        System.out.println("random 300 cases -> " + (match ? "match" : "differ") + "  " + (match ? "PASS" : "FAIL"));
    }
}
