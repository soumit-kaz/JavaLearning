# 04 - Heaps

A binary heap is a complete binary tree stored in an array, where every parent comes before its children.
It keeps the smallest (or largest) value on top and is the usual way to build a priority queue.

## Lessons

| # | Lesson | What it shows |
|---|--------|---------------|
| 1 | [M1804L01_BinaryHeap](M1804L01_BinaryHeap.java) | Array min-heap: push with sift up, pop with sift down, O(n) heapify |
| 2 | [M1804L02_HeapSort](M1804L02_HeapSort.java) | In-place heap sort with a max-heap |
| 3 | [M1804L03_PriorityQueueFromScratch](M1804L03_PriorityQueueFromScratch.java) | Generic priority queue with a Comparator |
| 4 | [M1804L04_TwoHeapsMedian](M1804L04_TwoHeapsMedian.java) | Running median with a max-heap and a min-heap |

Run a lesson with `java M1804L01_BinaryHeap.java`. Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)

Not covered here: indexed heaps with decrease-key (used by some Dijkstra implementations).

## Big-O

| Operation | Time |
|-----------|------|
| peek | O(1) |
| push / pop | O(log n) |
| heapify n values | O(n) |
| heap sort | O(n log n), O(1) extra, not stable |
| search any value | O(n) |
| k-way merge of N values | O(N log k) |
| running median add / get | O(log n) / O(1) |

## Key points

- Index `i`: parent `(i - 1) / 2`, children `2i + 1` and `2i + 2`.
- Push: add at the end and sift up. Pop: move the last value to the root and sift down.
- Heapify sifts down from the last parent to the root; most nodes are near the bottom, so it is O(n).
- `java.util.PriorityQueue` is a min-heap; pass `Collections.reverseOrder()` for a max-heap.
- "Top k largest" uses a min-heap of size k (the k-th largest sits on top).
- The heap array is not sorted, and heaps are not stable (add a sequence number to break ties).
