package MovieTicketBooking;

import MovieTicketBooking.model.Show;
import MovieTicketBooking.service.BookingService;
import java.time.LocalDateTime;
import java.util.Arrays;

public class MovieTicketBookingMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Movie Ticket Booking — LLD Demo        ║");
        System.out.println("╚════════════════════════════════════════╝");

        BookingService service = new BookingService();
        Show show = new Show("S1", "Inception", "Screen-1",
                LocalDateTime.of(2026, 3, 25, 18, 30), 6, 8);
        service.addShow(show);

        System.out.println("\n===== Initial layout =====");
        service.printShowLayout("S1");

        System.out.println("\n===== Book VIP seats (A1, A2) for Alice =====");
        var b1 = service.bookSeats("S1", "Alice", Arrays.asList(new int[]{0,0}, new int[]{0,1}));

        System.out.println("\n===== Book PREMIUM seats (C3, C4, C5) for Bob =====");
        var b2 = service.bookSeats("S1", "Bob", Arrays.asList(new int[]{2,2}, new int[]{2,3}, new int[]{2,4}));

        System.out.println("\n===== Book REGULAR (E1, E2, E3, E4) for Charlie =====");
        service.bookSeats("S1", "Charlie", Arrays.asList(new int[]{4,0}, new int[]{4,1}, new int[]{4,2}, new int[]{4,3}));

        System.out.println("\n===== Try booking already booked seat =====");
        service.bookSeats("S1", "Diana", Arrays.asList(new int[]{0,0})); // A1 taken

        System.out.println("\n===== Layout after bookings =====");
        service.printShowLayout("S1");
        System.out.println("  Available: " + service.getAvailableCount("S1"));

        System.out.println("\n===== Cancel Bob's booking =====");
        service.cancelBooking(b2.getId());
        service.printShowLayout("S1");

        System.out.println("\n✅ Done.");
    }
}
