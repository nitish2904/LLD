package ElevatorManagement.state;

import ElevatorManagement.model.*;

/**
 * Elevator is moving downward. Stops at floors in downStops set (reverse order).
 */
public class MovingDownState implements ElevatorState {

    @Override
    public void handleRequest(Elevator elevator, Request request) {
        elevator.getRequests().add(request);
        int entry = request.getEntryFloor();
        int dest = request.getDestinationFloor();

        if (entry <= elevator.getCurrentFloor()) {
            elevator.addStop(entry, Direction.DOWN);
        } else {
            elevator.addStop(entry, Direction.UP);
        }

        if (dest < entry) {
            elevator.addStop(dest, Direction.DOWN);
        } else {
            elevator.addStop(dest, Direction.UP);
        }

        System.out.println("  [MovingDown] Queued " + request);
    }

    @Override
    public void move(Elevator elevator) {
        if (elevator.getDownStops().isEmpty()) {
            if (!elevator.getUpStops().isEmpty()) {
                elevator.setDirection(Direction.UP);
                elevator.setState(new MovingUpState());
                System.out.println("  [MovingDown→Up] Switching direction at floor " + elevator.getCurrentFloor());
            } else {
                elevator.setDirection(Direction.IDLE);
                elevator.setState(new IdleState());
                System.out.println("  [MovingDown→Idle] No more stops at floor " + elevator.getCurrentFloor());
            }
            return;
        }

        int nextFloor = elevator.getDownStops().first(); // highest remaining (reverse order)
        elevator.setCurrentFloor(nextFloor);
        elevator.getDownStops().remove(nextFloor);

        elevator.setState(new DoorOpenState());
        System.out.println("  [MovingDown] Arrived floor " + nextFloor);
        ((DoorOpenState) elevator.getState()).processFloor(elevator);
    }

    @Override
    public String getStateName() { return "MOVING_DOWN"; }
}
