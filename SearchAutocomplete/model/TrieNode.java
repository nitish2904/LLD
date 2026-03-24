package SearchAutocomplete.model;

import java.util.*;

public class TrieNode {
    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean endOfWord;
    private int frequency; // search frequency for ranking

    public Map<Character, TrieNode> getChildren() { return children; }
    public boolean isEndOfWord() { return endOfWord; }
    public void setEndOfWord(boolean e) { this.endOfWord = e; }
    public int getFrequency() { return frequency; }
    public void incrementFrequency() { frequency++; }
    public void setFrequency(int f) { this.frequency = f; }
}
