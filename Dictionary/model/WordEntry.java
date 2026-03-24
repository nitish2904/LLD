package Dictionary.model;

import java.util.*;

/**
 * Represents a word entry with definitions, synonyms, antonyms, examples.
 */
public class WordEntry {
    private final String word;
    private final List<String> definitions;
    private final List<String> synonyms;
    private final List<String> antonyms;
    private final List<String> examples;
    private final String partOfSpeech; // noun, verb, adjective, etc.

    public WordEntry(String word, String partOfSpeech) {
        this.word = word.toLowerCase();
        this.partOfSpeech = partOfSpeech;
        this.definitions = new ArrayList<>();
        this.synonyms = new ArrayList<>();
        this.antonyms = new ArrayList<>();
        this.examples = new ArrayList<>();
    }

    public String getWord() { return word; }
    public String getPartOfSpeech() { return partOfSpeech; }
    public List<String> getDefinitions() { return definitions; }
    public List<String> getSynonyms() { return synonyms; }
    public List<String> getAntonyms() { return antonyms; }
    public List<String> getExamples() { return examples; }

    public WordEntry addDefinition(String def) { definitions.add(def); return this; }
    public WordEntry addSynonym(String syn) { synonyms.add(syn); return this; }
    public WordEntry addAntonym(String ant) { antonyms.add(ant); return this; }
    public WordEntry addExample(String ex) { examples.add(ex); return this; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(word).append(" (").append(partOfSpeech).append(")\n");
        for (int i = 0; i < definitions.size(); i++)
            sb.append("  ").append(i + 1).append(". ").append(definitions.get(i)).append("\n");
        if (!synonyms.isEmpty()) sb.append("  Synonyms: ").append(String.join(", ", synonyms)).append("\n");
        if (!antonyms.isEmpty()) sb.append("  Antonyms: ").append(String.join(", ", antonyms)).append("\n");
        if (!examples.isEmpty()) {
            sb.append("  Examples:\n");
            for (String ex : examples) sb.append("    - ").append(ex).append("\n");
        }
        return sb.toString();
    }
}
