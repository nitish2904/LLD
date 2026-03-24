package MovieTicketBooking.model;

import java.util.List;
import java.util.stream.Collectors;

public class Booking {
    private static int counter = 0;
    private final int id;
    private final Show show;
    private final String customer;
    private final List<Seat> seats;
    private final double totalCost;
    private boolean active;

    public Booking(Show show, String customer, List<Seat> seats) {
        this.id = ++counter; this.show = show; this.customer = customer;
        this.seats = seats; this.active = true;
        this.totalCost = seats.stream().mapToDouble(s -> s.getType().getPrice()).sum();
    }

    public int getId() { return id; }
    public Show getShow() { return show; }
    public List<Seat> getSeats() { return seats; }
    public double getTotalCost() { return totalCost; }
    public boolean isActive() { return active; }
    public void cancel() { this.active = false; }

    @Override
    public String toString() {
        String seatIds = seats.stream().map(Seat::getSeatId).collect(Collectors.joining(","));
        return "Booking#" + id + "{" + show.getMovieName() + ", " + customer + ", seats=[" + seatIds + "], $" + totalCost + "}";
    }
}
