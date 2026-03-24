package TextEditorUndoRedo.command;

import TextEditorUndoRedo.model.TextBuffer;

/**
 * Command to replace text — composite of delete + insert.
 */
public class ReplaceCommand implements Command {
    private final TextBuffer buffer;
    private final int position;
    private final int oldLength;
    private final String newText;
    private String oldText; // saved for undo

    public ReplaceCommand(TextBuffer buffer, int position, int oldLength, String newText) {
        this.buffer = buffer;
        this.position = position;
        this.oldLength = oldLength;
        this.newText = newText;
    }

    @Override
    public void execute() {
        oldText = buffer.delete(position, oldLength);
        buffer.insert(position, newText);
    }

    @Override
    public void undo() {
        buffer.delete(position, newText.length());
        buffer.insert(position, oldText);
    }

    @Override
    public String describe() { return "REPLACE \"" + oldText + "\" → \"" + newText + "\" at " + position; }
}
