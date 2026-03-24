package MeetingRoom.strategy;

import MeetingRoom.model.Room;
import java.util.Comparator;
import java.util.List;

/**
 * Pick the largest available room (maximize comfort).
 */
public class LargestRoomStrategy implements RoomSelectionStrategy {
    @Override
    public Room selectRoom(List<Room> availableRooms, int attendees) {
        return availableRooms.stream()
                .filter(r -> r.getCapacity() >= attendees)
                .max(Comparator.comparingInt(Room::getCapacity))
                .orElse(null);
    }
}
