# 01 - Linear Structures: Problems

Each solution runs its own tests and prints one PASS or FAIL line per test.

## P01 - Valid Parentheses (Easy)
Given a string of `()[]{}` characters, return true if every bracket is closed by the same type, in the right order.
Example: `"()[]{}"` -> `true`
Approach: stack of expected closing brackets. Time O(n), space O(n).
Solution: [M1801P01_ValidParentheses.java](P01_ValidParentheses/M1801P01_ValidParentheses.java)

## P02 - Merge Two Sorted Lists (Easy)
Given two sorted linked lists, join their nodes into one sorted list and return its head.
Example: `1->2->4` and `1->3->4` -> `1->1->2->3->4->4`
Approach: dummy node and a tail pointer; attach the smaller head each step. Time O(n + m), space O(1).
Solution: [M1801P02_MergeTwoSortedLists.java](P02_MergeTwoSortedLists/M1801P02_MergeTwoSortedLists.java)

## P03 - Palindrome Linked List (Easy)
Return true if a singly linked list reads the same forwards and backwards. Use O(1) extra space and leave the list unchanged.
Example: `1->2->2->1` -> `true`
Approach: find the middle, reverse the second half, compare, reverse it back. Time O(n), space O(1).
Solution: [M1801P03_PalindromeLinkedList.java](P03_PalindromeLinkedList/M1801P03_PalindromeLinkedList.java)

## P04 - Add Two Numbers (Medium)
Two non-negative numbers are stored as linked lists with digits in reverse order (head is the ones digit).
Return their sum as a list in the same format. The numbers can be too big for `long`.
Example: `2->4->3` + `5->6->4` -> `7->0->8` (342 + 465 = 807)
Approach: school addition with a carry; loop while digits or a carry remain. Time O(max(n, m)), space O(max(n, m)).
Solution: [M1801P04_AddTwoNumbers.java](P04_AddTwoNumbers/M1801P04_AddTwoNumbers.java)

## P05 - Daily Temperatures (Medium)
For each day, return how many days you wait for a strictly warmer temperature, or 0 if none comes.
Example: `[73,74,75,71,69,72,76,73]` -> `[1,1,4,2,1,1,0,0]`
Approach: monotonic stack of days still waiting; a warmer day pops and answers them. Time O(n), space O(n).
Solution: [M1801P05_DailyTemperatures.java](P05_DailyTemperatures/M1801P05_DailyTemperatures.java)

## P06 - Remove N-th Node From End of List (Medium)
Remove the `n`-th node from the end of a linked list, using a single pass.
Example: `1->2->3->4->5`, n = 2 -> `1->2->3->5`
Approach: dummy node, move fast n + 1 steps ahead, then move both until fast is null. Time O(n), space O(1).
Solution: [M1801P06_RemoveNthFromEnd.java](P06_RemoveNthFromEnd/M1801P06_RemoveNthFromEnd.java)
