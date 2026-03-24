package Dictionary.repository;

import Dictionary.model.WordEntry;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Repository: stores and retrieves word entries using a Trie for prefix search
 * and a HashMap for O(1) exact lookup. (Single Responsibility)
 */
public class DictionaryRepository {
    private final Map<String, List<WordEntry>> wordMap;  // exact lookup
    private final TrieNode trieRoot;                      // prefix search

    public DictionaryRepository() {
        this.wordMap = new HashMap<>();
        this.trieRoot = new TrieNode();
    }

    public void addWord(WordEntry entry) {
        String key = entry.getWord().toLowerCase();
        wordMap.computeIfAbsent(key, k -> new ArrayList<>()).add(entry);
        insertIntoTrie(key);
    }

    public List<WordEntry> lookup(String word) {
        return wordMap.getOrDefault(word.toLowerCase(), Collections.emptyList());
    }

    public boolean removeWord(String word) {
        return wordMap.remove(word.toLowerCase()) != null;
    }

    /**
     * Autocomplete: find all words starting with prefix.
     */
    public List<String> autocomplete(String prefix, int limit) {
        prefix = prefix.toLowerCase();
        TrieNode node = trieRoot;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return Collections.emptyList();
        }
        List<String> results = new ArrayList<>();
        collectWords(node, new StringBuilder(prefix), results, limit);
        return results;
    }

    public int size() { return wordMap.size(); }

    // --- Trie implementation ---

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord;
    }

    private void insertIntoTrie(String word) {
        TrieNode node = trieRoot;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isEndOfWord = true;
    }

    private void collectWords(TrieNode node, StringBuilder prefix, List<String> results, int limit) {
        if (results.size() >= limit) return;
        if (node.isEndOfWord) results.add(prefix.toString());
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            prefix.append(entry.getKey());
            collectWords(entry.getValue(), prefix, results, limit);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
