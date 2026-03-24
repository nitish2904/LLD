package MovieTicketBooking.model;

public class Seat {
    private final int row;
    private final int col;
    private final SeatType type;
    private boolean booked;

    public Seat(int row, int col, SeatType type) {
        this.row = row; this.col = col; this.type = type; this.booked = false;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public SeatType getType() { return type; }
    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }
    public String getSeatId() { return (char)('A' + row) + "" + (col + 1); }

    @Override
    public String toString() { return getSeatId() + "(" + type + (booked ? ",BOOKED" : "") + ")"; }
}
