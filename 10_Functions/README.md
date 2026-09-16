# 10 - Functions

A **method** is a named block of code that you can call again and again.
Run a lesson from this folder, for example `java M10L01_MethodBasics.java`.

## Lessons

| Lesson | Shows |
|---|---|
| [M10L01_MethodBasics](M10L01_MethodBasics.java) | defining and calling methods, parameters, `void`, command-line `args` |
| [M10L02_ReturnValues](M10L02_ReturnValues.java) | return types, `return` on every path, early return, returning an array |
| [M10L03_PassByValue](M10L03_PassByValue.java) | copies of primitives, arrays and objects; String vs StringBuilder |
| [M10L04_VariableScope](M10L04_VariableScope.java) | local variables, shadowing a field, loop and block scope |
| [M10L05_Varargs](M10L05_Varargs.java) | `int...` parameters, empty calls, passing an array, `null` |
| [M10L06_Recursion](M10L06_Recursion.java) | base case, call order, factorial, recursion vs a loop |
| [M10L07_RecursionExamples](M10L07_RecursionExamples.java) | GCD, palindrome, binary search, Hanoi, subsets |
| [M10L08_Memoization](M10L08_Memoization.java) | slow Fibonacci, saving answers in an array, a loop version |
| [M10L09_TryCatchFinally](M10L09_TryCatchFinally.java) | `try`/`catch`/`finally`, catch order, `return` and `finally` |
| [M10L10_ThrowingExceptions](M10L10_ThrowingExceptions.java) | `throw`, `throws`, checked vs unchecked, a custom exception, `StackOverflowError` |

Practice problems: [problems/PROBLEMS.md](problems/PROBLEMS.md)

## Key points

- A method has a return type, a name and parameters: `static int add(int a, int b)`. The **signature** is the name plus the parameter types.
- A non-`void` method must `return` a value on every path.
- Java always passes a **copy of the value**. For arrays and objects the reference is copied: changes *through* it are shared, but assigning a new object to the parameter is not.
- A local variable exists only inside its method or block, and hides a static field with the same name.
- `int... numbers` is an `int[]` inside the method. No arguments gives an empty array. The varargs parameter must be **last**.
- Every recursive method needs a **base case** and must move toward it. Too deep recursion throws `StackOverflowError`; use a loop for very deep work.
- Saving answers in an array (**memoization**) stops recursion repeating work.
- An exception jumps to the nearest matching `catch`, possibly in a caller. Catch the more specific type first.
- `finally` always runs, even after `return`.
- **Checked** exceptions must be caught or declared with `throws`. **Unchecked** ones (`IllegalArgumentException`) need no `throws`.
