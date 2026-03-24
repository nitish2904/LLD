package TicTacToe;

import TicTacToe.model.*;
import TicTacToe.service.GameService;

/**
 * Tic-Tac-Toe — LLD (#10348)
 * O(1) win detection using row/col/diagonal sum tracking.
 */
public class TicTacToeMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Tic-Tac-Toe — LLD Demo                ║");
        System.out.println("╚════════════════════════════════════════╝");

        // ===== Game 1: X wins =====
        System.out.println("\n===== Game 1: X wins (row) =====");
        Player p1 = new Player("Alice", Piece.X);
        Player p2 = new Player("Bob", Piece.O);
        GameService game1 = new GameService(3, p1, p2);

        game1.makeMove(0, 0); // X
        game1.makeMove(1, 0); // O
        game1.makeMove(0, 1); // X
        game1.makeMove(1, 1); // O
        game1.makeMove(0, 2); // X wins row 0

        // ===== Game 2: O wins =====
        System.out.println("\n===== Game 2: O wins (diagonal) =====");
        GameService game2 = new GameService(3, p1, p2);
        game2.makeMove(0, 1); // X
        game2.makeMove(0, 0); // O
        game2.makeMove(0, 2); // X
        game2.makeMove(1, 1); // O
        game2.makeMove(1, 0); // X
        game2.makeMove(2, 2); // O wins diagonal

        // ===== Game 3: Draw =====
        System.out.println("\n===== Game 3: Draw =====");
        GameService game3 = new GameService(3, p1, p2);
        game3.makeMove(0, 0); // X
        game3.makeMove(0, 1); // O
        game3.makeMove(0, 2); // X
        game3.makeMove(1, 1); // O
        game3.makeMove(1, 0); // X
        game3.makeMove(2, 0); // O
        game3.makeMove(1, 2); // X
        game3.makeMove(2, 2); // O
        game3.makeMove(2, 1); // X — draw

        // ===== Game 4: Invalid moves =====
        System.out.println("\n===== Game 4: Invalid moves =====");
        GameService game4 = new GameService(3, p1, p2);
        game4.makeMove(1, 1); // X
        game4.makeMove(1, 1); // invalid — occupied!
        game4.makeMove(-1, 5); // invalid — out of bounds!

        System.out.println("\n✅ Done.");
    }
}
