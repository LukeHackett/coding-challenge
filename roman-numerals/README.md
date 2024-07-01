# Roman Numerals

Roman numerals are a system for representing numbers that originated in ancient Rome. 

Unlike the modern numerals `0, 1, 2, 3, ...` we use today, Roman numerals use a combination of letters from the Latin alphabet to represent different values.

The table below highlights the fundamental roman numeral values that are used to create other numbers.

Numbers that are not in the table can be formed by using multiple symbols, for example, `125` can be represented as `CXXV (100, 10, 10, 5)`.

| Symbol | Value |
|:------:|:-----:|
|    I   |   1   |
|   IV   |   4   |
|    V   |   5   |
|   IX   |   9   |
|    X   |   10  |
|   XL   |   40  |
|    L   |   50  |
|   XC   |   90  |
|    C   |  100  |
|   CD   |  400  |
|    D   |  500  |
|   CM   |  900  |
|    M   |  1000 |

## Instructions

This challenge asks you to create a program that translates numbers between `1` and `999` into their Roman numeral equivalents. 

Imagine a place value system where instead of digits `0-9`, we use Roman numerals `I, V, X, C, M`. 

Your program should be able to represent any number within this range using these special symbols.

### Requirements
- A symbol can be repeated only for three times, for example, `XXX = 30`, `CC = 200`.
- Symbols `V`, `L`, and `D` are never repeated.
- When a symbol of smaller value appears after a symbol of greater value, its values will be added, for example, `VI = V + I = 5 + 1 = 6`.
- When a symbol of a smaller value appears before a greater value symbol, it will be subtracted, for example, `IX = X - I = 10 - 1 = 9`.
- The symbols `V`, `L`, and `D` are never subtracted, as they are not written before a greater value symbol.
- For numbers outside the range `1 - 999` you can throw an exception
