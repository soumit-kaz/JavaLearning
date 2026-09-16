# 11 - OOP

Classes, objects and the ideas built on them.
Run a lesson from this folder, for example `java M11L01_ClassesAndObjects.java`.

## Lessons

| # | Lesson | What it shows |
|---|---|---|
| 1 | [M11L01_ClassesAndObjects](M11L01_ClassesAndObjects.java) | `new`, instance methods, references, `null` |
| 2 | [M11L02_Fields](M11L02_Fields.java) | Instance fields, default values, `static` fields |
| 3 | [M11L03_Constructors](M11L03_Constructors.java) | Constructors, the hidden default one, initialization order |
| 4 | [M11L04_ThisKeyword](M11L04_ThisKeyword.java) | `this.field`, `this(...)`, returning `this` |
| 5 | [M11L05_Encapsulation](M11L05_Encapsulation.java) | `private` fields, getters, setters, validation |
| 6 | [M11L06_AccessModifiers](M11L06_AccessModifiers.java) | `public`, `protected`, package-private, `private` |
| 7 | [M11L07_ToString](M11L07_ToString.java) | Default and custom `toString` |
| 8 | [M11L08_EqualsAndHashCode](M11L08_EqualsAndHashCode.java) | `==` vs `equals`, a correct `equals` and `hashCode` |
| 9 | [M11L09_Inheritance](M11L09_Inheritance.java) | `extends`, inherited members, constructor order |
| 10 | [M11L10_SuperKeyword](M11L10_SuperKeyword.java) | `super(...)`, `super.method()`, `super.field` |
| 11 | [M11L11_Polymorphism](M11L11_Polymorphism.java) | A parent variable holding child objects |
| 12 | [M11L12_CastingAndInstanceof](M11L12_CastingAndInstanceof.java) | Downcasts, `ClassCastException`, `instanceof` patterns |
| 13 | [M11L13_ObjectClass](M11L13_ObjectClass.java) | `Object`, `getClass`, inherited `Object` methods |
| 14 | [M11L14_Composition](M11L14_Composition.java) | Has-a, delegation, shallow vs deep copy |
| 15 | [M11L15_Immutability](M11L15_Immutability.java) | `final`, immutable classes, defensive copies |
| 16 | [M11L16_Enums](M11L16_Enums.java) | Enums with fields and methods, `values`, `valueOf` |
| 17 | [M11L17_Records](M11L17_Records.java) | Records, compact constructors, record patterns |
| 18 | [M11L18_InnerClasses](M11L18_InnerClasses.java) | Static nested, inner and local classes |
| 19 | [M11L19_AnonymousClasses](M11L19_AnonymousClasses.java) | One-time subclasses without a name |

Practice problems: [problems/PROBLEMS.md](problems/PROBLEMS.md)

## Key points

- A class-type variable holds a **reference**. `b = a` copies the reference, not the object.
- Fields get default values (`0`, `false`, `null`); local variables do not.
- Once you write any constructor, Java no longer adds the no-arg one.
- `this(...)` calls a constructor of the same class; `super(...)` calls the parent's. Without `super(...)`, Java adds `super()`.
- Order for `new`: static blocks (once per class), then parent before child: field initializers and instance blocks, then the constructor body.

| Modifier | Same class | Same package | Subclass (other package) | Everywhere |
|---|---|---|---|---|
| `private` | yes | no | no | no |
| (none) | yes | yes | no | no |
| `protected` | yes | yes | yes | no |
| `public` | yes | yes | yes | yes |

- In these lessons all classes share one file, so even `private` members are visible.
- Override `equals(Object)`, not `equals(Point)`. Equal objects **must** have equal hash codes.
- The **object's** class decides which overridden method runs; the **variable's** type decides what you may call.
- A bad downcast throws `ClassCastException`; check with `instanceof` first.
- Prefer composition (has-a) unless the child truly is-a parent.
- A `final` reference can still point to an object that changes.
- Enum constants are single objects, so `==` is safe. Records are immutable data carriers with accessors like `x()`.
- An inner class needs an outer object (`outer.new Inner()`); a static nested class does not.
