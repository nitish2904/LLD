package TextEditor.model;

import java.util.Objects;

/**
 * Flyweight — shared intrinsic state for character formatting.
 * Instances are cached and reused via CharacterStyleFactory.
 */
public class CharacterStyle {
    private final String fontFamily;
    private final int fontSize;
    private final boolean bold;
    private final boolean italic;
    private final boolean underline;
    private final String color;

    public CharacterStyle(String fontFamily, int fontSize, boolean bold,
                          boolean italic, boolean underline, String color) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.color = color;
    }

    public String getFontFamily() { return fontFamily; }
    public int getFontSize() { return fontSize; }
    public boolean isBold() { return bold; }
    public boolean isItalic() { return italic; }
    public boolean isUnderline() { return underline; }
    public String getColor() { return color; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterStyle s)) return false;
        return fontSize == s.fontSize && bold == s.bold && italic == s.italic &&
               underline == s.underline &&
               Objects.equals(fontFamily, s.fontFamily) && Objects.equals(color, s.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fontFamily, fontSize, bold, italic, underline, color);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(fontFamily).append("/").append(fontSize);
        if (bold) sb.append("/B");
        if (italic) sb.append("/I");
        if (underline) sb.append("/U");
        sb.append("/").append(color);
        return sb.toString();
    }
}
