# 11 - OOP: Problems

## P01 - BankAccount (Easy)

Write a `BankAccount` with a private balance. `deposit` and `withdraw` reject amounts `<= 0`, `withdraw` rejects overdrafts,
and `transferTo(other, amount)` changes neither account if it fails.

Example: balance 120, `withdraw(500)` gives `error: insufficient funds` and the balance stays `120.0`.

Solution: [M11P01_BankAccount.java](P01_BankAccount/M11P01_BankAccount.java)

## P02 - PointEquality (Easy)

Give `Point(x, y)` a correct `equals(Object)` and `hashCode()`, and write `countDistinct(Object[] items)`.
Show why a `BadPoint` with `equals(BadPoint p)` fails when compared through an `Object` reference.

Example: `countDistinct([(1,2), (1,2), (2,1)])` gives `2`.

Solution: [M11P02_PointEquality.java](P02_PointEquality/M11P02_PointEquality.java)

## P03 - StudentReport (Easy)

A `Student` has a name and an `int[]` of marks. Write `average()`, `grade()` (A >= 90, B >= 75, C >= 50, else F)
and `top(Student[])`. Protect the marks with defensive copies.

Example: marks `[90, 95, 100]` give average `95.0` and grade `A`.

Solution: [M11P03_StudentReport.java](P03_StudentReport/M11P03_StudentReport.java)

## P04 - TimeOfDay (Medium)

Write an immutable `Time` with constructors `()`, `(h)`, `(h, m)` and `(h, m, s)` chained with `this(...)`.
Values wrap around the clock, `plusSeconds(n)` returns a new `Time`, and `toString()` gives `HH:MM:SS`.

Example: `new Time(23, 59, 30).plusSeconds(45)` gives `00:00:15`.

Solution: [M11P04_TimeOfDay.java](P04_TimeOfDay/M11P04_TimeOfDay.java)

## P05 - Fraction (Medium)

Write an immutable `Fraction`, always stored in lowest terms with a positive denominator.
Add `plus`, `times`, `dividedBy`, value-based `equals`/`hashCode`, and reject a zero denominator.

Example: `1/2 + 1/3` gives `5/6`, and `new Fraction(3, -6)` prints `-1/2`.

Solution: [M11P05_Fraction.java](P05_Fraction/M11P05_Fraction.java)

## P06 - Library (Hard)

Build a small library with arrays: a `Book` record, a `Member` with a `Level` enum (BASIC 2 loans, PREMIUM 4),
and `borrow`/`giveBack` that throw a checked `LibraryException` when a rule is broken.

Example: Ann (BASIC) borrows B1 and B2, then B3 gives `error: Ann at limit`.

Solution: [M11P06_Library.java](P06_Library/M11P06_Library.java)

## P07 - ParkingLot (Hard)

Spots are `SMALL`, `MEDIUM` or `LARGE`; a vehicle parks in the first free spot at least its size.
The first hour is free, then bike 1, car 2, truck 5 per hour, and trucks pay 10 on entry.

Example: a car leaving after 3 hours pays `(3 - 1) * 2 = 4`.

Solution: [M11P07_ParkingLot.java](P07_ParkingLot/M11P07_ParkingLot.java)
