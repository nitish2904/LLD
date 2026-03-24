package CustomHashMap;

import CustomHashMap.model.CustomHashMap;

/**
 * Custom HashMap — LLD (#43)
 * Array + chaining, dynamic resizing at 75% load factor.
 */
public class CustomHashMapMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Custom HashMap — LLD Demo               ║");
        System.out.println("╚════════════════════════════════════════╝");

        CustomHashMap<String, Integer> map = new CustomHashMap<>(4);
        System.out.println("\n===== Put =====");
        map.put("Alice", 90);
        map.put("Bob", 85);
        map.put("Charlie", 78);
        System.out.println("  " + map);

        System.out.println("\n===== Get =====");
        System.out.println("  Alice: " + map.get("Alice"));
        System.out.println("  Bob: " + map.get("Bob"));
        System.out.println("  Unknown: " + map.get("Unknown"));

        System.out.println("\n===== Update =====");
        map.put("Alice", 100);
        System.out.println("  Alice (updated): " + map.get("Alice"));

        System.out.println("\n===== Resize (triggers at >75% load) =====");
        map.put("David", 88);
        map.put("Eve", 95);
        System.out.println("  " + map);

        System.out.println("\n===== Remove =====");
        System.out.println("  Removed Bob: " + map.remove("Bob"));
        System.out.println("  Removed Unknown: " + map.remove("Unknown"));
        System.out.println("  Contains Bob: " + map.containsKey("Bob"));
        System.out.println("  " + map);

        System.out.println("\n===== Null key =====");
        map.put(null, 999);
        System.out.println("  null key: " + map.get(null));

        System.out.println("\n===== Integer keys =====");
        CustomHashMap<Integer, String> intMap = new CustomHashMap<>();
        for (int i = 0; i < 20; i++) intMap.put(i, "val" + i);
        System.out.println("  size=" + intMap.size() + ", capacity=" + intMap.getCapacity());
        System.out.println("  get(15)=" + intMap.get(15));

        System.out.println("\n✅ Done.");
    }
}
