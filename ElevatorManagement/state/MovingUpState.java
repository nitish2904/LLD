package ElevatorManagement.state;

import ElevatorManagement.model.*;

/**
 * Elevator is moving upward. Stops at floors in upStops set.
 */
public class MovingUpState implements ElevatorState {

    @Override
    public void handleRequest(Elevator elevator, Request request) {
        elevator.getRequests().add(request);
        int entry = request.getEntryFloor();
        int dest = request.getDestinationFloor();

        // Schedule entry stop
        if (entry >= elevator.getCurrentFloor()) {
            elevator.addStop(entry, Direction.UP);
        } else {
            elevator.addStop(entry, Direction.DOWN);
        }

        // Schedule destination stop
        if (dest > entry) {
            elevator.addStop(dest, Direction.UP);
        } else {
            elevator.addStop(dest, Direction.DOWN);
        }

        System.out.println("  [MovingUp] Queued " + request);
    }

    @Override
    public void move(Elevator elevator) {
        if (elevator.getUpStops().isEmpty()) {
            // Switch to down or idle
            if (!elevator.getDownStops().isEmpty()) {
                elevator.setDirection(Direction.DOWN);
                elevator.setState(new MovingDownState());
                System.out.println("  [MovingUp→Down] Switching direction at floor " + elevator.getCurrentFloor());
            } else {
                elevator.setDirection(Direction.IDLE);
                elevator.setState(new IdleState());
                System.out.println("  [MovingUp→Idle] No more stops at floor " + elevator.getCurrentFloor());
            }
            return;
        }

        int nextFloor = elevator.getUpStops().first();
        elevator.setCurrentFloor(nextFloor);
        elevator.getUpStops().remove(nextFloor);

        // Open doors state
        elevator.setState(new DoorOpenState());
        System.out.println("  [MovingUp] Arrived floor " + nextFloor);
        ((DoorOpenState) elevator.getState()).processFloor(elevator);
    }

    @Override
    public String getStateName() { return "MOVING_UP"; }
}
