import java.util.Arrays;

public class M1801P03_PalindromeLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        // slow stops at the end of the first half
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // reverse the second half so we can walk it from the end
        ListNode secondHalf = reverse(slow.next);
        boolean same = true;
        ListNode p = head;
        ListNode q = secondHalf;
        while (q != null) {
            if (p.val != q.val) {
                same = false;
                break;
            }
            p = p.next;
            q = q.next;
        }
        // put the list back the way we found it
        slow.next = reverse(secondHalf);
        return same;
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

    static void test(int[] values, boolean expected) {
        ListNode head = build(values);
        boolean result = isPalindrome(head);
        // also check that the list was restored
        boolean pass = result == expected && show(head).equals(Arrays.toString(values));
        System.out.println(Arrays.toString(values) + " -> " + result + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[] {1, 2, 2, 1}, true);
        test(new int[] {1, 2}, false);
        test(new int[] {}, true);
        test(new int[] {7}, true);
        test(new int[] {1, 2, 3, 2, 1}, true);
        test(new int[] {1, 2, 3, 1, 1}, false);
        test(new int[] {1, 0, 0}, false);
    }
}
