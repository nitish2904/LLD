package TextEditor.model;

/**
 * A single character in the document.
 * Extrinsic state: the char value and its position.
 * Intrinsic state: the CharacterStyle (flyweight, shared).
 */
public class Character {
    private final char value;
    private CharacterStyle style; // flyweight reference

    public Character(char value, CharacterStyle style) {
        this.value = value;
        this.style = style;
    }

    public char getValue() { return value; }
    public CharacterStyle getStyle() { return style; }
    public void setStyle(CharacterStyle style) { this.style = style; }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public String toDetailedString() {
        return "'" + value + "'[" + style + "]";
    }
}
