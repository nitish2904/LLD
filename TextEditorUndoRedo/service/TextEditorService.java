package TextEditorUndoRedo.service;

import TextEditorUndoRedo.command.*;
import TextEditorUndoRedo.model.TextBuffer;

/**
 * Text editor with undo/redo powered by Command Pattern.
 */
public class TextEditorService {
    private final TextBuffer buffer;
    private final CommandHistory history;

    public TextEditorService() {
        this.buffer = new TextBuffer();
        this.history = new CommandHistory();
    }

    public void type(String text) {
        Command cmd = new InsertCommand(buffer, buffer.getCursor(), text);
        history.execute(cmd);
    }

    public void insertAt(int position, String text) {
        Command cmd = new InsertCommand(buffer, position, text);
        history.execute(cmd);
    }

    public void delete(int position, int length) {
        Command cmd = new DeleteCommand(buffer, position, length);
        history.execute(cmd);
    }

    public void backspace(int count) {
        int pos = buffer.getCursor();
        int actual = Math.min(count, pos);
        if (actual > 0) delete(pos - actual, actual);
    }

    public void replace(int position, int oldLen, String newText) {
        Command cmd = new ReplaceCommand(buffer, position, oldLen, newText);
        history.execute(cmd);
    }

    public boolean undo() {
        boolean ok = history.undo();
        System.out.println("  " + (ok ? "Undo" : "Nothing to undo") + " → \"" + buffer.getText() + "\"");
        return ok;
    }

    public boolean redo() {
        boolean ok = history.redo();
        System.out.println("  " + (ok ? "Redo" : "Nothing to redo") + " → \"" + buffer.getText() + "\"");
        return ok;
    }

    public String getText() { return buffer.getText(); }
    public void print() { System.out.println("  " + buffer); }
}
