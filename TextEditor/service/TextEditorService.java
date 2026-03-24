package TextEditor.service;

import TextEditor.flyweight.CharacterStyleFactory;
import TextEditor.model.Character;
import TextEditor.model.CharacterStyle;
import TextEditor.model.Document;

/**
 * Service layer: provides text editor operations.
 */
public class TextEditorService {
    private final Document document;
    private CharacterStyle currentStyle;

    public TextEditorService(String documentName) {
        this.document = new Document(documentName);
        this.currentStyle = CharacterStyleFactory.getDefaultStyle();
    }

    // --- Typing ---

    public void type(String text) {
        for (char c : text.toCharArray()) {
            Character ch = new Character(c, currentStyle);
            document.insertAt(document.getCursorPosition(), ch);
        }
    }

    public void typeAt(int position, String text) {
        document.setCursorPosition(position);
        type(text);
    }

    // --- Deletion ---

    public void backspace() {
        int pos = document.getCursorPosition();
        if (pos > 0) {
            document.deleteAt(pos - 1);
        }
    }

    public void deleteRange(int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            document.deleteAt(i);
        }
    }

    // --- Cursor ---

    public void moveCursor(int position) {
        document.setCursorPosition(position);
    }

    public void moveCursorLeft() {
        document.setCursorPosition(document.getCursorPosition() - 1);
    }

    public void moveCursorRight() {
        document.setCursorPosition(document.getCursorPosition() + 1);
    }

    // --- Formatting (Flyweight) ---

    public void setCurrentStyle(String font, int size, boolean bold,
                                 boolean italic, boolean underline, String color) {
        this.currentStyle = CharacterStyleFactory.getStyle(font, size, bold, italic, underline, color);
    }

    public void setBold(boolean bold) {
        this.currentStyle = CharacterStyleFactory.getStyle(
                currentStyle.getFontFamily(), currentStyle.getFontSize(),
                bold, currentStyle.isItalic(), currentStyle.isUnderline(), currentStyle.getColor());
    }

    public void setItalic(boolean italic) {
        this.currentStyle = CharacterStyleFactory.getStyle(
                currentStyle.getFontFamily(), currentStyle.getFontSize(),
                currentStyle.isBold(), italic, currentStyle.isUnderline(), currentStyle.getColor());
    }

    public void setFontSize(int size) {
        this.currentStyle = CharacterStyleFactory.getStyle(
                currentStyle.getFontFamily(), size,
                currentStyle.isBold(), currentStyle.isItalic(), currentStyle.isUnderline(), currentStyle.getColor());
    }

    public void setColor(String color) {
        this.currentStyle = CharacterStyleFactory.getStyle(
                currentStyle.getFontFamily(), currentStyle.getFontSize(),
                currentStyle.isBold(), currentStyle.isItalic(), currentStyle.isUnderline(), color);
    }

    /**
     * Apply a style to a range of characters [start, end).
     */
    public void applyStyle(int start, int end, String font, int size,
                           boolean bold, boolean italic, boolean underline, String color) {
        CharacterStyle style = CharacterStyleFactory.getStyle(font, size, bold, italic, underline, color);
        for (int i = start; i < end && i < document.length(); i++) {
            document.getCharacters().get(i).setStyle(style);
        }
    }

    public void applyBold(int start, int end) {
        for (int i = start; i < end && i < document.length(); i++) {
            CharacterStyle old = document.getCharacters().get(i).getStyle();
            CharacterStyle newStyle = CharacterStyleFactory.getStyle(
                    old.getFontFamily(), old.getFontSize(), true,
                    old.isItalic(), old.isUnderline(), old.getColor());
            document.getCharacters().get(i).setStyle(newStyle);
        }
    }

    public void applyItalic(int start, int end) {
        for (int i = start; i < end && i < document.length(); i++) {
            CharacterStyle old = document.getCharacters().get(i).getStyle();
            CharacterStyle newStyle = CharacterStyleFactory.getStyle(
                    old.getFontFamily(), old.getFontSize(), old.isBold(),
                    true, old.isUnderline(), old.getColor());
            document.getCharacters().get(i).setStyle(newStyle);
        }
    }

    // --- Find / Replace ---

    public int find(String target) {
        String text = document.getText();
        return text.indexOf(target);
    }

    public boolean replace(String target, String replacement) {
        int idx = find(target);
        if (idx == -1) return false;
        deleteRange(idx, idx + target.length());
        document.setCursorPosition(idx);
        type(replacement);
        return true;
    }

    public int replaceAll(String target, String replacement) {
        int count = 0;
        while (replace(target, replacement)) {
            count++;
        }
        return count;
    }

    // --- Display ---

    public String getText() { return document.getText(); }
    public Document getDocument() { return document; }

    public void printDocument() {
        System.out.println("\n" + document);
        System.out.println("  Text: \"" + document.getText() + "\"");
        System.out.println("  Flyweight cache size: " + CharacterStyleFactory.getCacheSize());
    }

    public void printDetailedDocument() {
        System.out.println("\n" + document);
        for (int i = 0; i < document.length(); i++) {
            System.out.println("  [" + i + "] " + document.getCharacters().get(i).toDetailedString());
        }
        System.out.println("  Flyweight cache size: " + CharacterStyleFactory.getCacheSize());
    }
}
