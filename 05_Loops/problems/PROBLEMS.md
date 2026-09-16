# 05 - Loops: Problems

## P01 - Multiplication Table (Easy)

Print the multiplication table of 7, from 1 to 10.

```
7 x  1 =  7
7 x  2 = 14
```

Solution: [M05P01_MultiplicationTable.java](P01_MultiplicationTable/M05P01_MultiplicationTable.java)

## P02 - Factorial (Easy)

Compute `n! = 1 * 2 * ... * n` with `0! = 1`. A `long` holds only up to 20!, so use `BigInteger`.

```
5! -> 120  PASS
```

Solution: [M05P02_Factorial.java](P02_Factorial/M05P02_Factorial.java)

## P03 - Fibonacci (Easy)

Build the first `count` Fibonacci numbers (starting 0, 1) separated by spaces.

```
5 -> [0 1 1 2 3]  PASS
```

Solution: [M05P03_Fibonacci.java](P03_Fibonacci/M05P03_Fibonacci.java)

## P04 - Prime Check (Easy)

Decide whether `n` is prime. Numbers below 2 are not. Try divisors while `d * d <= n`.

```
91 -> false  PASS
```

Solution: [M05P04_PrimeCheck.java](P04_PrimeCheck/M05P04_PrimeCheck.java)

## P05 - Reverse Number (Medium)

Reverse the digits of an `int`, keeping the sign. Return `0` if the result does not fit in an `int`.

```
-56 -> -65  PASS
```

Solution: [M05P05_ReverseNumber.java](P05_ReverseNumber/M05P05_ReverseNumber.java)

## P06 - Gcd And Lcm (Medium)

Find the greatest common divisor (Euclid) and least common multiple of two integers, ignoring signs.

```
12, 18 -> gcd=6 lcm=36  PASS
```

Solution: [M05P06_GcdAndLcm.java](P06_GcdAndLcm/M05P06_GcdAndLcm.java)

## P07 - FizzBuzz (Easy)

For 1 to `n`, write `Fizz` (multiple of 3), `Buzz` (of 5), `FizzBuzz` (of both) or the number. Test 15 first.

```
5 -> [1 2 Fizz 4 Buzz]  PASS
```

Solution: [M05P07_FizzBuzz.java](P07_FizzBuzz/M05P07_FizzBuzz.java)

## P08 - Pattern Printing (Hard)

For `size = 4`, print a pyramid, a diamond and Floyd's triangle, with no trailing spaces.

```
   *
  ***
```

Solution: [M05P08_PatternPrinting.java](P08_PatternPrinting/M05P08_PatternPrinting.java)
