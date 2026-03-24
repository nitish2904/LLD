package SearchAutocomplete;

import SearchAutocomplete.service.AutocompleteService;
import java.util.List;

/**
 * Google Search Autocomplete — LLD (#10642)
 * Trie + frequency ranking for top-k suggestions.
 */
public class SearchAutocompleteMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Search Autocomplete — LLD Demo          ║");
        System.out.println("╚════════════════════════════════════════╝");

        AutocompleteService ac = new AutocompleteService();

        // Seed with historical search data
        ac.insert("amazon prime", 10);
        ac.insert("amazon web services", 8);
        ac.insert("amazon kindle", 5);
        ac.insert("amazon music", 3);
        ac.insert("apple iphone", 7);
        ac.insert("apple macbook", 6);
        ac.insert("apple watch", 4);
        ac.insert("google maps", 9);
        ac.insert("google drive", 6);
        ac.insert("google search", 12);

        System.out.println("\n===== Autocomplete 'ama' (top 3) =====");
        List<String> results = ac.autocomplete("ama", 3);
        results.forEach(r -> System.out.println("  " + r));

        System.out.println("\n===== Autocomplete 'app' (top 3) =====");
        ac.autocomplete("app", 3).forEach(r -> System.out.println("  " + r));

        System.out.println("\n===== Autocomplete 'goo' (top 3) =====");
        ac.autocomplete("goo", 3).forEach(r -> System.out.println("  " + r));

        System.out.println("\n===== User searches (boosts frequency) =====");
        ac.search("amazon music");
        ac.search("amazon music");
        ac.search("amazon music");
        ac.search("amazon music");
        ac.search("amazon music");
        // Now amazon music has freq 3+5=8, same as AWS
        System.out.println("  After 5 searches for 'amazon music':");
        ac.autocomplete("ama", 5).forEach(r -> System.out.println("  " + r));

        System.out.println("\n===== Autocomplete 'xyz' (no match) =====");
        System.out.println("  " + ac.autocomplete("xyz", 3));

        System.out.println("\n  Total entries: " + ac.size());
        System.out.println("\n✅ Done.");
    }
}
