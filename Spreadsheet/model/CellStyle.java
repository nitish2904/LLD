package Spreadsheet.model;

import java.util.Objects;

/**
 * Flyweight — shared cell formatting (font, size, bold, color, alignment).
 */
public class CellStyle {
    private final String fontFamily;
    private final int fontSize;
    private final boolean bold;
    private final String textColor;
    private final String bgColor;
    private final String alignment; // LEFT, CENTER, RIGHT

    public CellStyle(String fontFamily, int fontSize, boolean bold,
                     String textColor, String bgColor, String alignment) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.bold = bold;
        this.textColor = textColor;
        this.bgColor = bgColor;
        this.alignment = alignment;
    }

    public String getFontFamily() { return fontFamily; }
    public int getFontSize() { return fontSize; }
    public boolean isBold() { return bold; }
    public String getTextColor() { return textColor; }
    public String getBgColor() { return bgColor; }
    public String getAlignment() { return alignment; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CellStyle s)) return false;
        return fontSize == s.fontSize && bold == s.bold &&
               Objects.equals(fontFamily, s.fontFamily) && Objects.equals(textColor, s.textColor) &&
               Objects.equals(bgColor, s.bgColor) && Objects.equals(alignment, s.alignment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fontFamily, fontSize, bold, textColor, bgColor, alignment);
    }

    @Override
    public String toString() {
        return fontFamily + "/" + fontSize + (bold ? "/B" : "") + "/" + textColor + "/" + bgColor + "/" + alignment;
    }
}
