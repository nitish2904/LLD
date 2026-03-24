package TrainPlatform.service;

import TrainPlatform.model.*;
import TrainPlatform.strategy.PlatformAssignmentStrategy;
import java.util.*;

public class TrainPlatformService {
    private final List<Platform> platforms = new ArrayList<>();
    private final Map<String, Platform> trainPlatformMap = new HashMap<>();
    private PlatformAssignmentStrategy strategy;

    public TrainPlatformService(int numPlatforms, PlatformAssignmentStrategy strategy) {
        this.strategy = strategy;
        for (int i = 1; i <= numPlatforms; i++) platforms.add(new Platform(i));
    }

    public boolean scheduleTrain(Train train) {
        Platform platform = strategy.assignPlatform(platforms, train);
        if (platform == null) {
            System.out.println("  No platform available for " + train);
            return false;
        }
        platform.assignTrain(train);
        trainPlatformMap.put(train.getId(), platform);
        System.out.println("  " + train + " → " + platform);
        return true;
    }

    public void removeTrain(String trainId) {
        Platform p = trainPlatformMap.remove(trainId);
        if (p != null) { p.removeTrain(trainId); System.out.println("  " + trainId + " departed from " + p); }
    }

    public void setStrategy(PlatformAssignmentStrategy strategy) {
        this.strategy = strategy;
        System.out.println("  Strategy: " + strategy.getClass().getSimpleName());
    }

    public int getMinPlatformsRequired(List<Train> trains) {
        // Greedy interval partitioning
        List<int[]> events = new ArrayList<>();
        for (Train t : trains) { events.add(new int[]{t.getArrival().toSecondOfDay(), 1}); events.add(new int[]{t.getDeparture().toSecondOfDay(), -1}); }
        events.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int max = 0, cur = 0;
        for (int[] e : events) { cur += e[1]; max = Math.max(max, cur); }
        return max;
    }

    public void printStatus() {
        System.out.println("\n  Platform Status:");
        for (Platform p : platforms) {
            System.out.println("    " + p + ": " + p.getScheduledTrains());
        }
    }
}
