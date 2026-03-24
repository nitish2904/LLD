package ParkingLot.strategy;

import ParkingLot.model.ParkingSpot;
import ParkingLot.model.Vehicle;
import java.util.List;

/**
 * Finds the smallest available spot that fits the vehicle (no waste).
 */
public class ExactFitStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle) {
        ParkingSpot best = null;
        for (ParkingSpot spot : spots) {
            if (spot.canFit(vehicle)) {
                if (best == null || spot.getSpotType().ordinal() < best.getSpotType().ordinal()) {
                    best = spot;
                }
            }
        }
        return best;
    }
}
