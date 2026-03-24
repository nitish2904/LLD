package MeetingRoom.service;

import MeetingRoom.model.*;
import MeetingRoom.strategy.RoomSelectionStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class MeetingRoomService {
    private final List<Room> rooms = new ArrayList<>();
    private final Map<String, List<Booking>> bookings = new HashMap<>(); // roomId → bookings
    private RoomSelectionStrategy strategy;

    public MeetingRoomService(RoomSelectionStrategy strategy) { this.strategy = strategy; }

    public void addRoom(Room room) {
        rooms.add(room);
        bookings.put(room.getId(), new ArrayList<>());
    }

    public Booking book(String organizer, int attendees, TimeSlot slot) {
        List<Room> available = getAvailableRooms(slot);
        Room selected = strategy.selectRoom(available, attendees);
        if (selected == null) {
            System.out.println("  No room available for " + attendees + " attendees at " + slot);
            return null;
        }
        Booking booking = new Booking(selected, slot, organizer, attendees);
        bookings.get(selected.getId()).add(booking);
        System.out.println("  Booked: " + booking);
        return booking;
    }

    public boolean cancel(int bookingId) {
        for (var entry : bookings.entrySet()) {
            if (entry.getValue().removeIf(b -> b.getId() == bookingId)) {
                System.out.println("  Cancelled booking #" + bookingId);
                return true;
            }
        }
        System.out.println("  Booking #" + bookingId + " not found");
        return false;
    }

    public List<Room> getAvailableRooms(TimeSlot slot) {
        return rooms.stream()
                .filter(r -> bookings.get(r.getId()).stream().noneMatch(b -> b.getSlot().overlapsWith(slot)))
                .collect(Collectors.toList());
    }

    public List<Booking> getBookingsForRoom(String roomId) {
        return Collections.unmodifiableList(bookings.getOrDefault(roomId, Collections.emptyList()));
    }

    public void setStrategy(RoomSelectionStrategy strategy) {
        this.strategy = strategy;
        System.out.println("  Strategy: " + strategy.getClass().getSimpleName());
    }

    public void printStatus(TimeSlot slot) {
        System.out.println("\n  Available rooms at " + slot + ":");
        getAvailableRooms(slot).forEach(r -> System.out.println("    " + r));
    }
}
