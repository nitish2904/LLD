package ParkingLot.model;

/**
 * A single parking spot. Each spot can hold one vehicle of matching or smaller type.
 */
public class ParkingSpot {
    private final int id;
    private final int floor;
    private final VehicleType spotType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int id, int floor, VehicleType spotType) {
        this.id = id;
        this.floor = floor;
        this.spotType = spotType;
    }

    public boolean isAvailable() { return parkedVehicle == null; }

    public boolean canFit(Vehicle vehicle) {
        return isAvailable() && spotType.ordinal() >= vehicle.getType().ordinal();
    }

    public void park(Vehicle vehicle) { this.parkedVehicle = vehicle; }
    public void unpark() { this.parkedVehicle = null; }

    public int getId() { return id; }
    public int getFloor() { return floor; }
    public VehicleType getSpotType() { return spotType; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }

    @Override
    public String toString() {
        return "Spot#" + id + "(" + spotType + ",F" + floor + ")" +
               (parkedVehicle != null ? "→" + parkedVehicle : "→FREE");
    }
}
