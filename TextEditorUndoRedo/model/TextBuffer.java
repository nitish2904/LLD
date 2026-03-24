package TextEditorUndoRedo.model;

/**
 * Simple text buffer that commands operate on.
 */
public class TextBuffer {
    private final StringBuilder content;
    private int cursor;

    public TextBuffer() {
        this.content = new StringBuilder();
        this.cursor = 0;
    }

    public void insert(int position, String text) {
        content.insert(position, text);
        cursor = position + text.length();
    }

    public String delete(int position, int length) {
        String deleted = content.substring(position, position + length);
        content.delete(position, position + length);
        cursor = position;
        return deleted;
    }

    public String getText() { return content.toString(); }
    public int length() { return content.length(); }
    public int getCursor() { return cursor; }
    public void setCursor(int c) { this.cursor = Math.max(0, Math.min(c, content.length())); }

    @Override
    public String toString() {
        return "Buffer{\"" + content + "\", cursor=" + cursor + "}";
    }
}
