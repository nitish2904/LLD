package TrainPlatform.model;

import java.util.*;

public class Platform {
    private final int number;
    private final List<Train> scheduledTrains = new ArrayList<>();

    public Platform(int number) { this.number = number; }

    public int getNumber() { return number; }

    public boolean canAccommodate(Train train) {
        return scheduledTrains.stream().noneMatch(t -> t.overlapsWith(train));
    }

    public void assignTrain(Train train) { scheduledTrains.add(train); }
    public void removeTrain(String trainId) { scheduledTrains.removeIf(t -> t.getId().equals(trainId)); }
    public List<Train> getScheduledTrains() { return Collections.unmodifiableList(scheduledTrains); }

    @Override
    public String toString() { return "Platform-" + number; }
}
