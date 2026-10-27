# Lab U3.5 — The Triangle Class

Design and build this class from your diagram.

## Step 1 — the UML, on paper (15 minutes)

Draw the class diagram before you open this repository. Three private doubles,
one class constant, a constructor, three accessors, `perimeter`, `area`,
`toString`, and two boolean methods.

In UML a **static** member is **underlined**, so draw
`- TOLERANCE : double = 0.01` with a line under it.

## Heron's formula — given, not memorized

```java
double s = (side1 + side2 + side3) / 2.0;
double area = Math.sqrt( s * (s - side1) * (s - side2) * (s - side3) );
```

**Declare the sides `double`, not `int`.** Because they are doubles, the sum is
a double and Java promotes the `2` — so `/ 2` and `/ 2.0` give the same answer
here. Declare them `int` and the division truncates and the area is silently
wrong. Write `2.0` anyway: it states the intent and survives a type change.
`Math.sqrt` is a *class* method — called on the class name.

## Never compare two doubles with `==`

Most decimals cannot be stored exactly in binary, so a `double` is often a tiny
bit off:

```java
System.out.println(0.1 + 0.2 == 0.3);   // false
System.out.println(0.1 + 0.2);          // 0.30000000000000004
```

So instead of asking "are these equal?", ask "are these **close enough**?":

```java
private static final double TOLERANCE = 0.01;   // closer than this counts as equal

Math.abs(side1 - side2) < TOLERANCE             // true when within 0.01
```

`TOLERANCE` is a class constant: `static`, so every Triangle shares one copy,
and `final`, so it can never change. `Math.abs` is on your Java Quick Reference.

## The two boolean methods

No `if` statement needed. A comparison already **is** a boolean, so return it.

```java
public boolean isEquilateral()
{
    return Math.abs(side1 - side2) < TOLERANCE
        && Math.abs(side2 - side3) < TOLERANCE;      // ALL of them
}

public boolean isIsosceles()
{
    return Math.abs(side1 - side2) < TOLERANCE
        || Math.abs(side2 - side3) < TOLERANCE
        || Math.abs(side1 - side3) < TOLERANCE;      // ANY of them
}
```

`&&` and `||` are borrowed from Unit 4 — you get them properly on Day 46.

**An equilateral triangle IS also isosceles.** If `isIsosceles()` returns false
for 5-5-5, you have used `&&` where `||` belongs.

## Marking — 100 points

| # | Requirement |
|---|---|
| 1 | Three private doubles, the `private static final double TOLERANCE`, and a public constructor |
| 2 | `getSide1()`, `getSide2()`, `getSide3()` |
| 3 | `perimeter()` |
| 4 | `area()` — Heron's, with `2.0` |
| 5 | `isEquilateral()` and `isIsosceles()` — compared with `TOLERANCE`, not `==` |
| 6 | `toString()` + a `main` building the Triangles below |

## Testing it yourself

Build these four. Print each one and both boolean results.

| Triangle | area | isEquilateral | isIsosceles |
|---|---|---|---|
| 5-5-5 | 10.8253… | true | **true** |
| 5-5-8 | 12.0 | false | true |
| 3-4-5 | 6.0 | false | false |
| `0.1 + 0.2`, 0.3, 0.3 | 0.0390… | **true** | true |

- **5-5-5** catches the `&&` / `||` error — an equilateral triangle **is**
  isosceles. It is also the row where declaring the sides `int` would change
  the area.
- **3-4-5** catches a missing `Math.abs`. Without it, `side1 - side2` is
  negative, so it is "less than TOLERANCE", and 3-4-5 wrongly reports isosceles.
- **`0.1 + 0.2`, 0.3, 0.3** catches `==`. Print it and you will see the first
  side is `0.30000000000000004`. With `==`, `isEquilateral()` says false.

## See it drawn (optional, not marked)

`DrawTriangle.java.txt` pops up a window that draws your triangle with its
sides, perimeter, area and type.

It calls seven of your methods, so it cannot compile until your Triangle is
finished — and while it is a `.java` file, IntelliJ will refuse to run
*anything* in the project. That is why it arrives as `.txt`.

1. Finish every TODO in `Triangle.java`.
2. Rename `DrawTriangle.java.txt` to `DrawTriangle.java`
   (in IntelliJ: right-click → Refactor → Rename).
3. Add this to the end of `main`:

```java
new DrawTriangle(yourTriangle).showWindow();
```

It needs a screen, so it runs in **IntelliJ**, not in a Codespace. You don't
need to change anything in it, but it is written to be read.

## Running it

- **IntelliJ** — File → New → Project from Version Control. Needed for
  `DrawTriangle`.
- **Codespaces** — `.devcontainer` sets up Java 17. Fine for everything except
  `DrawTriangle`.

Commit and push before you leave. Due at the start of Friday's lesson.
