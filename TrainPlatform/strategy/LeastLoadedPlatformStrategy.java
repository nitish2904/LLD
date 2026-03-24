package TrainPlatform.strategy;

import TrainPlatform.model.*;
import java.util.Comparator;
import java.util.List;

/**
 * Assign train to the platform with the fewest scheduled trains (load balance).
 */
public class LeastLoadedPlatformStrategy implements PlatformAssignmentStrategy {
    @Override
    public Platform assignPlatform(List<Platform> platforms, Train train) {
        return platforms.stream()
                .filter(p -> p.canAccommodate(train))
                .min(Comparator.comparingInt(p -> p.getScheduledTrains().size()))
                .orElse(null);
    }
}
