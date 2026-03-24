package ElevatorManagement.service;

import ElevatorManagement.model.*;

/**
 * Service layer for the single elevator system.
 */
public class ElevatorService {
    private final Elevator elevator;

    public ElevatorService(int floorsCount, int capacity) {
        this.elevator = new Elevator(0, floorsCount, capacity);
    }

    public void addRequest(int entryFloor, int destFloor) {
        System.out.println("\n→ New request: " + entryFloor + "→" + destFloor);
        if (entryFloor < 0 || entryFloor >= elevator.getFloorsCount() ||
            destFloor < 0 || destFloor >= elevator.getFloorsCount() ||
            entryFloor == destFloor) {
            System.out.println("  ✗ Invalid request!");
            return;
        }
        Request req = new Request(entryFloor, destFloor);
        elevator.addRequest(req);
    }

    public void step() {
        elevator.move();
    }

    public void processAll() {
        System.out.println("\n--- Processing all requests ---");
        int tick = 0;
        while (elevator.hasStops()) {
            tick++;
            System.out.println("\n[Tick " + tick + "] " + elevator);
            elevator.move();
            if (tick > 50) { System.out.println("Safety break!"); break; }
        }
        System.out.println("\n--- Complete --- " + elevator);
    }

    public void printStatus() {
        System.out.println("\n" + elevator);
    }

    public Elevator getElevator() { return elevator; }
}
