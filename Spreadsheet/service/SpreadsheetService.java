package Spreadsheet.service;

import Spreadsheet.flyweight.CellStyleFactory;
import Spreadsheet.model.*;

/**
 * Service: manages spreadsheet operations.
 */
public class SpreadsheetService {
    private final Sheet sheet;
    private final FormulaEvaluator evaluator;

    public SpreadsheetService(String sheetName, int rows, int cols) {
        this.sheet = new Sheet(sheetName, rows, cols);
        this.evaluator = new FormulaEvaluator();
    }

    /**
     * Set a cell value. If it starts with '=', treat as formula.
     */
    public void setCellValue(String cellRef, String value) {
        Cell cell = sheet.getCell(cellRef);
        if (cell == null) { System.out.println("  Invalid cell: " + cellRef); return; }

        cell.setRawValue(value);

        if (value == null || value.isEmpty()) {
            cell.setType(CellType.EMPTY);
            cell.setComputedValue("");
        } else if (value.startsWith("=")) {
            cell.setType(CellType.FORMULA);
            Object result = evaluator.evaluate(value, sheet);
            cell.setComputedValue(result);
        } else {
            try {
                double num = Double.parseDouble(value);
                cell.setType(CellType.NUMBER);
                cell.setComputedValue(num);
            } catch (NumberFormatException e) {
                cell.setType(CellType.STRING);
                cell.setComputedValue(value);
            }
        }
    }

    public String getCellValue(String cellRef) {
        Cell cell = sheet.getCell(cellRef);
        return cell != null ? cell.getDisplayValue() : "";
    }

    /**
     * Apply style to a cell.
     */
    public void setCellStyle(String cellRef, String font, int size, boolean bold,
                              String textColor, String bgColor, String alignment) {
        Cell cell = sheet.getCell(cellRef);
        if (cell == null) return;
        cell.setStyle(CellStyleFactory.getStyle(font, size, bold, textColor, bgColor, alignment));
    }

    /**
     * Recalculate all formulas in the sheet.
     */
    public void recalculate() {
        for (int r = 0; r < sheet.getRows(); r++) {
            for (int c = 0; c < sheet.getCols(); c++) {
                Cell cell = sheet.getCell(r, c);
                if (cell.getType() == CellType.FORMULA) {
                    Object result = evaluator.evaluate(cell.getRawValue(), sheet);
                    cell.setComputedValue(result);
                }
            }
        }
    }

    /**
     * Print the sheet in a table format.
     */
    public void printSheet() {
        System.out.println("\n  Sheet: " + sheet.getName());
        // Header
        System.out.printf("  %4s", "");
        for (int c = 0; c < sheet.getCols(); c++) {
            System.out.printf(" | %10s", (char)('A' + c));
        }
        System.out.println(" |");
        System.out.println("  " + "-".repeat(4 + (sheet.getCols() * 13) + 1));

        // Rows
        for (int r = 0; r < sheet.getRows(); r++) {
            boolean hasContent = false;
            for (int c = 0; c < sheet.getCols(); c++) {
                if (sheet.getCell(r, c).getType() != CellType.EMPTY) { hasContent = true; break; }
            }
            if (!hasContent) continue;

            System.out.printf("  %4d", r + 1);
            for (int c = 0; c < sheet.getCols(); c++) {
                System.out.printf(" | %10s", sheet.getCell(r, c).getDisplayValue());
            }
            System.out.println(" |");
        }
        System.out.println("  Flyweight styles cached: " + CellStyleFactory.getCacheSize());
    }

    public Sheet getSheet() { return sheet; }
}
