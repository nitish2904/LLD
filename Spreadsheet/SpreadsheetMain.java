package Spreadsheet;

import Spreadsheet.service.SpreadsheetService;

/**
 * =====================================================================
 *  Spreadsheet like Microsoft Excel — LLD (CodeZym #25)
 * =====================================================================
 *
 *  Design Pattern: FLYWEIGHT PATTERN (CellStyle shared across cells)
 *  Features: cell values (string/number/formula), SUM/AVG/MIN/MAX,
 *    cell references (=A1+B2), recalculation, styling
 *
 *  Link: https://codezym.com/question/25
 * =====================================================================
 */
public class SpreadsheetMain {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  Spreadsheet (Excel-like) — LLD Demo         ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        SpreadsheetService ss = new SpreadsheetService("Sheet1", 10, 5);

        // ===== Scenario 1: Basic values =====
        System.out.println("\n===== Scenario 1: Basic Values =====");
        ss.setCellValue("A1", "Name");
        ss.setCellValue("B1", "Score");
        ss.setCellValue("A2", "Alice");
        ss.setCellValue("B2", "85");
        ss.setCellValue("A3", "Bob");
        ss.setCellValue("B3", "92");
        ss.setCellValue("A4", "Charlie");
        ss.setCellValue("B4", "78");
        ss.printSheet();

        // ===== Scenario 2: Formulas =====
        System.out.println("\n===== Scenario 2: Formulas =====");
        ss.setCellValue("A6", "Total");
        ss.setCellValue("B6", "=SUM(B2:B4)");
        ss.setCellValue("A7", "Average");
        ss.setCellValue("B7", "=AVG(B2:B4)");
        ss.setCellValue("A8", "Min");
        ss.setCellValue("B8", "=MIN(B2:B4)");
        ss.setCellValue("A9", "Max");
        ss.setCellValue("B9", "=MAX(B2:B4)");
        ss.printSheet();

        System.out.println("\n  B6 (SUM) = " + ss.getCellValue("B6"));
        System.out.println("  B7 (AVG) = " + ss.getCellValue("B7"));
        System.out.println("  B8 (MIN) = " + ss.getCellValue("B8"));
        System.out.println("  B9 (MAX) = " + ss.getCellValue("B9"));

        // ===== Scenario 3: Cell references =====
        System.out.println("\n===== Scenario 3: Cell References =====");
        ss.setCellValue("C2", "=B2*2");
        ss.setCellValue("C3", "=B3*2");
        ss.setCellValue("C4", "=B4*2");
        ss.setCellValue("C1", "Double");
        ss.printSheet();

        // ===== Scenario 4: Update & Recalculate =====
        System.out.println("\n===== Scenario 4: Update & Recalculate =====");
        System.out.println("  Updating B2 from 85 to 100...");
        ss.setCellValue("B2", "100");
        ss.recalculate();
        ss.printSheet();
        System.out.println("  B6 (SUM) after update = " + ss.getCellValue("B6"));

        // ===== Scenario 5: Styling (Flyweight) =====
        System.out.println("\n===== Scenario 5: Cell Styling (Flyweight) =====");
        ss.setCellStyle("A1", "Arial", 14, true, "black", "yellow", "CENTER");
        ss.setCellStyle("B1", "Arial", 14, true, "black", "yellow", "CENTER");
        ss.setCellStyle("C1", "Arial", 14, true, "black", "yellow", "CENTER");
        // All 3 headers share the same flyweight style object
        System.out.println("  3 header cells styled — same flyweight style shared");
        ss.printSheet();

        System.out.println("\n✅ All scenarios completed.");
    }
}
