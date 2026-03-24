package TextEditor;

import TextEditor.flyweight.CharacterStyleFactory;
import TextEditor.service.TextEditorService;

/**
 * =====================================================================
 *  Text Editor / Word Processor — LLD (CodeZym #9)
 * =====================================================================
 *
 *  Design Pattern: FLYWEIGHT PATTERN
 *    CharacterStyle objects are shared across characters with the same
 *    formatting. The CharacterStyleFactory caches style instances.
 *
 *  Operations: type, delete, backspace, cursor movement,
 *    formatting (bold/italic/underline/font/color),
 *    find, replace, replaceAll
 *
 *  Link: https://codezym.com/question/9
 * =====================================================================
 */
public class TextEditorMain {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  Text Editor / Word Processor — LLD Demo     ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        TextEditorService editor = new TextEditorService("MyDocument.txt");

        // ===== Scenario 1: Basic typing =====
        System.out.println("\n===== Scenario 1: Basic Typing =====");
        editor.type("Hello, World!");
        editor.printDocument();

        // ===== Scenario 2: Formatting with Flyweight =====
        System.out.println("\n===== Scenario 2: Formatting (Flyweight) =====");
        editor.setBold(true);
        editor.type(" BOLD");
        editor.setBold(false);
        editor.setItalic(true);
        editor.setColor("red");
        editor.type(" italic-red");
        editor.printDetailedDocument();

        System.out.println("\n  → Total characters: " + editor.getDocument().length());
        System.out.println("  → Unique styles (flyweight cache): " + CharacterStyleFactory.getCacheSize());
        System.out.println("  → Memory saved: " + editor.getDocument().length() + " chars share "
                + CharacterStyleFactory.getCacheSize() + " style objects");

        // ===== Scenario 3: Cursor movement & insert =====
        System.out.println("\n===== Scenario 3: Cursor & Insert =====");
        editor.moveCursor(5);
        editor.type(" INSERTED");
        editor.printDocument();

        // ===== Scenario 4: Delete & Backspace =====
        System.out.println("\n===== Scenario 4: Delete & Backspace =====");
        editor.moveCursor(editor.getDocument().length());
        editor.backspace();
        editor.backspace();
        editor.backspace();
        editor.deleteRange(0, 5);
        editor.printDocument();

        // ===== Scenario 5: Find & Replace =====
        System.out.println("\n===== Scenario 5: Find & Replace =====");
        TextEditorService editor2 = new TextEditorService("SearchDoc.txt");
        editor2.type("The quick brown fox jumps over the lazy fox");
        editor2.printDocument();

        int idx = editor2.find("fox");
        System.out.println("  Found 'fox' at index: " + idx);

        boolean replaced = editor2.replace("fox", "cat");
        System.out.println("  Replace first 'fox' → 'cat': " + replaced);
        editor2.printDocument();

        int count = editor2.replaceAll("fox", "dog");
        System.out.println("  ReplaceAll 'fox' → 'dog': " + count + " replacements");
        editor2.printDocument();

        // ===== Scenario 6: Apply style to range =====
        System.out.println("\n===== Scenario 6: Apply Style to Range =====");
        TextEditorService editor3 = new TextEditorService("StyleDoc.txt");
        editor3.type("Hello World Test");
        editor3.applyBold(0, 5);       // "Hello" in bold
        editor3.applyItalic(6, 11);    // "World" in italic
        editor3.applyStyle(12, 16, "Courier", 16, true, true, true, "blue");
        editor3.printDetailedDocument();

        System.out.println("\n  Flyweight cache total: " + CharacterStyleFactory.getCacheSize());
        System.out.println("\n✅ All scenarios completed.");
    }
}
