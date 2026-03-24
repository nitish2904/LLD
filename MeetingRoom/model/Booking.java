package MeetingRoom.model;

public class Booking {
    private static int counter = 0;
    private final int id;
    private final Room room;
    private final TimeSlot slot;
    private final String organizer;
    private final int attendees;

    public Booking(Room room, TimeSlot slot, String organizer, int attendees) {
        this.id = ++counter;
        this.room = room; this.slot = slot; this.organizer = organizer; this.attendees = attendees;
    }

    public int getId() { return id; }
    public Room getRoom() { return room; }
    public TimeSlot getSlot() { return slot; }
    public String getOrganizer() { return organizer; }
    public int getAttendees() { return attendees; }

    @Override
    public String toString() {
        return "Booking#" + id + "{" + room.getName() + ", " + slot + ", by=" + organizer + ", attendees=" + attendees + "}";
    }
}
