package TrainPlatform.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Train {
    private final String id;
    private final String name;
    private final LocalTime arrival;
    private final LocalTime departure;

    public Train(String id, String name, LocalTime arrival, LocalTime departure) {
        this.id = id; this.name = name; this.arrival = arrival; this.departure = departure;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public LocalTime getArrival() { return arrival; }
    public LocalTime getDeparture() { return departure; }

    public boolean overlapsWith(Train other) {
        return arrival.isBefore(other.departure) && other.arrival.isBefore(departure);
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        return id + "(" + name + " " + arrival.format(f) + "-" + departure.format(f) + ")";
    }
}
