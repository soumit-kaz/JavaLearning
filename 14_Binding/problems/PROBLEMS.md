# 14 - Compile-time and Runtime Binding: Problems

## P01 - Field Hiding Puzzle (Easy)

`A`, `B extends A` and `C extends B` each declare their own `name` field; `B` overrides `getName()` and `C` overrides `show()`.
Predict each field read and method call, remembering that fields use the declared type and methods use the real object.

Example: `A ab = new B(); ab.show()` -> `A/B`

Solution: [M14P01_FieldHidingPuzzle.java](P01_FieldHidingPuzzle/M14P01_FieldHidingPuzzle.java)

## P02 - Static Hiding Puzzle (Easy)

`Base` and `Derived` each have a static `id()` and a static `created` counter; only `kind()` is overridden.
Predict static calls made through variables (even `null`) and the two counters after several objects are created.

Example: `Base b = new Derived(); b.who()` -> `Base:derived`

Solution: [M14P02_StaticHidingPuzzle.java](P02_StaticHidingPuzzle/M14P02_StaticHidingPuzzle.java)

## P03 - Overload Meets Override (Medium)

`Animal` has `meet(Animal)` and `meet(Dog)`; `Dog` overrides both and adds `meet(Cat)`; `Cat` overrides only `meet(Animal)`.
Predict each call (the compiler picks the overload, the JVM picks the class), then show why `equals(Point)` is not found by code that uses `Object`.

Example: `Animal a1 = new Dog(); a1.meet(c)` with `Cat c` -> `Dog meets Animal`

Solution: [M14P03_OverloadMeetsOverride.java](P03_OverloadMeetsOverride/M14P03_OverloadMeetsOverride.java)

## P04 - Super Chain Puzzle (Medium)

Three levels `L1`, `L2`, `L3` call each other through `run()`, `step()`, `go()` and `super`.
Trace each call: `super.x()` is fixed at compile time, but calls inside the parent still dispatch on the real object.

Example: `new L3().run()` -> `L2.run>L1.run>L3.step`

Solution: [M14P04_SuperChainPuzzle.java](P04_SuperChainPuzzle/M14P04_SuperChainPuzzle.java)

## P05 - Collision Double Dispatch (Medium)

`Asteroid`, `Ship` and `Station` collide in any order. Use `collideWith(other)` plus `hitByX(this)` so both runtime types choose the result, with no `instanceof`.
Unknown names become a plain `SpaceObject` where nothing happens.

Example: `Ship+Station` -> `ship docks`

Solution: [M14P05_CollisionDoubleDispatch.java](P05_CollisionDoubleDispatch/M14P05_CollisionDoubleDispatch.java)

## P06 - Expression Visitor (Hard)

Build `Num`, `Var`, `Add` and `Mul` nodes with `accept(visitor)`, and three visitors: `Printer`, `Evaluator` (one variable, error if unknown) and `NumberCounter`.
The visitor base class has empty `visitX` methods, so each visitor overrides only what it needs.

Example: `(x + 2) * 3` with `x=4` -> `18`

Solution: [M14P06_ExpressionVisitor.java](P06_ExpressionVisitor/M14P06_ExpressionVisitor.java)
