package MeetingRoom.strategy;

import MeetingRoom.model.Room;
import MeetingRoom.model.TimeSlot;
import java.util.List;

/**
 * Strategy Pattern: pluggable room selection when multiple rooms are available.
 */
public interface RoomSelectionStrategy {
    Room selectRoom(List<Room> availableRooms, int attendees);
}
