import java.util.Arrays;

public class M1801P06_RemoveNthFromEnd {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    static ListNode removeNthFromEnd(ListNode head, int n) {
        // dummy node makes removing the head work like any other node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        // move fast n+1 steps ahead, so slow stops just before the target
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // skip over the target node
        slow.next = slow.next.next;
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

    static void test(int[] values, int n, int[] expected) {
        String result = show(removeNthFromEnd(build(values), n));
        boolean pass = result.equals(Arrays.toString(expected));
        System.out.println(Arrays.toString(values) + " n=" + n + " -> " + result + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[] {1, 2, 3, 4, 5}, 2, new int[] {1, 2, 3, 5});
        test(new int[] {1}, 1, new int[] {});
        test(new int[] {1, 2}, 1, new int[] {1});
        test(new int[] {1, 2}, 2, new int[] {2});
        test(new int[] {1, 2, 3, 4, 5}, 5, new int[] {2, 3, 4, 5});
        test(new int[] {7, 7, 7}, 2, new int[] {7, 7});
    }
}
