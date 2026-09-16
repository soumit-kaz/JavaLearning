# 14 - Compile-time and Runtime Binding

**Binding** means connecting a method call to the code that runs, or a field access to the field that is read.

- **Static (early) binding:** the compiler decides, using the **declared** type of the variable.
- **Dynamic (late) binding:** the JVM decides while the program runs, using the **real object**.

## Lessons

| # | Lesson | What it shows |
|---|---|---|
| 1 | [M14L01_StaticBinding](M14L01_StaticBinding.java) | private, static and final methods, `super` calls, fields and overloads are fixed at compile time |
| 2 | [M14L02_DynamicBinding](M14L02_DynamicBinding.java) | overridden methods follow the real object; casts, `instanceof`, enum constant bodies |
| 3 | [M14L03_OverloadMeetsOverride](M14L03_OverloadMeetsOverride.java) | the compiler picks the signature, the JVM picks the body; the `equals(Point)` trap |
| 4 | [M14L04_DoubleDispatch](M14L04_DoubleDispatch.java) | two virtual calls react to two runtime types (visitor with plain classes) |
| 5 | [M14L05_UnderTheHood](M14L05_UnderTheHood.java) | a pretend method table (vtable) and calling a method by name with reflection |

Run a lesson:

```bash
java M14L01_StaticBinding.java
```

## Key points

| Kind of call or access | Decided by | When |
|---|---|---|
| normal instance method | real object | runtime |
| `private` method | the class that declares it | compile time |
| `static` method | declared type | compile time |
| `final` method | there is only one version | compile time (in effect) |
| `super.method()` | the parent class | compile time |
| choosing between overloads | declared argument types | compile time |
| field access (static or not) | declared type | compile time |

- Every call is decided in two steps: the **compiler** picks the method *signature* from the declared types, then the **JVM** picks the *body* from the real object.
- Only the object before the dot is checked at runtime, never the arguments. Use double dispatch (a visitor) when both types matter.
- The declared type limits what you can call: `a.fetch()` does not compile when `a` is an `Animal`; `((Dog) a).fetch()` does. A wrong cast throws `ClassCastException`.
- A `private` method in the child is a new method; `@Override` on it does not compile.
- Static methods are **hidden**, not overridden. Call them as `ClassName.method()`; a call through a `null` variable still works.
- Fields are never polymorphic: `a.name` reads `Animal.name` even when `a` holds a `Dog`. Keep fields private and use getters.
- `equals(Point p)` is an overload, not an override: code that only knows `Object` never calls it. Always write `@Override`.
- `super.method()` never dispatches, and `super.super.method()` does not exist.

## Under the hood (bytecode)

`javac` turns each kind of call into a different JVM instruction (see them with `javap -c`):

```
invokestatic     static methods
invokespecial    constructors, super.method()
invokevirtual    instance methods (and private ones since Java 11)
```

From lesson 1, `Animal a = new Dog(); a.describe();` compiles to:

```
0: new           #17   // class M14L01_StaticBinding$Dog
3: dup
4: invokespecial #19   // Method M14L01_StaticBinding$Dog."<init>":()V
7: astore_1
...
12: invokevirtual #26  // Method M14L01_StaticBinding$Animal.describe:()Ljava/lang/String;
```

The bytecode only names `Animal`. At runtime the JVM looks in the real object's method table (the **vtable**): a child copies its parent's table and replaces the entries it overrides (lesson 5).

Practice problems are in [problems/PROBLEMS.md](problems/PROBLEMS.md).
