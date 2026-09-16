# 04 - Heaps: Problems

Each solution runs its own tests and prints one PASS or FAIL line per test.

## P01 - Kth Largest Element in a Stream (Easy)
Build a class that takes `k` and some starting numbers; each `add(value)` returns the k-th largest number seen so far (`null` until k numbers were seen).
Example: k = 3, start `[4, 5, 8, 2]`; add 3, 5, 10, 9, 4 -> `4, 5, 5, 8, 8`
Approach: min-heap holding only the k largest; its root is the answer. Time O(log k) per add, space O(k).
Solution: [M1804P01_KthLargestStream.java](P01_KthLargestStream/M1804P01_KthLargestStream.java)

## P02 - Last Stone Weight (Easy)
Each turn smash the two heaviest stones `y >= x`: equal stones both vanish, otherwise a stone of weight `y - x` remains. Return the last stone's weight, or 0.
Example: `[2, 7, 4, 1, 8, 1]` -> `1`
Approach: max-heap simulation, push back the non-zero difference. Time O(n log n), space O(n).
Solution: [M1804P02_LastStoneWeight.java](P02_LastStoneWeight/M1804P02_LastStoneWeight.java)

## P03 - K Closest Points to Origin (Medium)
Return the `k` points closest to `(0, 0)`, sorted by (distance, x, y).
Example: `[(3,3), (5,-1), (-2,4)]`, k = 2 -> `[(3,3), (-2,4)]`
Approach: max-heap of size k on squared distance (as `long`), drop the top when it grows past k. Time O(n log k), space O(k).
Solution: [M1804P03_KClosestPoints.java](P03_KClosestPoints/M1804P03_KClosestPoints.java)

## P04 - Meeting Rooms II (Medium)
Given meetings `[start, end)`, return the minimum number of rooms so overlapping meetings never share one. A meeting ending at t frees its room for one starting at t.
Example: `[[0,30],[5,10],[15,20]]` -> `2`
Approach: sort by start, min-heap of end times; reuse a room when the earliest end is `<= start`. Time O(n log n), space O(n).
Solution: [M1804P04_MeetingRooms.java](P04_MeetingRooms/M1804P04_MeetingRooms.java)

## P05 - Merge k Sorted Lists (Hard)
Merge `k` sorted linked lists into one sorted linked list, reusing the nodes.
Example: `[[1,4,5],[1,3,4],[2,6]]` -> `1,1,2,3,4,4,5,6`
Approach: min-heap of list heads, poll the smallest, link it, push its `next`. Time O(N log k), space O(k).
Solution: [M1804P05_MergeKLists.java](P05_MergeKLists/M1804P05_MergeKLists.java)
