# 09 - Static

Your first methods and small classes, all built around the keyword `static`. Run a lesson with `java M09L01_StaticVariables.java`.

## Lessons

| Lesson | Shows |
|---|---|
| [M09L01_StaticVariables](M09L01_StaticVariables.java) | static variables, default values, hiding, `static final` constants |
| [M09L02_StaticMethods](M09L02_StaticMethods.java) | writing and calling static methods, static vs local lifetime |
| [M09L03_StaticBlocks](M09L03_StaticBlocks.java) | `static { }` blocks, their order, setting a `static final` |
| [M09L04_InitializationOrder](M09L04_InitializationOrder.java) | static block, instance block, constructor, setup on first use |
| [M09L05_StaticVsInstance](M09L05_StaticVsInstance.java) | a small class, `new`, shared static fields vs per-object fields |
| [M09L06_StaticNestedClass](M09L06_StaticNestedClass.java) | classes inside classes, full names, static method hiding |
| [M09L07_Singleton](M09L07_Singleton.java) | eager and lazy singletons |
| [M09L08_StaticImport](M09L08_StaticImport.java) | `import static` |

## Key points

- A static variable has one copy for the whole class; an instance variable has one copy per object.
- Static variables get defaults (`0`, `false`, `null`). Local variables do not and must be assigned first.
- A local variable with the same name hides a static one. Use `ClassName.name` to reach the static one.
- `static final` makes a constant, assigned exactly once and named in `UPPER_CASE`.
- A local variable starts fresh on every call; a static variable keeps its value between calls.
- Static blocks run once, top to bottom, when the class is first used (for the main class, before `main`).
- `new` runs the instance block, then the constructor body. Reading a compile-time constant does not set up its class.
- A static method has no object: it cannot use instance fields directly.
- A static method with the same name in a child class hides the parent's one; it does not override it.
- A singleton has a `private` constructor and a static `getInstance()`. Eager creates the object at setup; lazy creates it on the first call.
- Use `import static` sparingly, or it is hard to see where a name comes from.

Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)
