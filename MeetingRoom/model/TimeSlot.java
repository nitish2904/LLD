package MeetingRoom.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeSlot {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        if (!end.isAfter(start)) throw new IllegalArgumentException("End must be after start");
        this.start = start; this.end = end;
    }

    public boolean overlapsWith(TimeSlot other) {
        return start.isBefore(other.end) && other.start.isBefore(end);
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        return start.format(f) + "-" + end.format(f);
    }
}
