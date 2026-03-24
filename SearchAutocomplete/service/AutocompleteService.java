package SearchAutocomplete.service;

import SearchAutocomplete.model.TrieNode;
import java.util.*;

/**
 * Google Search Autocomplete — Trie with frequency-based ranking.
 */
public class AutocompleteService {
    private final TrieNode root = new TrieNode();

    public void insert(String sentence, int frequency) {
        TrieNode node = root;
        for (char c : sentence.toLowerCase().toCharArray()) {
            node = node.getChildren().computeIfAbsent(c, k -> new TrieNode());
        }
        node.setEndOfWord(true);
        node.setFrequency(node.getFrequency() + frequency);
    }

    public void search(String sentence) {
        insert(sentence, 1); // every search increments frequency
    }

    /**
     * Get top-k autocomplete suggestions sorted by frequency (desc).
     */
    public List<String> autocomplete(String prefix, int k) {
        prefix = prefix.toLowerCase();
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.getChildren().get(c);
            if (node == null) return Collections.emptyList();
        }

        // Collect all words with their frequencies
        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        collectAll(node, new StringBuilder(prefix), pq);

        List<String> results = new ArrayList<>();
        while (!pq.isEmpty() && results.size() < k) {
            results.add(pq.poll().getKey());
        }
        return results;
    }

    private void collectAll(TrieNode node, StringBuilder sb, PriorityQueue<Map.Entry<String, Integer>> pq) {
        if (node.isEndOfWord()) {
            pq.offer(Map.entry(sb.toString(), node.getFrequency()));
        }
        for (var entry : node.getChildren().entrySet()) {
            sb.append(entry.getKey());
            collectAll(entry.getValue(), sb, pq);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public int size() {
        int[] count = {0};
        countWords(root, count);
        return count[0];
    }

    private void countWords(TrieNode node, int[] count) {
        if (node.isEndOfWord()) count[0]++;
        for (TrieNode child : node.getChildren().values()) countWords(child, count);
    }
}
