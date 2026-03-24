package MeetingRoom.strategy;

import MeetingRoom.model.Room;
import java.util.Comparator;
import java.util.List;

/**
 * Pick the smallest room that fits the attendees (minimize waste).
 */
public class SmallestFitStrategy implements RoomSelectionStrategy {
    @Override
    public Room selectRoom(List<Room> availableRooms, int attendees) {
        return availableRooms.stream()
                .filter(r -> r.getCapacity() >= attendees)
                .min(Comparator.comparingInt(Room::getCapacity))
                .orElse(null);
    }
}
