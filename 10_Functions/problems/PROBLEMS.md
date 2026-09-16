# 10 - Functions: Problems

## P01 - DigitalRoot (Easy)

Given `n >= 0`, keep replacing it with the sum of its digits until one digit is left.
Return that digit (the root) and how many sums it took (the steps). Use small recursive methods.

Example: `38 -> 11 -> 2` gives `root 2, steps 2`.

Solution: [M10P01_DigitalRoot.java](P01_DigitalRoot/M10P01_DigitalRoot.java)

## P02 - PassByValueReverse (Easy)

Write `reverseInPlace(int[] a)`, which reverses the caller's array, and `reversedCopy(int[] a)`, which returns a new reversed array.
Explain why a method that only does `a = reversedCopy(a);` changes nothing for the caller.

Example: `reverseInPlace([1, 2, 3])` leaves the caller with `[3, 2, 1]`.

Solution: [M10P02_PassByValueReverse.java](P02_PassByValueReverse/M10P02_PassByValueReverse.java)

## P03 - VarargsStats (Easy)

Write `min`, `max` and `average` that take one or more ints, declared as `(int first, int... rest)`.
Sum into a `long` so large values do not overflow.

Example: `3, 9, -2, 6` gives `-2 9 4.0`.

Solution: [M10P03_VarargsStats.java](P03_VarargsStats/M10P03_VarargsStats.java)

## P04 - ClimbStairs (Medium)

You climb `n` stairs taking 1, 2 or 3 steps at a time. Count the different ways to reach the top.
Use `ways(n) = ways(n-1) + ways(n-2) + ways(n-3)` and save answers in a `long[]` memo.

Example: `n = 3` gives `4` (1+1+1, 1+2, 2+1, 3).

Solution: [M10P04_ClimbStairs.java](P04_ClimbStairs/M10P04_ClimbStairs.java)

## P05 - FastPowerMod (Medium)

Compute `base^exp % mod` for a huge `exp`, once recursively and once with a loop.
Halve the exponent each step and take `% mod` after every multiplication.

Example: `2^10 mod 1000` gives `24`.

Solution: [M10P05_FastPowerMod.java](P05_FastPowerMod/M10P05_FastPowerMod.java)

## P06 - Hanoi (Medium)

List the moves that carry `n` disks from peg A to peg C, count them (`2^n - 1`),
and find the k-th move without listing them all, even for `n = 60`.

Example: `moves(2)` gives `1:A->B 2:A->C 1:B->C`.

Solution: [M10P06_Hanoi.java](P06_Hanoi/M10P06_Hanoi.java)

## P07 - NQueens (Hard)

Place `n` queens on an `n x n` board so that no two attack each other.
Count the solutions and show the first one. Use backtracking with boolean arrays for columns and diagonals.

Example: `n = 4` gives `2` solutions, the first is `.Q../...Q/Q.../..Q.`.

Solution: [M10P07_NQueens.java](P07_NQueens/M10P07_NQueens.java)
