package ParkingLot.service;

import ParkingLot.model.*;
import ParkingLot.strategy.ParkingStrategy;

import java.util.*;

/**
 * Parking Lot service — manages spots, tickets, and uses Strategy for spot allocation.
 */
public class ParkingLotService {
    private final String name;
    private final List<ParkingSpot> spots;
    private final Map<String, Ticket> activeTickets; // licensePlate → Ticket
    private ParkingStrategy strategy;

    public ParkingLotService(String name, ParkingStrategy strategy) {
        this.name = name;
        this.spots = new ArrayList<>();
        this.activeTickets = new HashMap<>();
        this.strategy = strategy;
    }

    public void addSpot(ParkingSpot spot) { spots.add(spot); }

    public void addSpots(int floor, VehicleType type, int count) {
        int startId = spots.size();
        for (int i = 0; i < count; i++) {
            spots.add(new ParkingSpot(startId + i, floor, type));
        }
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        if (activeTickets.containsKey(vehicle.getLicensePlate())) {
            System.out.println("  " + vehicle + " is already parked!");
            return null;
        }

        ParkingSpot spot = strategy.findSpot(spots, vehicle);
        if (spot == null) {
            System.out.println("  No spot available for " + vehicle);
            return null;
        }

        spot.park(vehicle);
        Ticket ticket = new Ticket(vehicle, spot);
        activeTickets.put(vehicle.getLicensePlate(), ticket);
        System.out.println("  Parked: " + ticket);
        return ticket;
    }

    public Ticket unparkVehicle(String licensePlate) {
        Ticket ticket = activeTickets.remove(licensePlate);
        if (ticket == null) {
            System.out.println("  Vehicle " + licensePlate + " not found!");
            return null;
        }
        ticket.getSpot().unpark();
        ticket.setExitTime();
        System.out.println("  Unparked: " + ticket);
        return ticket;
    }

    public void setStrategy(ParkingStrategy strategy) {
        this.strategy = strategy;
        System.out.println("  Strategy changed to: " + strategy.getClass().getSimpleName());
    }

    public void printStatus() {
        long available = spots.stream().filter(ParkingSpot::isAvailable).count();
        System.out.println("\n  " + name + ": " + available + "/" + spots.size() + " spots available");
        for (ParkingSpot s : spots) {
            if (!s.isAvailable()) System.out.println("    " + s);
        }
    }
}
