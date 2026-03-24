package TextEditorUndoRedo.command;

import TextEditorUndoRedo.model.TextBuffer;

/**
 * Command to delete text at a position.
 */
public class DeleteCommand implements Command {
    private final TextBuffer buffer;
    private final int position;
    private final int length;
    private String deletedText; // saved for undo

    public DeleteCommand(TextBuffer buffer, int position, int length) {
        this.buffer = buffer;
        this.position = position;
        this.length = length;
    }

    @Override
    public void execute() { deletedText = buffer.delete(position, length); }

    @Override
    public void undo() { buffer.insert(position, deletedText); }

    @Override
    public String describe() { return "DELETE " + length + " chars at " + position + " (\"" + deletedText + "\")"; }
}
