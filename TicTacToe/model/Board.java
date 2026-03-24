package TicTacToe.model;

/**
 * N×N board. Tracks row/col/diagonal sums for O(1) win detection.
 */
public class Board {
    private final int size;
    private final Piece[][] grid;
    private int movesCount;

    // For O(1) win check: +1 for X, -1 for O
    private final int[] rowSum;
    private final int[] colSum;
    private int diagSum;
    private int antiDiagSum;

    public Board(int size) {
        this.size = size;
        this.grid = new Piece[size][size];
        this.rowSum = new int[size];
        this.colSum = new int[size];
        this.movesCount = 0;
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                grid[r][c] = Piece.EMPTY;
    }

    public boolean placePiece(int row, int col, Piece piece) {
        if (row < 0 || row >= size || col < 0 || col >= size) return false;
        if (grid[row][col] != Piece.EMPTY) return false;

        grid[row][col] = piece;
        movesCount++;

        int val = (piece == Piece.X) ? 1 : -1;
        rowSum[row] += val;
        colSum[col] += val;
        if (row == col) diagSum += val;
        if (row + col == size - 1) antiDiagSum += val;

        return true;
    }

    /**
     * Check if the last move at (row, col) resulted in a win.
     */
    public boolean checkWin(int row, int col) {
        int target = (grid[row][col] == Piece.X) ? size : -size;
        return rowSum[row] == target || colSum[col] == target ||
               diagSum == target || antiDiagSum == target;
    }

    public boolean isFull() { return movesCount == size * size; }
    public int getSize() { return size; }
    public Piece getCell(int r, int c) { return grid[r][c]; }

    public void print() {
        System.out.println();
        for (int r = 0; r < size; r++) {
            System.out.print("    ");
            for (int c = 0; c < size; c++) {
                System.out.print(grid[r][c] + (c < size - 1 ? " | " : ""));
            }
            System.out.println();
            if (r < size - 1) System.out.println("    " + "---+".repeat(size - 1) + "---");
        }
        System.out.println();
    }
}
