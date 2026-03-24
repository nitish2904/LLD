package Leaderboard.service;

import Leaderboard.model.Player;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Leaderboard using a TreeSet (sorted) + HashMap for O(log n) updates and O(k) top-k.
 */
public class LeaderboardService {
    private final Map<String, Player> players = new HashMap<>();
    private final TreeSet<Player> leaderboard = new TreeSet<>(
            Comparator.comparingInt(Player::getScore).reversed()
                    .thenComparing(Player::getName)
    );

    public void addPlayer(Player player) {
        players.put(player.getId(), player);
        leaderboard.add(player);
    }

    public void updateScore(String playerId, int points) {
        Player p = players.get(playerId);
        if (p == null) { System.out.println("  Player " + playerId + " not found"); return; }
        leaderboard.remove(p);
        p.addScore(points);
        leaderboard.add(p);
    }

    public List<Player> getTopK(int k) {
        return leaderboard.stream().limit(k).collect(Collectors.toList());
    }

    public int getRank(String playerId) {
        Player target = players.get(playerId);
        if (target == null) return -1;
        int rank = 1;
        for (Player p : leaderboard) {
            if (p.getId().equals(playerId)) return rank;
            rank++;
        }
        return -1;
    }

    public void printTopK(int k) {
        System.out.println("  Top " + k + ":");
        int rank = 1;
        for (Player p : getTopK(k)) {
            System.out.println("    #" + rank++ + " " + p);
        }
    }
}
