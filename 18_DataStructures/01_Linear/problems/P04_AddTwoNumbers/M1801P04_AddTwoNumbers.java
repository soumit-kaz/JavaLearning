public class M1801P04_AddTwoNumbers {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    static ListNode add(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;
        // keep going while there are digits left or a carry to write
        while (a != null || b != null || carry != 0) {
            int sum = carry;
            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }
            // write the last digit and carry the rest
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }
        return dummy.next;
    }

    static ListNode fromNumber(String number) {
        // digits are stored in reverse order: ones digit first
        ListNode head = null;
        for (char c : number.toCharArray()) {
            ListNode node = new ListNode(c - '0');
            node.next = head;
            head = node;
        }
        return head;
    }

    static String toNumber(ListNode head) {
        StringBuilder sb = new StringBuilder();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            sb.append(cur.val);
        }
        return sb.reverse().toString();
    }

    static void test(String x, String y, String expected) {
        String result = toNumber(add(fromNumber(x), fromNumber(y)));
        System.out.println(x + " + " + y + " -> " + result + "  " + (result.equals(expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test("342", "465", "807");
        test("0", "0", "0");
        test("9999999", "9999", "10009998");
        test("5", "5", "10");
        test("1", "99999", "100000");
        test("123456789012345678901234567890", "987654321098765432109876543210", "1111111110111111111011111111100");
    }
}
