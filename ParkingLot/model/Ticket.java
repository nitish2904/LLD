package ParkingLot.model;

import java.time.LocalDateTime;

public class Ticket {
    private static int counter = 0;
    private final int id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.id = ++counter;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public void setExitTime() { this.exitTime = LocalDateTime.now(); }

    public int getId() { return id; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSpot getSpot() { return spot; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }

    @Override
    public String toString() {
        return "Ticket#" + id + "{" + vehicle + " @ " + spot + "}";
    }
}
