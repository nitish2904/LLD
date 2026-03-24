package MeetingRoom;

import MeetingRoom.model.*;
import MeetingRoom.service.MeetingRoomService;
import MeetingRoom.strategy.*;

import java.time.LocalDateTime;

/**
 * Meeting Room Reservation System — LLD (#29)
 * Strategy Pattern for room selection.
 */
public class MeetingRoomMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Meeting Room Reservation — LLD Demo    ║");
        System.out.println("╚════════════════════════════════════════╝");

        MeetingRoomService service = new MeetingRoomService(new SmallestFitStrategy());
        service.addRoom(new Room("R1", "Huddle", 4));
        service.addRoom(new Room("R2", "Board Room", 12));
        service.addRoom(new Room("R3", "Conference", 20));

        LocalDateTime today = LocalDateTime.of(2026, 3, 25, 9, 0);

        System.out.println("\n===== Book meetings (SmallestFit) =====");
        TimeSlot morning = new TimeSlot(today, today.plusHours(1));
        service.book("Alice", 3, morning);  // Huddle (4)
        service.book("Bob", 10, morning);   // Board Room (12)
        service.book("Charlie", 15, morning); // Conference (20)

        service.printStatus(morning);

        System.out.println("\n===== Overlap — no room left =====");
        service.book("Dave", 2, morning); // all booked!

        System.out.println("\n===== Book non-overlapping slot =====");
        TimeSlot afternoon = new TimeSlot(today.plusHours(3), today.plusHours(4));
        service.book("Eve", 5, afternoon); // Board Room at 12:00-13:00

        System.out.println("\n===== Cancel and rebook =====");
        service.cancel(1); // cancel Huddle booking
        service.book("Frank", 2, morning); // now Huddle is free

        System.out.println("\n===== Switch to LargestRoom strategy =====");
        service.setStrategy(new LargestRoomStrategy());
        TimeSlot late = new TimeSlot(today.plusHours(5), today.plusHours(6));
        service.book("Grace", 2, late); // picks Conference (largest)

        System.out.println("\n✅ Done.");
    }
}
