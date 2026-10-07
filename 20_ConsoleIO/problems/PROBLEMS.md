# 20 - Console Input and Output: Problems

Run a solution from its folder, for example `java M20P01_Greeting.java`.

Each solution puts the reading in a method that takes a `Scanner`, so the same method works
for the keyboard (`new Scanner(System.in)`) and for the tests (`new Scanner("Ann\n30\n")`).

## P01 - Greeting (Easy)

Read a name on the first line and an age on the second, then return
`Hello <name>, you are <age>`.

Example: `Ann`, `30` -> `Hello Ann, you are 30`

Solution: [M20P01_Greeting.java](P01_Greeting/M20P01_Greeting.java)

## P02 - Sum Until End (Easy)

Add up one number per line. Stop at the word `end`, or when the input runs out.
Return the total.

Example: `1`, `2`, `3`, `end` -> `6`

Solution: [M20P02_SumUntilEnd.java](P02_SumUntilEnd/M20P02_SumUntilEnd.java)

## P03 - Calculator (Easy)

Read a number, a sign (`+`, `-`, `*`, `/`) and a second number, each on its own line.
Return the whole sum as text. Dividing by zero gives `cannot divide by zero`, and any other
sign gives `unknown sign <sign>`.

Example: `2`, `+`, `3` -> `2 + 3 = 5`

Solution: [M20P03_Calculator.java](P03_Calculator/M20P03_Calculator.java)

## P04 - Menu Choice (Medium)

Keep reading lines until one of them is a number from 1 to 3, and return it. Say what is wrong
with every line you reject. Return 0 when the input runs out first.

Example: `hello`, `9`, `2` -> `2`

Solution: [M20P04_MenuChoice.java](P04_MenuChoice/M20P04_MenuChoice.java)

## P05 - Price Table (Medium)

Build a price table with `String.format`: the name on the left in 10 places, the price on the
right in 8 places with two decimals, and a `TOTAL` line at the end.

Example: `tea 3.5`, `biscuits 12.25` -> `tea            3.50`, `biscuits      12.25`, `TOTAL         15.75`

Solution: [M20P05_PriceTable.java](P05_PriceTable/M20P05_PriceTable.java)
