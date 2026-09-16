# 06 - Arrays

An array holds a fixed number of values of one type. Run a lesson with `java M06L01_CreatingArrays.java`.

## Lessons

| Lesson | Shows |
|---|---|
| [M06L01_CreatingArrays](M06L01_CreatingArrays.java) | `new`, `{...}` initializers, default values, reading and writing elements |
| [M06L02_ArrayLength](M06L02_ArrayLength.java) | the `length` field, first and last index, fixed size |
| [M06L03_LoopingWithFor](M06L03_LoopingWithFor.java) | index loops: forward, backward, steps, changing elements |
| [M06L04_ForEachLoop](M06L04_ForEachLoop.java) | for-each over `int`, `String` and `char` arrays; read-only; when you need the index |
| [M06L05_ArraysOfOtherTypes](M06L05_ArraysOfOtherTypes.java) | primitive, `String[]` and `Integer[]` arrays, `null` elements, `char[]` |
| [M06L06_ArrayReferences](M06L06_ArrayReferences.java) | `b = a` shares one array, `==`, `null` |
| [M06L07_CopyingArrays](M06L07_CopyingArrays.java) | loop copy, `clone`, `System.arraycopy`, `Arrays.copyOf`, `copyOfRange` |
| [M06L08_ArraysClass](M06L08_ArraysClass.java) | `Arrays.toString`, `sort`, `fill`, `equals`, `mismatch`, `compare`, `binarySearch` |
| [M06L09_CommonAlgorithms](M06L09_CommonAlgorithms.java) | sum, average, min/max, search, reverse, counting, insert and delete |
| [M06L10_ArrayPitfalls](M06L10_ArrayPitfalls.java) | bad index, off-by-one, negative size, `null`, wrong max, overflow |

## Key points

- Indexes go from `0` to `length - 1`. `length` is a field (no `()`); a String uses `length()`.
- An array's size never changes. To "grow" one, copy it into a bigger array.
- `{1, 2}` works only in a declaration. Elsewhere write `new int[] {1, 2}`.
- New arrays hold defaults: `0`, `0.0`, `false`, `null`, and for `char` the code 0 (not a space).
- Prefer `int[] a, b;` (two arrays). `int a[], b;` makes one array and one plain int.
- `for (int x : array)` reads every element. `x` is a copy, so use an index loop to change elements or to know the position.
- `b = a` copies nothing: both names point to the same array.
- Compare values with `Arrays.equals` and print with `Arrays.toString`; `==` and `.equals` only compare references.
- Copies are shallow: a copied `String[]` holds the same String objects.
- Ranges like `copyOfRange(a, from, to)` include `from` but not `to`.
- `Arrays.binarySearch` needs a sorted array.
- Start min/max at `array[0]`, not `0`. Cast before dividing for an average. Use `long` for big totals.
- A bad index, a negative size or a `null` array fail only when the program runs.

Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)
