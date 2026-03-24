package TextEditor.flyweight;

import TextEditor.model.CharacterStyle;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Factory — caches and reuses CharacterStyle objects.
 * Dramatically reduces memory when many characters share the same formatting.
 */
public class CharacterStyleFactory {
    private static final Map<CharacterStyle, CharacterStyle> cache = new HashMap<>();

    /**
     * Get or create a cached CharacterStyle.
     */
    public static CharacterStyle getStyle(String fontFamily, int fontSize, boolean bold,
                                           boolean italic, boolean underline, String color) {
        CharacterStyle key = new CharacterStyle(fontFamily, fontSize, bold, italic, underline, color);
        return cache.computeIfAbsent(key, k -> k);
    }

    /**
     * Default style: Arial 12pt black.
     */
    public static CharacterStyle getDefaultStyle() {
        return getStyle("Arial", 12, false, false, false, "black");
    }

    public static int getCacheSize() { return cache.size(); }

    public static void clearCache() { cache.clear(); }
}
