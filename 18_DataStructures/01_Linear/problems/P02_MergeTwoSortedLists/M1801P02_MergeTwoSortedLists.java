import java.util.Arrays;

public class M1801P02_MergeTwoSortedLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    static ListNode merge(ListNode a, ListNode b) {
        // dummy node so we never need a special case for the first node
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            // take the smaller head and move forward in that list
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        // attach whatever is left, it is already sorted
        tail.next = (a != null) ? a : b;
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

    static String show(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        for (ListNode cur = head; cur != null; cur = cur.next) {
            sb.append(cur.val).append(cur.next != null ? ", " : "");
        }
        return sb.append("]").toString();
    }

    static void test(int[] a, int[] b, int[] expected) {
        String result = show(merge(build(a), build(b)));
        boolean pass = result.equals(Arrays.toString(expected));
        System.out.println(Arrays.toString(a) + " + " + Arrays.toString(b) + " -> " + result + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[] {1, 2, 4}, new int[] {1, 3, 4}, new int[] {1, 1, 2, 3, 4, 4});
        test(new int[] {}, new int[] {}, new int[] {});
        test(new int[] {}, new int[] {0}, new int[] {0});
        test(new int[] {5}, new int[] {}, new int[] {5});
        test(new int[] {1, 3, 5, 7}, new int[] {2, 4, 6, 8, 10}, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 10});
        test(new int[] {-3, -1}, new int[] {-2, -2, 9}, new int[] {-3, -2, -2, -1, 9});
    }
}
