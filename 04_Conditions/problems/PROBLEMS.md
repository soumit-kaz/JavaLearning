# 04 - Conditions: Problems

## P01 - Even Odd (Easy)

Given an integer `n`, print `even` or `odd`. Test `n % 2 == 0`, because `-3 % 2` is `-1`.

```
-3 -> odd  PASS
```

Solution: [M04P01_EvenOdd.java](P01_EvenOdd/M04P01_EvenOdd.java)

## P02 - Largest Of Three (Easy)

Given three integers, print the largest. Start with `a`, not `0`, so negative inputs work.

```
-4, -8, -1 -> -1  PASS
```

Solution: [M04P02_LargestOfThree.java](P02_LargestOfThree/M04P02_LargestOfThree.java)

## P03 - Leap Year (Easy)

Print whether a year is a leap year: divisible by 4, except by 100, unless also by 400.

```
1900 -> false  PASS
```

Solution: [M04P03_LeapYear.java](P03_LeapYear/M04P03_LeapYear.java)

## P04 - Grade Calculator (Easy)

Turn a score into a letter: 90+ `A`, 80+ `B`, 70+ `C`, 60+ `D`, else `F`. Scores outside 0-100 are `invalid`.

```
80 -> B  PASS
```

Solution: [M04P04_GradeCalculator.java](P04_GradeCalculator/M04P04_GradeCalculator.java)

## P05 - Triangle Type (Medium)

Given three sides, print `invalid`, `equilateral`, `isosceles` or `scalene`. Check validity first.

```
1, 2, 3 -> invalid  PASS
```

Solution: [M04P05_TriangleType.java](P05_TriangleType/M04P05_TriangleType.java)

## P06 - Switch Calculator (Medium)

Given two ints and an operator (`+ - * / %`), print the result, `division by zero`, or `unknown operator`.

```
7 / 2 -> 3  PASS
```

Solution: [M04P06_SwitchCalculator.java](P06_SwitchCalculator/M04P06_SwitchCalculator.java)
