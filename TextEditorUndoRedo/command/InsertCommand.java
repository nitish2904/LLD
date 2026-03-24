package TextEditorUndoRedo.command;

import TextEditorUndoRedo.model.TextBuffer;

/**
 * Command to insert text at a position.
 */
public class InsertCommand implements Command {
    private final TextBuffer buffer;
    private final int position;
    private final String text;

    public InsertCommand(TextBuffer buffer, int position, String text) {
        this.buffer = buffer;
        this.position = position;
        this.text = text;
    }

    @Override
    public void execute() { buffer.insert(position, text); }

    @Override
    public void undo() { buffer.delete(position, text.length()); }

    @Override
    public String describe() { return "INSERT \"" + text + "\" at " + position; }
}
