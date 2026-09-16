# 09 - Static: Problems

## P01 - Call Counter (Easy)
Write `greet(name)` that returns `"Hello <name> #k"`, where k counts the calls so far, and `reset()` that restarts the count.
Example: `greet("Ana")`, `greet("Ben")` -> `Hello Ana #1`, `Hello Ben #2`
Solution: [M09P01_CallCounter.java](P01_CallCounter/M09P01_CallCounter.java)

## P02 - Student Ids (Easy)
Make a `Student` class where each new student gets the next id `S1`, `S2`, ... Add a static `count()`.
Example: Asha, Bilal, Chen -> `S1`, `S2`, `S3`; count = 3
Solution: [M09P02_StudentIds.java](P02_StudentIds/M09P02_StudentIds.java)

## P03 - Shared Interest Rate (Medium)
Make an `Account` with its own `balance` and one static `rate` for all accounts. `addInterest()` adds `balance * rate`; `changeRate()` changes it for everyone.
Example: 1000 at 10% -> 1100.0; then at 5% -> 1155.0
Solution: [M09P03_SharedInterestRate.java](P03_SharedInterestRate/M09P03_SharedInterestRate.java)

## P04 - ScoreBoard Singleton (Medium)
Make a lazy singleton `ScoreBoard` that tracks total points and the best single score. It must be created once, on first use.
Example: `addPoints` 10, 25, 5 -> total 40, best 25, created 1
Solution: [M09P04_ScoreBoardSingleton.java](P04_ScoreBoardSingleton/M09P04_ScoreBoardSingleton.java)

## P05 - Init Order Puzzle (Hard)
Predict the tokens each step logs: `S` static block, `I` instance block, `Cn` constructor, `P` static method, `G` Gadget's static block.
Example: `Widget.ping()` -> `S P`; `new Widget()` -> `I C1`; `Gadget.SIZE` -> `size3` (a constant does not set up the class)
Solution: [M09P05_InitOrderPuzzle.java](P05_InitOrderPuzzle/M09P05_InitOrderPuzzle.java)
