package Dictionary.service;

import Dictionary.model.WordEntry;
import Dictionary.repository.DictionaryRepository;

import java.util.List;

/**
 * Service layer: business logic for dictionary operations. (SRP)
 */
public class DictionaryService {
    private final DictionaryRepository repository;

    public DictionaryService() {
        this.repository = new DictionaryRepository();
    }

    public void addWord(WordEntry entry) {
        repository.addWord(entry);
        System.out.println("  Added: " + entry.getWord() + " (" + entry.getPartOfSpeech() + ")");
    }

    public void lookup(String word) {
        List<WordEntry> entries = repository.lookup(word);
        if (entries.isEmpty()) {
            System.out.println("  '" + word + "' not found.");
            return;
        }
        System.out.println("  Found " + entries.size() + " entry(ies) for '" + word + "':");
        for (WordEntry e : entries) {
            System.out.println(e);
        }
    }

    public void autocomplete(String prefix) {
        List<String> suggestions = repository.autocomplete(prefix, 10);
        System.out.println("  Autocomplete '" + prefix + "': " + suggestions);
    }

    public void removeWord(String word) {
        boolean removed = repository.removeWord(word);
        System.out.println("  Remove '" + word + "': " + (removed ? "success" : "not found"));
    }

    public int size() { return repository.size(); }
}
