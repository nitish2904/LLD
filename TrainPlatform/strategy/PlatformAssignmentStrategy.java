package TrainPlatform.strategy;

import TrainPlatform.model.*;
import java.util.List;

/**
 * Strategy Pattern: pluggable platform assignment.
 */
public interface PlatformAssignmentStrategy {
    Platform assignPlatform(List<Platform> platforms, Train train);
}
