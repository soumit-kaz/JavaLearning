# 02 - Data Types: Problems

## P01 - Swap Variables (Easy)

Swap the values of two `int` variables, then two `String` variables, using a temp variable.

```
before: a = 5, b = 9
after:  a = 9, b = 5
```

Solution: [M02P01_SwapVariables.java](P01_SwapVariables/M02P01_SwapVariables.java)

## P02 - Temperature Conversion (Easy)

Convert 25 C to Fahrenheit (`F = C * 9 / 5 + 32`), show what goes wrong with `9 / 5`, and convert 98.6 F back.

```
25.0 C = 77.0 F
with 9 / 5: 57.0 F
```

Solution: [M02P02_TemperatureConversion.java](P02_TemperatureConversion/M02P02_TemperatureConversion.java)

## P03 - Seconds To Time (Easy)

Split 100000 seconds (a `long`) into hours, minutes and seconds.

```
100000 s = 27 h 46 min 40 s
```

Solution: [M02P03_SecondsToTime.java](P03_SecondsToTime/M02P03_SecondsToTime.java)

## P04 - Casting Puzzles (Medium)

Predict the result of several casts before running the program.

```
(byte) 200 = -56
(double) (7 / 2) = 3.0
```

Solution: [M02P04_CastingPuzzles.java](P04_CastingPuzzles/M02P04_CastingPuzzles.java)

## P05 - Money BigDecimal (Hard)

Compare `double` and `BigDecimal` for money: 0.1 + 0.2, making change, and a subtotal with tax rounded to cents.

```
double change = 0.8999999999999999
BigDecimal change = 0.90
```

Solution: [M02P05_MoneyBigDecimal.java](P05_MoneyBigDecimal/M02P05_MoneyBigDecimal.java)
