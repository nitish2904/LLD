package TextEditor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The document — a list of characters with styles.
 */
public class Document {
    private final String name;
    private final List<Character> characters;
    private int cursorPosition;

    public Document(String name) {
        this.name = name;
        this.characters = new ArrayList<>();
        this.cursorPosition = 0;
    }

    public String getName() { return name; }
    public List<Character> getCharacters() { return characters; }
    public int getCursorPosition() { return cursorPosition; }
    public void setCursorPosition(int pos) {
        this.cursorPosition = Math.max(0, Math.min(pos, characters.size()));
    }

    public int length() { return characters.size(); }

    /**
     * Insert a character at the cursor position.
     */
    public void insertAt(int position, Character ch) {
        characters.add(position, ch);
        cursorPosition = position + 1;
    }

    /**
     * Delete a character at the given position.
     */
    public Character deleteAt(int position) {
        if (position < 0 || position >= characters.size()) return null;
        Character removed = characters.remove(position);
        if (cursorPosition > position) cursorPosition--;
        return removed;
    }

    /**
     * Get plain text content.
     */
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (Character ch : characters) {
            sb.append(ch.getValue());
        }
        return sb.toString();
    }

    /**
     * Get text in a range [start, end).
     */
    public String getText(int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end && i < characters.size(); i++) {
            sb.append(characters.get(i).getValue());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Document{'" + name + "', len=" + characters.size() + ", cursor=" + cursorPosition + "}";
    }
}
