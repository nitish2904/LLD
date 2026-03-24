package ElevatorManagement.state;

import ElevatorManagement.model.*;

/**
 * Elevator is idle, doors closed, waiting for requests.
 */
public class IdleState implements ElevatorState {

    @Override
    public void handleRequest(Elevator elevator, Request request) {
        elevator.getRequests().add(request);

        int entry = request.getEntryFloor();
        int dest = request.getDestinationFloor();

        if (entry == elevator.getCurrentFloor()) {
            // Already here — pick up immediately
            request.setPickedUp(true);
            if (dest > elevator.getCurrentFloor()) {
                elevator.addStop(dest, Direction.UP);
                elevator.setDirection(Direction.UP);
                elevator.setState(new MovingUpState());
            } else {
                elevator.addStop(dest, Direction.DOWN);
                elevator.setDirection(Direction.DOWN);
                elevator.setState(new MovingDownState());
            }
        } else if (entry > elevator.getCurrentFloor()) {
            elevator.addStop(entry, Direction.UP);
            if (dest > entry) {
                elevator.addStop(dest, Direction.UP);
            } else {
                elevator.addStop(dest, Direction.DOWN);
            }
            elevator.setDirection(Direction.UP);
            elevator.setState(new MovingUpState());
        } else {
            elevator.addStop(entry, Direction.DOWN);
            if (dest < entry) {
                elevator.addStop(dest, Direction.DOWN);
            } else {
                elevator.addStop(dest, Direction.UP);
            }
            elevator.setDirection(Direction.DOWN);
            elevator.setState(new MovingDownState());
        }

        System.out.println("  [Idle] Accepted " + request + " → now " + elevator.getState().getStateName());
    }

    @Override
    public void move(Elevator elevator) {
        // Nothing to do when idle
    }

    @Override
    public String getStateName() { return "IDLE"; }
}
