# 13 - Overriding: Problems

## P01 - Animal Chorus (Easy)

Write `Animal`, `Dog`, `Cat` and `Puppy extends Dog`, each overriding `String sound()`. `Puppy` reuses `super.sound()`, lowercases it and adds `!`.
Write `chorus(Animal[])`, which joins all sounds with spaces.

Example: `chorus([Puppy, Cat])` -> `woof! Meow`

Solution: [M13P01_AnimalChorus.java](P01_AnimalChorus/M13P01_AnimalChorus.java)

## P02 - Shape Areas (Easy)

Write `Shape` (area 0), `Circle(r)`, `Rectangle(w, h)` and `Square(side) extends Rectangle`, overriding `area()` and `name()`.
`describe()` is written once in `Shape` and returns the name and the area with 2 decimals. Also write `totalArea(Shape[])`.

Example: `new Square(4).describe()` -> `Square 16.00`

Solution: [M13P02_ShapeAreas.java](P02_ShapeAreas/M13P02_ShapeAreas.java)

## P03 - Payroll (Easy)

`Employee` earns `base`; `Manager` adds 100.00 per report; `Director` adds a bonus percent on top of the Manager pay; `Contractor` earns `hours * rate`, with hours above 160 paid 1.5x.
Build each pay on `super.pay()`. `toString()` is written only in `Employee`: `Title Name: amount`.

Example: `Director("Dia", 8000.00, 2 reports, 10%)` -> `Director Dia: 9020.00`

Solution: [M13P03_Payroll.java](P03_Payroll/M13P03_Payroll.java)

## P04 - The equals Overload Trap (Medium)

`BuggyIsbn` declares `equals(BuggyIsbn)`. Predict `b1.equals(b2)`, `b1.equals((Object) b2)` and `indexOf([b1], b2)`.
Then write a fixed `Isbn` with `equals(Object)` and `hashCode()` that ignores dashes and spaces.

Example: buggy `b1.equals((Object) b2)` -> `false`; fixed `Isbn("978-0-13").equals(Isbn("978 0 13"))` -> `true`

Solution: [M13P04_EqualsOverloadTrap.java](P04_EqualsOverloadTrap/M13P04_EqualsOverloadTrap.java)

## P05 - The Constructor Trap (Medium)

`Widget()` calls the overridable `render()`. `Label` overrides it and has fields `text = "default"` and `final int width = 10`. Predict the trace of `new Label("Hi")`.
Then fix it in `EarlyLabel` by setting the field before `super()` (Java 25).

Example: `new Label("Hi")` -> `Widget() | label text=null width=10 | Label() text was default | after: label text=Hi width=10`

Solution: [M13P05_ConstructorTrap.java](P05_ConstructorTrap/M13P05_ConstructorTrap.java)

## P06 - Report Template (Medium)

`Report.build(Sale[])` is `final`: `header()`, then `row()` per included sale with `separator()` between rows, then `footer(sum)`. Sales with qty 0 are skipped.
`CsvReport` overrides the header, rows and separator. `RegionReport extends CsvReport` keeps one region (using `super.include`) and prints its total.

Example: `RegionReport("EU")` -> `item,total;pen,6;book,20;EU total=26`

Solution: [M13P06_ReportTemplate.java](P06_ReportTemplate/M13P06_ReportTemplate.java)
