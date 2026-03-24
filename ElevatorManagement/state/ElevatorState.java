package ElevatorManagement.state;

import ElevatorManagement.model.Elevator;
import ElevatorManagement.model.Request;

/**
 * State Pattern interface — each state defines how the elevator
 * handles new requests and moves.
 */
public interface ElevatorState {
    void handleRequest(Elevator elevator, Request request);
    void move(Elevator elevator);
    String getStateName();
}
