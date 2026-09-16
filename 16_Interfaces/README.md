# 16 - Interfaces

An **interface** is a contract: a list of methods that a class promises to have.

## Lessons

| # | Lesson | What it shows |
|---|---|---|
| 1 | [M16L01_InterfaceBasics](M16L01_InterfaceBasics.java) | `implements`, the interface as a type, constants, partial implementation |
| 2 | [M16L02_MultipleInterfaces](M16L02_MultipleInterfaces.java) | one class, many interfaces; interfaces extending interfaces; marker interfaces |
| 3 | [M16L03_DefaultMethods](M16L03_DefaultMethods.java) | `default` methods, overriding them, `Interface.super.method()` |
| 4 | [M16L04_StaticAndPrivateMethods](M16L04_StaticAndPrivateMethods.java) | `static` factory methods and `private` helpers inside an interface |
| 5 | [M16L05_DiamondRules](M16L05_DiamondRules.java) | which default wins when two versions meet |
| 6 | [M16L06_AnonymousClassFromInterface](M16L06_AnonymousClassFromInterface.java) | implementing an interface on the spot; nested listener interfaces |
| 7 | [M16L07_FunctionalInterfaces](M16L07_FunctionalInterfaces.java) | one abstract method, `@FunctionalInterface`, `Runnable` |
| 8 | [M16L08_Lambdas](M16L08_Lambdas.java) | lambda syntax, `this`, effectively final, checked exceptions |
| 9 | [M16L09_BuiltInFunctionalInterfaces](M16L09_BuiltInFunctionalInterfaces.java) | first generics (`Box<T>`); `Supplier`, `Consumer`, `Predicate`, `Function`; combining them |
| 10 | [M16L10_MethodReferences](M16L10_MethodReferences.java) | the four kinds of `::` references |
| 11 | [M16L11_Comparable](M16L11_Comparable.java) | natural order with `Comparable<Student>` and `Arrays.sort` |
| 12 | [M16L12_Comparator](M16L12_Comparator.java) | comparators, `comparing`, `reversed`, `thenComparing`, `nullsLast` |
| 13 | [M16L13_Iterable](M16L13_Iterable.java) | making your class work in a for-each loop |
| 14 | [M16L14_AutoCloseable](M16L14_AutoCloseable.java) | try-with-resources, close order, suppressed exceptions |
| 15 | [M16L15_SealedInterfaces](M16L15_SealedInterfaces.java) | `sealed` / `permits`, `non-sealed`, a `switch` with no `default` |

## Key points

- Interface methods are `public abstract` and fields are `public static final`, so implementing methods must be `public`.
- A class `extends` one class but `implements` many interfaces. An interface `extends` interfaces, never a class.
- `default` methods have a body; `static` methods are called as `Shape.of(...)` and are not inherited; `private` methods are helpers.
- Diamond rules: 1) a class method wins; 2) the most specific interface wins; 3) otherwise override, and use `X.super.m()` if needed.
- A functional interface has exactly one abstract method; a lambda or method reference implements it.
- In a lambda, `this` is the surrounding object, and local variables must be effectively final.
- In `compareTo`, use `Integer.compare(a, b)`, not `a - b` (overflow).
- `iterator()` should return a fresh iterator; try-with-resources closes in reverse order.
- A `sealed` interface lists its implementations, so a `switch` over it needs no `default`.

| Need | Choose |
|---|---|
| Shared fields and constructors | abstract class |
| A capability for unrelated classes; more than one parent type | interface |
| A target for a lambda | functional interface |

## Problems

See [problems/PROBLEMS.md](problems/PROBLEMS.md).
