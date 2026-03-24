package PhoneDirectory.service;

import PhoneDirectory.model.Contact;
import java.util.*;

/**
 * Phone Directory using a Trie for prefix search on names.
 */
public class PhoneDirectoryService {
    private final TrieNode root = new TrieNode();
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(String name, String phone) {
        Contact c = new Contact(name, phone);
        contacts.put(name.toLowerCase(), c);
        insertTrie(name.toLowerCase());
        System.out.println("  Added: " + c);
    }

    public Contact lookup(String name) {
        return contacts.get(name.toLowerCase());
    }

    public List<Contact> search(String prefix) {
        prefix = prefix.toLowerCase();
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) return Collections.emptyList();
        }
        List<String> names = new ArrayList<>();
        collectAll(node, new StringBuilder(prefix), names, 10);
        List<Contact> results = new ArrayList<>();
        for (String n : names) {
            Contact c = contacts.get(n);
            if (c != null) results.add(c);
        }
        return results;
    }

    public boolean deleteContact(String name) {
        return contacts.remove(name.toLowerCase()) != null;
    }

    public int size() { return contacts.size(); }

    // Trie internals
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean endOfWord;
    }

    private void insertTrie(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) node = node.children.computeIfAbsent(c, k -> new TrieNode());
        node.endOfWord = true;
    }

    private void collectAll(TrieNode node, StringBuilder sb, List<String> results, int limit) {
        if (results.size() >= limit) return;
        if (node.endOfWord) results.add(sb.toString());
        for (var e : node.children.entrySet()) {
            sb.append(e.getKey());
            collectAll(e.getValue(), sb, results, limit);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
