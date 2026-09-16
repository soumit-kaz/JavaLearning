# 15 - Abstraction

Abstraction shows **what** an object does and hides **how**. This module uses **abstract classes**; interfaces come in 16.

## Lessons

| # | Lesson | What it shows |
|---|---|---|
| 1 | [M15L01_AbstractClassBasics](M15L01_AbstractClassBasics.java) | abstract class and method, abstract type as variable/array, anonymous subclass, wider access |
| 2 | [M15L02_AbstractConstructorsAndState](M15L02_AbstractConstructorsAndState.java) | fields, constructors with `super(...)`, shared validation, the constructor pitfall |
| 3 | [M15L03_TemplateMethod](M15L03_TemplateMethod.java) | a `final` recipe with abstract steps and an optional hook |
| 4 | [M15L04_AbstractHierarchy](M15L04_AbstractHierarchy.java) | partial implementation, re-abstracting a method, `sealed` / `non-sealed` |
| 5 | [M15L05_AbstractVsInterface](M15L05_AbstractVsInterface.java) | first look at interfaces next to an abstract class |

## Key points

- An **abstract class** cannot be created with `new`, but it works as a variable, parameter or array type.
  ```java
  Animal a = new Animal();          // does NOT compile
  Animal b = new Dog();             // OK
  Animal c = new Animal() { ... };  // OK: an anonymous subclass
  ```
- An **abstract method** has no body. A class with one must be abstract. A concrete subclass must implement
  **every** inherited abstract method; otherwise it must be abstract too (partial implementation).
- A class may be abstract with no abstract methods: this only stops `new`.
- Abstract classes can have fields, constructors, concrete, `static`, `final` and `private` methods.
  Constructors run through `super(...)`, parent first.
- `abstract` cannot be combined with `private`, `static` or `final`, and fields cannot be abstract.
- An implementation may **widen** access (`protected` -> `public`), never narrow it.
- **Pitfall:** an abstract method called from a constructor sees the subclass fields still `null`/`0`.
  Pass values to `super(...)` instead.
- **Template method:** fixed steps in a `final` method; subclasses fill in abstract steps and may override **hooks**.
- A **sealed** class lists its subclasses (`permits`); each must be `final`, `sealed` or `non-sealed`.
  A `switch` over it needs no `default`.
- A class extends only **one** class. Pick an abstract class when subclasses share state and constructor logic;
  interfaces (16) allow many parents.

## Problems

See [problems/PROBLEMS.md](problems/PROBLEMS.md).
