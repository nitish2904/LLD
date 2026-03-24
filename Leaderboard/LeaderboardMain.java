package Leaderboard;

import Leaderboard.model.Player;
import Leaderboard.service.LeaderboardService;

/**
 * Leaderboard for Fantasy Teams — LLD (#31)
 * TreeSet + HashMap for O(log n) score updates, O(k) top-k.
 */
public class LeaderboardMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Leaderboard — LLD Demo                 ║");
        System.out.println("╚════════════════════════════════════════╝");

        LeaderboardService lb = new LeaderboardService();
        lb.addPlayer(new Player("p1", "Alice"));
        lb.addPlayer(new Player("p2", "Bob"));
        lb.addPlayer(new Player("p3", "Charlie"));
        lb.addPlayer(new Player("p4", "Diana"));
        lb.addPlayer(new Player("p5", "Eve"));

        System.out.println("\n===== Initial scores =====");
        lb.updateScore("p1", 120);
        lb.updateScore("p2", 85);
        lb.updateScore("p3", 200);
        lb.updateScore("p4", 150);
        lb.updateScore("p5", 95);
        lb.printTopK(5);

        System.out.println("\n===== Update scores =====");
        lb.updateScore("p2", 130); // Bob now 215
        lb.updateScore("p5", 200); // Eve now 295
        lb.printTopK(3);

        System.out.println("\n===== Get rank =====");
        System.out.println("  Alice rank: " + lb.getRank("p1"));
        System.out.println("  Eve rank: " + lb.getRank("p5"));

        System.out.println("\n✅ Done.");
    }
}
