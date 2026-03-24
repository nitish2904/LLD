package TrainPlatform.strategy;

import TrainPlatform.model.*;
import java.util.List;

/**
 * Assign train to the first available (lowest-numbered) platform.
 */
public class FirstAvailableStrategy implements PlatformAssignmentStrategy {
    @Override
    public Platform assignPlatform(List<Platform> platforms, Train train) {
        return platforms.stream().filter(p -> p.canAccommodate(train)).findFirst().orElse(null);
    }
}
