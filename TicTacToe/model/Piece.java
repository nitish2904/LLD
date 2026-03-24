package TicTacToe.model;

public enum Piece {
    X, O, EMPTY;

    @Override
    public String toString() {
        return this == EMPTY ? "." : name();
    }
}
