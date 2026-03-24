# 📊 Spreadsheet like Microsoft Excel — Low Level Design

Design a spreadsheet with cells, formulas, and formatting using the **Flyweight Pattern**.

**Problem Link:** [CodeZym #25](https://codezym.com/question/25)

## Design Patterns Used

| Pattern | Purpose | Classes |
|---------|---------|---------|
| **Flyweight** | Share `CellStyle` objects across cells with identical formatting | `CellStyle` (flyweight), `CellStyleFactory` (cache) |

## 🔑 Key Concepts

- **Cells** have a type (EMPTY, STRING, NUMBER, FORMULA)
- **Formulas** start with `=` and support: cell references (A1+B2), SUM, AVG, MIN, MAX
- **Recalculation** re-evaluates all formulas when data changes
- **CellStyle** is a flyweight — 1000 cells with same formatting share 1 style object

## 📂 Package Structure

```
Spreadsheet/
├── model/
│   ├── CellType.java   — EMPTY, STRING, NUMBER, FORMULA
│   ├── CellStyle.java  — flyweight: font, size, bold, color, alignment
│   ├── Cell.java        — row, col, rawValue, computedValue, type, style
│   └── Sheet.java       — grid of cells, cell reference parser
├── flyweight/
│   └── CellStyleFactory.java — cache for CellStyle flyweights
├── service/
│   ├── FormulaEvaluator.java  — evaluates =SUM(), =A1+B2, etc.
│   └── SpreadsheetService.java — set/get values, style, recalculate, print
└── SpreadsheetMain.java
```

## 📐 UML Class Diagram

```mermaid
classDiagram
    class CellType{ <<enum>> EMPTY; STRING; NUMBER; FORMULA }
    class CellStyle{ -String font; -int size; -boolean bold; -String color; -String bgColor; -String alignment }
    class Cell{ -int row; -int col; -String rawValue; -Object computedValue; -CellType type; -CellStyle style }
    class Sheet{ -String name; -Cell[][] grid; +getCell(ref) }
    class CellStyleFactory{ -Map cache$; +getStyle(...)$ CellStyle }
    class FormulaEvaluator{ +evaluate(formula, sheet) Object }
    class SpreadsheetService{ -Sheet; -FormulaEvaluator; +setCellValue(); +recalculate(); +printSheet() }

    Cell --> CellStyle : flyweight
    Cell --> CellType
    Sheet --> Cell : grid
    CellStyleFactory ..> CellStyle : creates/caches
    SpreadsheetService --> Sheet
    SpreadsheetService --> FormulaEvaluator
```

## 🚀 How to Run

```bash
javac -d out $(find Spreadsheet -name "*.java")
java -cp out Spreadsheet.SpreadsheetMain
```
