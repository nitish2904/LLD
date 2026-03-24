package ParkingLot.strategy;

import ParkingLot.model.ParkingSpot;
import ParkingLot.model.Vehicle;
import java.util.List;

/**
 * Finds the first available spot (nearest to entrance — lowest ID).
 */
public class NearestSpotStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle) {
        return spots.stream()
                .filter(s -> s.canFit(vehicle))
                .findFirst()
                .orElse(null);
    }
}
