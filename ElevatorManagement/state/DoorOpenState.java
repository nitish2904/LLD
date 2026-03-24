package ElevatorManagement.state;

import ElevatorManagement.model.*;

/**
 * Doors open — pick up and drop off passengers, then resume direction.
 */
public class DoorOpenState implements ElevatorState {

    @Override
    public void handleRequest(Elevator elevator, Request request) {
        // Queue the request while doors are open
        elevator.getRequests().add(request);
        int entry = request.getEntryFloor();
        int dest = request.getDestinationFloor();

        if (entry == elevator.getCurrentFloor()) {
            request.setPickedUp(true);
        }

        if (dest > entry) {
            if (entry != elevator.getCurrentFloor()) elevator.addStop(entry, Direction.UP);
            elevator.addStop(dest, Direction.UP);
        } else {
            if (entry != elevator.getCurrentFloor()) elevator.addStop(entry, Direction.DOWN);
            elevator.addStop(dest, Direction.DOWN);
        }
    }

    /**
     * Process pickup/dropoff at the current floor.
     */
    public void processFloor(Elevator elevator) {
        int floor = elevator.getCurrentFloor();

        // Drop off passengers whose destination is this floor
        for (Request r : elevator.getRequests()) {
            if (r.isPickedUp() && !r.isCompleted() && r.getDestinationFloor() == floor) {
                r.setCompleted(true);
                System.out.println("    ↓ Dropped: " + r);
            }
        }

        // Pick up passengers whose entry is this floor
        for (Request r : elevator.getRequests()) {
            if (!r.isPickedUp() && r.getEntryFloor() == floor) {
                if (elevator.getOnboardCount() < elevator.getCapacity()) {
                    r.setPickedUp(true);
                    System.out.println("    ↑ Picked: " + r);
                } else {
                    System.out.println("    ✗ Full! Cannot pick: " + r);
                }
            }
        }

        // Close doors — resume direction
        System.out.println("    Doors closing at floor " + floor);
        if (elevator.getDirection() == Direction.UP) {
            elevator.setState(new MovingUpState());
        } else if (elevator.getDirection() == Direction.DOWN) {
            elevator.setState(new MovingDownState());
        } else {
            elevator.setState(new IdleState());
        }
    }

    @Override
    public void move(Elevator elevator) {
        processFloor(elevator);
    }

    @Override
    public String getStateName() { return "DOOR_OPEN"; }
}
