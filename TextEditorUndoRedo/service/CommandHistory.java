package TextEditorUndoRedo.service;

import TextEditorUndoRedo.command.Command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Manages undo/redo stacks. (Single Responsibility)
 */
public class CommandHistory {
    private final Deque<Command> undoStack;
    private final Deque<Command> redoStack;

    public CommandHistory() {
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new ArrayDeque<>();
    }

    public void execute(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // new command invalidates redo history
    }

    public boolean undo() {
        if (undoStack.isEmpty()) return false;
        Command cmd = undoStack.pop();
        cmd.undo();
        redoStack.push(cmd);
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) return false;
        Command cmd = redoStack.pop();
        cmd.execute();
        undoStack.push(cmd);
        return true;
    }

    public boolean canUndo() { return !undoStack.isEmpty(); }
    public boolean canRedo() { return !redoStack.isEmpty(); }
    public int undoSize() { return undoStack.size(); }
    public int redoSize() { return redoStack.size(); }
}
