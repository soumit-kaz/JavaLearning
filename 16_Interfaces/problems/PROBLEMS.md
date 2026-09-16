# 16 - Interfaces: Problems

## P01 - Range Iterable (Easy)

Write `Range(start, end, step)` that works in a for-each loop. `end` is excluded, `step` may be negative, a step of 0 is an error, and a range pointing the wrong way is empty.
It must loop twice and must not loop forever near `Integer.MAX_VALUE` (count the values with `long` math).

Example: `Range(10, 0, -4)` -> `[10,6,2]`

Solution: [M16P01_RangeIterable.java](P01_RangeIterable/M16P01_RangeIterable.java)

## P02 - Pricing Strategies (Medium)

Make a checkout discount pluggable with `PricingStrategy { int discount(Item[] cart); }` and the helpers `none`, `percentOff`, `flatOver`, `buyXGetOneFree`, `best` and a default `plus`.
The final discount must stay between 0 and the subtotal.

Example: cart of 7 apples at 50, 1 bread at 300, 2 milk at 150, `percentOff(10).plus(buyXGetOneFree("apple", 2))` -> `755`

Solution: [M16P02_Strategy.java](P02_Strategy/M16P02_Strategy.java)

## P03 - Diamond Puzzles (Medium)

`B` and `D` extend `A` and override the default `who()`; `C` extends `A` without overriding it.
Predict `who()` for each class in the solution using the three diamond rules, then run it to check.

Example: `class P1 implements A, B {}` -> `B`

Solution: [M16P03_DiamondPuzzle.java](P03_DiamondPuzzle/M16P03_DiamondPuzzle.java)

## P04 - Validator Chain (Medium)

Design `Validator<T> { String validate(T value); }` (returns `""` when valid) with defaults `isValid`, `and` (keep all errors), `andThen` (stop at the first failure), `or`, and statics `rule` and `required`.
Use them to validate `User(name, email, age, phone)`.

Example: `User("rinaaaaaaaaaaa", "rina@mail", 12, "123")` -> `name: too long; not capitalized; email: bad email; age: too young; phone: (not empty) or (not 11 digits)`

Solution: [M16P04_ValidatorChain.java](P04_ValidatorChain/M16P04_ValidatorChain.java)

## P05 - Payment Adapters (Medium)

Adapt an old bank API (taka, error codes) and an old wallet SDK (checked exception) to `PaymentProcessor`.
Write a `Router` that is itself a `PaymentProcessor`: it tries each supporting processor in order and returns the first success, or a failure listing every reason.

Example: `charge("US777", 6000)` -> `card OK CARD-6000` (the bank fails with "invalid IBAN" first)

Solution: [M16P05_PaymentAdapter.java](P05_PaymentAdapter/M16P05_PaymentAdapter.java)

## P06 - Multi-Key Comparator (Medium)

Sort `Student(name, grade, age, gpa)` by a text spec such as `"grade, gpa desc"`. Keys: `name`, `grade`, `age` (may be null), `gpa`, `namelen`; modifiers: `asc`, `desc`, `nullsFirst`, `nullsLast` (nulls last by default).
Break ties by the natural order (name); an unknown key or modifier is an error.

Example: `"age desc nullsFirst"` -> `Adam Carl Bob Eve Zara Mina`

Solution: [M16P06_MultiKeyComparator.java](P06_MultiKeyComparator/M16P06_MultiKeyComparator.java)
