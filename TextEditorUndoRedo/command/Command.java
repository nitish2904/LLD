package TextEditorUndoRedo.command;

/**
 * Command Pattern interface. Each operation knows how to execute and undo itself.
 */
public interface Command {
    void execute();
    void undo();
    String describe();
}
