package Spreadsheet.model;

/**
 * A single cell in the spreadsheet.
 */
public class Cell {
    private final int row;
    private final int col;
    private String rawValue;    // what user typed (could be "=A1+B2")
    private Object computedValue; // evaluated result
    private CellType type;
    private CellStyle style;    // flyweight reference

    public Cell(int row, int col, CellStyle defaultStyle) {
        this.row = row;
        this.col = col;
        this.rawValue = "";
        this.computedValue = "";
        this.type = CellType.EMPTY;
        this.style = defaultStyle;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public String getRawValue() { return rawValue; }
    public Object getComputedValue() { return computedValue; }
    public CellType getType() { return type; }
    public CellStyle getStyle() { return style; }

    public void setRawValue(String rawValue) { this.rawValue = rawValue; }
    public void setComputedValue(Object computedValue) { this.computedValue = computedValue; }
    public void setType(CellType type) { this.type = type; }
    public void setStyle(CellStyle style) { this.style = style; }

    public String getDisplayValue() {
        if (type == CellType.EMPTY) return "";
        if (computedValue instanceof Double d) {
            return d == Math.floor(d) ? String.valueOf(d.intValue()) : String.valueOf(d);
        }
        return String.valueOf(computedValue);
    }

    public String getCellId() {
        return "" + (char)('A' + col) + (row + 1);
    }

    @Override
    public String toString() {
        return getCellId() + "=" + getDisplayValue();
    }
}
