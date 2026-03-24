package ElevatorRequestFeasibility.service;

import ElevatorRequestFeasibility.model.*;

/**
 * Main service: manages the elevator and handles requests.
 */
public class ElevatorService {
    private final Elevator elevator;
    private final FeasibilityChecker checker;

    public ElevatorService(int floorsCount, int liftCapacity, int maxStops) {
        this.elevator = new Elevator(floorsCount, liftCapacity, maxStops);
        this.checker = new FeasibilityChecker();
    }

    /**
     * Check if a new request (entryFloor → destFloor) is feasible.
     * Returns true if the lift can serve this request without violating
     * maxStops for any existing or new passenger, and without exceeding capacity.
     */
    public boolean isRequestFeasible(int entryFloor, int destFloor) {
        boolean feasible = checker.isFeasible(elevator, entryFloor, destFloor);
        System.out.println("  isRequestFeasible(" + entryFloor + "→" + destFloor + ") = " + feasible
                + " | " + elevator);
        return feasible;
    }

    /**
     * Accept a request (after checking feasibility).
     * Adds entry and destination to scheduled stops.
     */
    public boolean acceptRequest(int entryFloor, int destFloor) {
        if (!isRequestFeasible(entryFloor, destFloor)) {
            System.out.println("  ✗ Request REJECTED: " + entryFloor + "→" + destFloor);
            return false;
        }
        Request req = new Request(entryFloor, destFloor);
        elevator.getPending().add(req);
        // Don't add entryFloor if it equals current floor (will pick up immediately)
        if (entryFloor != elevator.getCurrentFloor()) {
            elevator.getScheduledStops().add(entryFloor);
        }
        elevator.getScheduledStops().add(destFloor);
        System.out.println("  ✓ Request ACCEPTED: " + entryFloor + "→" + destFloor + " | " + elevator);
        return true;
    }

    /**
     * Simulate the lift moving to the next stop.
     */
    public void moveToNextStop() {
        if (elevator.getScheduledStops().isEmpty()) {
            System.out.println("  No stops scheduled. Lift is idle.");
            return;
        }

        // Determine next stop
        Integer nextStop;
        if (elevator.getDirection() == Direction.UP || elevator.getDirection() == Direction.IDLE) {
            nextStop = elevator.getScheduledStops().higher(elevator.getCurrentFloor());
            if (nextStop == null) {
                nextStop = elevator.getScheduledStops().lower(elevator.getCurrentFloor());
                if (nextStop != null) elevator.setDirection(Direction.DOWN);
            } else {
                elevator.setDirection(Direction.UP);
            }
        } else {
            nextStop = elevator.getScheduledStops().lower(elevator.getCurrentFloor());
            if (nextStop == null) {
                nextStop = elevator.getScheduledStops().higher(elevator.getCurrentFloor());
                if (nextStop != null) elevator.setDirection(Direction.UP);
            }
        }

        if (nextStop == null) {
            if (elevator.getScheduledStops().contains(elevator.getCurrentFloor())) {
                nextStop = elevator.getCurrentFloor();
            } else {
                elevator.setDirection(Direction.IDLE);
                return;
            }
        }

        final int stopFloor = nextStop;
        elevator.setCurrentFloor(stopFloor);
        elevator.getScheduledStops().remove(stopFloor);

        // Increment stops for all onboard passengers
        for (Request r : elevator.getOnboard()) {
            r.incrementStops();
        }

        // Pick up passengers at this floor
        var toPickUp = elevator.getPending().stream()
                .filter(r -> r.getEntryFloor() == stopFloor)
                .toList();
        for (Request r : toPickUp) {
            elevator.getPending().remove(r);
            elevator.getOnboard().add(r);
        }

        // Drop off passengers at this floor
        var toDropOff = elevator.getOnboard().stream()
                .filter(r -> r.getDestinationFloor() == stopFloor)
                .toList();
        for (Request r : toDropOff) {
            elevator.getOnboard().remove(r);
        }

        if (elevator.isIdle()) {
            elevator.setDirection(Direction.IDLE);
        }

        System.out.println("  Moved to floor " + nextStop + " | " + elevator
                + " | picked=" + toPickUp.size() + " dropped=" + toDropOff.size());
    }

    /**
     * Pick up any pending passengers waiting at the current floor.
     */
    private void pickupAtCurrentFloor() {
        int floor = elevator.getCurrentFloor();
        var toPickUp = elevator.getPending().stream()
                .filter(r -> r.getEntryFloor() == floor)
                .toList();
        for (Request r : toPickUp) {
            elevator.getPending().remove(r);
            elevator.getOnboard().add(r);
        }
        // Drop off passengers at this floor
        var toDropOff = elevator.getOnboard().stream()
                .filter(r -> r.getDestinationFloor() == floor)
                .toList();
        for (Request r : toDropOff) {
            elevator.getOnboard().remove(r);
        }
        if (!toPickUp.isEmpty() || !toDropOff.isEmpty()) {
            System.out.println("  At floor " + floor + ": picked=" + toPickUp.size()
                    + " dropped=" + toDropOff.size() + " | " + elevator);
        }
    }

    /**
     * Process all stops until the elevator is idle.
     */
    public void processAll() {
        System.out.println("\n--- Processing all stops ---");
        pickupAtCurrentFloor(); // handle any waiting at start floor
        int tick = 0;
        while (!elevator.getScheduledStops().isEmpty()) {
            tick++;
            System.out.println("[Tick " + tick + "]");
            moveToNextStop();
            if (tick > 50) { System.out.println("  Safety break!"); break; }
        }
        // Final check: anyone still onboard has no destination stop (edge case)
        if (!elevator.getOnboard().isEmpty()) {
            System.out.println("  Warning: " + elevator.getOnboard().size() + " passengers still onboard!");
        }
        elevator.setDirection(Direction.IDLE);
        System.out.println("--- All done --- | " + elevator);
    }

    public Elevator getElevator() { return elevator; }
}
