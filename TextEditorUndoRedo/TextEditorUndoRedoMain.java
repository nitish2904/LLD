package TextEditorUndoRedo;

import TextEditorUndoRedo.service.TextEditorService;

/**
 * Text Editor with Undo & Redo — LLD (CodeZym #27)
 * Pattern: COMMAND PATTERN — each edit is a Command with execute()/undo().
 */
public class TextEditorUndoRedoMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Text Editor Undo/Redo — LLD Demo      ║");
        System.out.println("╚════════════════════════════════════════╝");

        TextEditorService editor = new TextEditorService();

        System.out.println("\n===== Type =====");
        editor.type("Hello");
        editor.print();
        editor.type(" World");
        editor.print();
        editor.type("!");
        editor.print();

        System.out.println("\n===== Undo =====");
        editor.undo(); // removes "!"
        editor.undo(); // removes " World"
        editor.undo(); // removes "Hello"

        System.out.println("\n===== Redo =====");
        editor.redo(); // restores "Hello"
        editor.redo(); // restores " World"

        System.out.println("\n===== Type after undo (clears redo) =====");
        editor.type(" Java");
        editor.print();
        editor.redo(); // should fail — redo cleared

        System.out.println("\n===== Delete & Replace =====");
        editor.delete(0, 5); // remove "Hello"
        editor.print();
        editor.replace(0, 5, "Beautiful"); // replace " Java" with "Beautiful"
        editor.print();

        System.out.println("\n===== Undo all =====");
        editor.undo();
        editor.undo();
        editor.undo();
        editor.undo();

        System.out.println("\n===== Backspace =====");
        editor.type("ABCDEF");
        editor.print();
        editor.backspace(3); // remove DEF
        editor.print();
        editor.undo();
        editor.print();

        System.out.println("\n✅ Done.");
    }
}
