# 15 - Abstraction: Problems

## P01 - Text Case Converter (Easy)

Abstract `CaseConverter` has a `final convert(text)` that splits text into lowercase words and joins them with abstract `separator()` and `shapeWord(word, index)`.
Build camelCase, snake_case, CONSTANT_CASE and kebab-case. Words break at non-letters, `aB`, the last capital of an acronym, and letter/digit changes.

Example: SnakeCase `parseHTTPResponse` -> `parse_http_response`

Solution: [M15P01_TextCaseConverter.java](P01_TextCaseConverter/M15P01_TextCaseConverter.java)

## P02 - Number Sequences (Medium)

Abstract `Sequence` with `abstract long produce()` (calls `finish()` at the end) and `final take(max)`.
Build `Range(start, end, step)` (end excluded, step 0 is an error), `Fibonacci(limit)` and `MultiplesOf(source, k)`, which wraps another sequence.

Example: `MultiplesOf(Fibonacci(100), 2)` -> `[0 2 8 34]`

Solution: [M15P02_NumberSequence.java](P02_NumberSequence/M15P02_NumberSequence.java)

## P03 - Stack and Queue (Medium)

Abstract `IntContainer` holds an `int[]` and `size`; its `final add`/`remove` check full/empty and call abstract `store`, `take`, `peekAt`.
Build `IntStack` and a circular `IntQueue`. Ops: `+n` adds, `-` removes; the first error stops the run.

Example: IntQueue(3) `+1 +2 - +3 +4 -` -> `removed 1 2 left [3 4]`

Solution: [M15P03_StackAndQueue.java](P03_StackAndQueue/M15P03_StackAndQueue.java)

## P04 - File System Tree (Medium)

Abstract `Node` with `File` and `Folder` (children in an array): `size()`, `fileCount()`, `find(ext)`, `render(indent)` and a `final path()`.
Duplicate names in a folder, empty names and names with `/` are errors.

Example: `find("txt")` -> `/root/src/util/notes.txt /root/docs/readme.txt`

Solution: [M15P04_FileSystemTree.java](P04_FileSystemTree/M15P04_FileSystemTree.java)

## P05 - Vending Machine States (Hard)

Abstract `State` gives each event (`COIN`, `SELECT`, `CANCEL`, `SERVICE`) a default that returns `ignored`; `IDLE`, `CREDIT`, `SOLDOUT` and `SERVICE` override only what they handle.
Each event prints `BEFORE->AFTER message`.

Example: `COIN 25;SELECT cola` (cola=35) -> `IDLE->CREDIT credit 25 | CREDIT->CREDIT need 10`

Solution: [M15P05_VendingStateMachine.java](P05_VendingStateMachine/M15P05_VendingStateMachine.java)

## P06 - Discount Engine (Hard)

Abstract `Rule` has a `final discountFor(cart, remaining)`: 0 if `appliesTo` is false, never more than `remaining`.
Rules apply in order on what is left; an `exclusive()` rule (coupon) replaces all others when it applies.

Example: tv 12000 + novel 2000, member -> `subtotal=14000 BOOKS10=-200 SPEND100=-1500 MEMBER5=-615 total=11685`

Solution: [M15P06_DiscountEngine.java](P06_DiscountEngine/M15P06_DiscountEngine.java)
