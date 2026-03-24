package Dictionary;

import Dictionary.model.WordEntry;
import Dictionary.service.DictionaryService;

/**
 * Dictionary App — LLD (CodeZym #26)
 * Uses Trie for autocomplete, HashMap for O(1) lookup.
 * Patterns: Repository Pattern (SRP), Builder-style fluent API.
 */
public class DictionaryMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Dictionary App — LLD Demo              ║");
        System.out.println("╚════════════════════════════════════════╝");

        DictionaryService dict = new DictionaryService();

        // Add words
        dict.addWord(new WordEntry("happy", "adjective")
                .addDefinition("Feeling or showing pleasure")
                .addDefinition("Fortunate and convenient")
                .addSynonym("joyful").addSynonym("cheerful").addSynonym("content")
                .addAntonym("sad").addAntonym("unhappy")
                .addExample("She felt happy after the good news."));

        dict.addWord(new WordEntry("run", "verb")
                .addDefinition("Move at a speed faster than a walk")
                .addDefinition("Manage or operate")
                .addSynonym("sprint").addSynonym("dash").addSynonym("jog")
                .addAntonym("walk").addAntonym("stop")
                .addExample("He runs every morning."));

        dict.addWord(new WordEntry("run", "noun")
                .addDefinition("An act or spell of running")
                .addExample("She went for a run in the park."));

        dict.addWord(new WordEntry("happiness", "noun")
                .addDefinition("The state of being happy")
                .addSynonym("joy").addSynonym("bliss"));

        dict.addWord(new WordEntry("happen", "verb")
                .addDefinition("Take place; occur")
                .addSynonym("occur").addSynonym("transpire"));

        dict.addWord(new WordEntry("hash", "noun")
                .addDefinition("A dish of cooked meat cut into small pieces")
                .addDefinition("The # symbol"));

        dict.addWord(new WordEntry("hashmap", "noun")
                .addDefinition("A data structure mapping keys to values"));

        // Lookup
        System.out.println("\n===== Lookup =====");
        dict.lookup("happy");
        dict.lookup("run");
        dict.lookup("unknown");

        // Autocomplete
        System.out.println("\n===== Autocomplete =====");
        dict.autocomplete("hap");
        dict.autocomplete("has");
        dict.autocomplete("ru");
        dict.autocomplete("xyz");

        // Remove
        System.out.println("\n===== Remove =====");
        dict.removeWord("hashmap");
        dict.autocomplete("has");

        System.out.println("\n  Dictionary size: " + dict.size());
        System.out.println("\n✅ Done.");
    }
}
