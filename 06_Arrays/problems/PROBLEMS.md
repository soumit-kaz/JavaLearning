# 06 - Arrays: Problems

Each solution runs its own tests and prints `PASS` or `FAIL` for each one.

## P01 - Second Largest (Easy)
Find the second largest distinct value. Print `none` if there is none.
Example: `[3, 5, 1]` -> `3`, `[5, 5, 5]` -> `none`
Solution: [M06P01_SecondLargest.java](P01_SecondLargest/M06P01_SecondLargest.java)

## P02 - Reverse an Array (Easy)
Reverse an array in place, without a second array.
Example: `[1, 2, 3]` -> `[3, 2, 1]`
Solution: [M06P02_ReverseArray.java](P02_ReverseArray/M06P02_ReverseArray.java)

## P03 - Move Zeros (Easy)
Move all zeros to the end in place, keeping the order of the other values.
Example: `[0, 1, 0, 3, 12]` -> `[1, 3, 12, 0, 0]`
Solution: [M06P03_MoveZeros.java](P03_MoveZeros/M06P03_MoveZeros.java)

## P04 - Rotate an Array (Easy)
Rotate an array right by `k` steps. `k` may be larger than the length, or negative (rotate left).
Example: `[1, 2, 3, 4, 5, 6, 7], k=3` -> `[5, 6, 7, 1, 2, 3, 4]`
Solution: [M06P04_RotateArray.java](P04_RotateArray/M06P04_RotateArray.java)

## P05 - Remove Duplicates from a Sorted Array (Easy)
Keep each value once, in place. Print the new length and the kept elements.
Example: `[1, 1, 2]` -> `2 [1, 2]`
Solution: [M06P05_RemoveDuplicatesSorted.java](P05_RemoveDuplicatesSorted/M06P05_RemoveDuplicatesSorted.java)

## P06 - Merge Two Sorted Arrays (Easy)
Merge two sorted arrays into one new sorted array.
Example: `[1, 4, 7] + [2, 3, 8, 9]` -> `[1, 2, 3, 4, 7, 8, 9]`
Solution: [M06P06_MergeSortedArrays.java](P06_MergeSortedArrays/M06P06_MergeSortedArrays.java)

## P07 - Missing Number (Easy)
An array holds `n` different numbers from `0..n`. Find the missing one.
Example: `[3, 0, 1]` -> `2`
Solution: [M06P07_MissingNumber.java](P07_MissingNumber/M06P07_MissingNumber.java)

## P08 - Maximum Subarray Sum (Medium)
Find the largest sum of a non-empty run of neighboring elements (Kadane's algorithm).
Example: `[-2, 1, -3, 4, -1, 2, 1, -5, 4]` -> `6` (from `[4, -1, 2, 1]`)
Solution: [M06P08_MaxSubarraySum.java](P08_MaxSubarraySum/M06P08_MaxSubarraySum.java)
