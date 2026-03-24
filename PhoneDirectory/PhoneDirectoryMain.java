package PhoneDirectory;

import PhoneDirectory.model.Contact;
import PhoneDirectory.service.PhoneDirectoryService;
import java.util.List;

/**
 * Phone Directory — LLD (#10379)
 * Trie for prefix search on contact names.
 */
public class PhoneDirectoryMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Phone Directory — LLD Demo              ║");
        System.out.println("╚════════════════════════════════════════╝");

        PhoneDirectoryService dir = new PhoneDirectoryService();

        System.out.println("\n===== Add contacts =====");
        dir.addContact("Alice", "+1-555-0101");
        dir.addContact("Bob", "+1-555-0102");
        dir.addContact("Charlie", "+1-555-0103");
        dir.addContact("Alex", "+1-555-0104");
        dir.addContact("Albert", "+1-555-0105");
        dir.addContact("Bobby", "+1-555-0106");

        System.out.println("\n===== Lookup =====");
        System.out.println("  Alice: " + dir.lookup("Alice"));
        System.out.println("  Unknown: " + dir.lookup("Unknown"));

        System.out.println("\n===== Search prefix 'al' =====");
        List<Contact> results = dir.search("al");
        results.forEach(c -> System.out.println("  " + c));

        System.out.println("\n===== Search prefix 'bob' =====");
        dir.search("bob").forEach(c -> System.out.println("  " + c));

        System.out.println("\n===== Search prefix 'ch' =====");
        dir.search("ch").forEach(c -> System.out.println("  " + c));

        System.out.println("\n===== Delete =====");
        dir.deleteContact("Albert");
        System.out.println("  After delete, search 'al':");
        dir.search("al").forEach(c -> System.out.println("    " + c));

        System.out.println("\n  Total contacts: " + dir.size());
        System.out.println("\n✅ Done.");
    }
}
