package Spreadsheet.flyweight;

import Spreadsheet.model.CellStyle;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Factory for CellStyle objects.
 */
public class CellStyleFactory {
    private static final Map<CellStyle, CellStyle> cache = new HashMap<>();

    public static CellStyle getStyle(String font, int size, boolean bold,
                                      String textColor, String bgColor, String alignment) {
        CellStyle key = new CellStyle(font, size, bold, textColor, bgColor, alignment);
        return cache.computeIfAbsent(key, k -> k);
    }

    public static CellStyle getDefaultStyle() {
        return getStyle("Arial", 11, false, "black", "white", "LEFT");
    }

    public static int getCacheSize() { return cache.size(); }
}
