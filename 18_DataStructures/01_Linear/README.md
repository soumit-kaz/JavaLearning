# 01 - Linear Structures

Linear structures keep items in a row, one after another. Each lesson builds one from scratch and checks it against the JDK.

## Lessons

| # | Lesson | What it shows |
|---|--------|---------------|
| 1 | [M1801L01_DynamicArray](M1801L01_DynamicArray.java) | Growable array like `ArrayList`: doubling, shifting, shrinking |
| 2 | [M1801L02_SinglyLinkedList](M1801L02_SinglyLinkedList.java) | Add/remove with a dummy node, reverse, middle node, Floyd cycle detection |
| 3 | [M1801L03_DoublyLinkedList](M1801L03_DoublyLinkedList.java) | Sentinel nodes, add/remove at both ends, O(1) remove of a known node |
| 4 | [M1801L04_Stack](M1801L04_Stack.java) | Array-backed and linked stacks, balanced brackets |
| 5 | [M1801L05_Queue](M1801L05_Queue.java) | Circular array queue and linked queue |
| 6 | [M1801L06_MonotonicStack](M1801L06_MonotonicStack.java) | Next greater element with a decreasing stack |
| 7 | [M1801L07_MonotonicQueue](M1801L07_MonotonicQueue.java) | Sliding window maximum with a decreasing deque |

Run a lesson with `java M1801L01_DynamicArray.java`. Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)

Not covered here: deque from scratch, circular linked lists, infix to postfix, min stack, queue via stacks, stack via queues.

## Big-O

| Structure | Index access | Search | Add/remove front | Add/remove back | Middle |
|-----------|--------------|--------|------------------|-----------------|--------|
| Dynamic array | O(1) | O(n) | O(n) | O(1) amortized | O(n) |
| Singly linked list | O(n) | O(n) | O(1) | add O(1) with tail, remove O(n) | O(1) relink after O(n) walk |
| Doubly linked list | O(n) | O(n) | O(1) | O(1) | O(1) with the node |
| Stack / queue / deque | - | - | O(1) | O(1) | - |
| Monotonic stack / deque | - | - | - | O(1) amortized per item | - |

## Key points

- A dynamic array doubles when full, so appending is O(1) amortized; inserting in the middle shifts items.
- Save `next` before changing a link, and update `tail` when a list becomes empty.
- A dummy or sentinel node removes the special case for the head (and tail).
- Floyd's slow/fast pointers find the middle node and detect a cycle in O(1) space.
- A circular array wraps indexes with `%`, so freed slots are reused.
- Prefer `ArrayDeque` over `java.util.Stack` for stacks and queues.
- A monotonic stack or deque drops items that can never be the answer; each item is pushed and popped once, so O(n) total.
