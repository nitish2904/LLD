package MovieTicketBooking.service;

import MovieTicketBooking.model.*;
import java.util.*;

public class BookingService {
    private final Map<String, Show> shows = new HashMap<>();
    private final Map<Integer, Booking> bookings = new HashMap<>();

    public void addShow(Show show) { shows.put(show.getId(), show); }

    public Booking bookSeats(String showId, String customer, List<int[]> seatPositions) {
        Show show = shows.get(showId);
        if (show == null) { System.out.println("  Show not found"); return null; }

        List<Seat> seatsToBook = new ArrayList<>();
        for (int[] pos : seatPositions) {
            Seat seat = show.getSeat(pos[0], pos[1]);
            if (seat == null) { System.out.println("  Invalid seat " + pos[0] + "," + pos[1]); return null; }
            if (seat.isBooked()) { System.out.println("  Seat " + seat.getSeatId() + " already booked"); return null; }
            seatsToBook.add(seat);
        }

        seatsToBook.forEach(s -> s.setBooked(true));
        Booking booking = new Booking(show, customer, seatsToBook);
        bookings.put(booking.getId(), booking);
        System.out.println("  " + booking);
        return booking;
    }

    public boolean cancelBooking(int bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null || !booking.isActive()) { System.out.println("  Booking not found"); return false; }
        booking.getSeats().forEach(s -> s.setBooked(false));
        booking.cancel();
        System.out.println("  Cancelled booking #" + bookingId);
        return true;
    }

    public void printShowLayout(String showId) {
        Show show = shows.get(showId);
        if (show != null) show.printLayout();
    }

    public int getAvailableCount(String showId) {
        Show show = shows.get(showId);
        return show != null ? show.getAvailableSeats().size() : 0;
    }
}
