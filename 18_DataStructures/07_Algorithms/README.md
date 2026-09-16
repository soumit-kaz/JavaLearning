# 07 - Algorithms

Classic algorithms written from scratch: sorting, binary search, two pointers, sliding windows, backtracking and dynamic programming.
Each lesson prints a short demo, then `OK` when its self-check passes.

## Lessons

| # | Lesson | What it shows |
|---|--------|---------------|
| 1 | [M1807L01_BubbleSort](M1807L01_BubbleSort.java) | Swap neighbours; stop early when a pass makes no swaps (stable) |
| 2 | [M1807L02_SelectionSort](M1807L02_SelectionSort.java) | Move the smallest remaining value to the front; at most n - 1 swaps (unstable) |
| 3 | [M1807L03_InsertionSort](M1807L03_InsertionSort.java) | Insert each value into the sorted prefix; shifts equal inversions (stable) |
| 4 | [M1807L04_MergeSort](M1807L04_MergeSort.java) | Split, sort both halves, merge; top-down and bottom-up |
| 5 | [M1807L05_QuickSort](M1807L05_QuickSort.java) | Lomuto partition with a random pivot; three-way partition for duplicates |
| 6 | [M1807L06_CountingSort](M1807L06_CountingSort.java) | Count each value, place values with prefix sums; handles negatives |
| 7 | [M1807L07_RadixSort](M1807L07_RadixSort.java) | Stable counting sort on one decimal digit at a time |
| 8 | [M1807L08_BinarySearch](M1807L08_BinarySearch.java) | Halve a sorted range each step, loop and recursive |
| 9 | [M1807L09_BinarySearchVariants](M1807L09_BinarySearchVariants.java) | Lower and upper bound, counting a value, search on the answer (integer square root) |
| 10 | [M1807L10_TwoPointers](M1807L10_TwoPointers.java) | Pair with a given sum from both ends; read/write pointers to remove duplicates |
| 11 | [M1807L11_SlidingWindow](M1807L11_SlidingWindow.java) | Fixed window sum, shortest window with sum >= target, longest unique substring |
| 12 | [M1807L12_Backtracking](M1807L12_Backtracking.java) | Subsets and permutations: choose, explore, un-choose |
| 13 | [M1807L13_DynamicProgramming](M1807L13_DynamicProgramming.java) | Fibonacci tabulation; 0/1 knapsack with chosen items and a one-row version |
| 14 | [M1807L14_DPOnStrings](M1807L14_DPOnStrings.java) | Longest common subsequence (rebuilt) and edit distance |
| 15 | [M1807L15_LongestIncreasingSubsequence](M1807L15_LongestIncreasingSubsequence.java) | O(n^2) DP and O(n log n) with binary search on tails |

Run a lesson with `java M1807L01_BubbleSort.java`. Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)

N-Queens is a backtracking problem in [10_Functions](../../10_Functions/problems/PROBLEMS.md).

## Big-O

k = value range, d = number of digits, W = knapsack capacity.

| Algorithm | Best | Average | Worst | Extra space | Stable |
|-----------|------|---------|-------|-------------|--------|
| Bubble sort | O(n) | O(n^2) | O(n^2) | O(1) | yes |
| Selection sort | O(n^2) | O(n^2) | O(n^2) | O(1) | no |
| Insertion sort | O(n) | O(n^2) | O(n^2) | O(1) | yes |
| Merge sort | O(n log n) | O(n log n) | O(n log n) | O(n) | yes |
| Quick sort | O(n log n) | O(n log n) | O(n^2) | O(log n) | no |
| Counting sort | O(n + k) | O(n + k) | O(n + k) | O(n + k) | yes |
| Radix sort | O(d(n + 10)) | O(d(n + 10)) | O(d(n + 10)) | O(n) | yes |
| Binary search / bounds | O(1) | O(log n) | O(log n) | O(1) | - |
| Two pointers / sliding window | O(n) | O(n) | O(n) | O(1) to O(k) | - |
| Subsets / permutations | O(n 2^n) / O(n n!) | same | same | O(n) | - |
| Fibonacci table | O(n) | O(n) | O(n) | O(n) or O(1) | - |
| 0/1 knapsack | O(n W) | O(n W) | O(n W) | O(n W) or O(W) | - |
| LCS / edit distance | O(n m) | O(n m) | O(n m) | O(n m) | - |
| LIS | O(n log n) | O(n log n) | O(n log n) | O(n) | - |

## Key points

- A sort is stable if equal keys keep their input order; this matters when you sort by one field after another.
- Bubble, insertion, merge, counting and radix sort are stable; selection and quick sort are not.
- Java's `Arrays.sort` is stable (TimSort) for objects and uses dual-pivot quicksort for primitives.
- Insertion sort is fast on nearly sorted data; quicksort with a fixed pivot is O(n^2) on sorted data, so pick a random pivot.
- Radix sort only works because each digit pass is a stable counting sort.
- Binary search needs sorted data or a yes/no question whose answer flips once; use `low + (high - low) / 2`.
- Decide whether `high` is inside the range (`<=` loop) or just past it (`<` loop) and stick to it.
- Two pointers and sliding windows move each pointer forward only, so the total work is O(n).
- Backtracking: choose, explore, un-choose; save a copy of the current list, not the list itself.
- DP: define the state, the transition and the base case, then fill the table so every value you read is final.
- In 0/1 knapsack's one-row version, loop capacities backwards or items get reused.
