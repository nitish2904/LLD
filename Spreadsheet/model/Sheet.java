package Spreadsheet.model;

import Spreadsheet.flyweight.CellStyleFactory;

/**
 * A sheet (grid of cells).
 */
public class Sheet {
    private final String name;
    private final int rows;
    private final int cols;
    private final Cell[][] grid;

    public Sheet(String name, int rows, int cols) {
        this.name = name;
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        CellStyle defaultStyle = CellStyleFactory.getDefaultStyle();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Cell(r, c, defaultStyle);
            }
        }
    }

    public String getName() { return name; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) return null;
        return grid[row][col];
    }

    /**
     * Parse cell reference like "A1" → row=0, col=0; "B3" → row=2, col=1
     */
    public Cell getCell(String ref) {
        ref = ref.trim().toUpperCase();
        int col = ref.charAt(0) - 'A';
        int row = Integer.parseInt(ref.substring(1)) - 1;
        return getCell(row, col);
    }

    public Cell[][] getGrid() { return grid; }
}
