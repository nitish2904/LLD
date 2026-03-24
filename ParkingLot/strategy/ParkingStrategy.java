package ParkingLot.strategy;

import ParkingLot.model.ParkingSpot;
import ParkingLot.model.Vehicle;
import java.util.List;

/**
 * Strategy Pattern: pluggable spot selection algorithm.
 * Open/Closed Principle: add new strategies without modifying existing code.
 */
public interface ParkingStrategy {
    ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle);
}
