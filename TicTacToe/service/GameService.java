package TicTacToe.service;

import TicTacToe.model.*;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Manages the Tic-Tac-Toe game flow.
 */
public class GameService {
    private final Board board;
    private final Deque<Player> players;
    private boolean gameOver;
    private Player winner;

    public GameService(int boardSize, Player player1, Player player2) {
        this.board = new Board(boardSize);
        this.players = new ArrayDeque<>();
        players.offer(player1);
        players.offer(player2);
        this.gameOver = false;
    }

    /**
     * Make a move. Returns true if move was valid.
     */
    public boolean makeMove(int row, int col) {
        if (gameOver) {
            System.out.println("  Game is already over!");
            return false;
        }

        Player current = players.peek();
        if (!board.placePiece(row, col, current.getPiece())) {
            System.out.println("  Invalid move! Cell occupied or out of bounds.");
            return false;
        }

        System.out.println("  " + current + " → (" + row + "," + col + ")");

        if (board.checkWin(row, col)) {
            gameOver = true;
            winner = current;
            board.print();
            System.out.println("  🏆 " + current.getName() + " wins!");
            return true;
        }

        if (board.isFull()) {
            gameOver = true;
            board.print();
            System.out.println("  🤝 It's a draw!");
            return true;
        }

        // Rotate players
        players.offer(players.poll());
        return true;
    }

    public void printBoard() { board.print(); }
    public boolean isGameOver() { return gameOver; }
    public Player getWinner() { return winner; }
}
