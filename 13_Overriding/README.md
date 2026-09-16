# 13 - Overriding

**Overriding** means a subclass gives its own body to an inherited method with the same name and parameters. At run time, the **real object** decides which version runs.

## Lessons

| # | Lesson | What it shows |
|---|---|---|
| 1 | [M13L01_OverridingBasics](M13L01_OverridingBasics.java) | `@Override`, override vs overload |
| 2 | [M13L02_SuperMethodCall](M13L02_SuperMethodCall.java) | `super.method()` across several levels |
| 3 | [M13L03_OverridingRules](M13L03_OverridingRules.java) | Access, covariant return, checked exceptions, `final` |
| 4 | [M13L04_MethodHiding](M13L04_MethodHiding.java) | Static methods are hidden; private methods are not inherited |
| 5 | [M13L05_FieldHiding](M13L05_FieldHiding.java) | Fields use the variable type |
| 6 | [M13L06_OverridingToString](M13L06_OverridingToString.java) | `toString` in a class hierarchy |
| 7 | [M13L07_OverridingEquals](M13L07_OverridingEquals.java) | `equals(Object)` and the `equals(Book)` trap |
| 8 | [M13L08_ConstructorTrap](M13L08_ConstructorTrap.java) | An overridable call in a constructor sees unset fields |
| 9 | [M13L09_TemplateMethod](M13L09_TemplateMethod.java) | The parent fixes the steps, children fill them in |
| 10 | [M13L10_LiskovSubstitution](M13L10_LiskovSubstitution.java) | An override must keep the parent's promises |

## Key points

- An override has the same name and parameter types. Always write `@Override`.
- `super.m()` calls the parent's version. There is no `super.super.m()`.
- Return type: the same type or a subclass of it. Access can only get wider. Checked exceptions can only get narrower.
- `final`, `static` and `private` methods are never overridden.
- Instance methods use the **object** type. Fields and static methods use the **variable** type.
- Override `equals(Object o)`, never `equals(Foo f)`, and override `hashCode()` with it.
- Do not call overridable methods from a constructor.
- A subclass must work anywhere its parent is expected (Liskov substitution).

## Problems

See [problems/PROBLEMS.md](problems/PROBLEMS.md).
