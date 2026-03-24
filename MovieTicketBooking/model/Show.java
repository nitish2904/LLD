package MovieTicketBooking.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Show {
    private final String id;
    private final String movieName;
    private final String screen;
    private final LocalDateTime showTime;
    private final Seat[][] seats;
    private final int rows;
    private final int cols;

    public Show(String id, String movieName, String screen, LocalDateTime showTime, int rows, int cols) {
        this.id = id; this.movieName = movieName; this.screen = screen;
        this.showTime = showTime; this.rows = rows; this.cols = cols;
        this.seats = new Seat[rows][cols];
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                seats[r][c] = new Seat(r, c, r < 2 ? SeatType.VIP : (r < 4 ? SeatType.PREMIUM : SeatType.REGULAR));
    }

    public String getId() { return id; }
    public String getMovieName() { return movieName; }
    public Seat getSeat(int row, int col) { return (row >= 0 && row < rows && col >= 0 && col < cols) ? seats[row][col] : null; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public Seat[][] getSeats() { return seats; }

    public List<Seat> getAvailableSeats() {
        List<Seat> avail = new ArrayList<>();
        for (Seat[] row : seats) for (Seat s : row) if (!s.isBooked()) avail.add(s);
        return avail;
    }

    public void printLayout() {
        System.out.println("    " + movieName + " @ " + screen + " [" + showTime.format(DateTimeFormatter.ofPattern("dd-MMM HH:mm")) + "]");
        System.out.print("    ");
        for (int c = 0; c < cols; c++) System.out.printf("%4d", c + 1);
        System.out.println();
        for (int r = 0; r < rows; r++) {
            System.out.print("  " + (char)('A' + r) + " ");
            for (int c = 0; c < cols; c++) System.out.print(seats[r][c].isBooked() ? " [X]" : " [ ]");
            System.out.println("  " + seats[r][0].getType());
        }
    }

    @Override
    public String toString() { return id + "(" + movieName + " @ " + screen + ")"; }
}
