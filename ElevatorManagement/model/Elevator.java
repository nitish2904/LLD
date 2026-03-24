package ElevatorManagement.model;

import ElevatorManagement.state.ElevatorState;
import ElevatorManagement.state.IdleState;

import java.util.*;

/**
 * Single elevator — context for the State Pattern.
 * Delegates all behavior to the current ElevatorState.
 */
public class Elevator {
    private final int id;
    private final int floorsCount;
    private final int capacity;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;

    private final List<Request> requests;       // all active requests
    private final TreeSet<Integer> upStops;     // stops going UP
    private final TreeSet<Integer> downStops;   // stops going DOWN

    public Elevator(int id, int floorsCount, int capacity) {
        this.id = id;
        this.floorsCount = floorsCount;
        this.capacity = capacity;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.state = new IdleState();
        this.requests = new ArrayList<>();
        this.upStops = new TreeSet<>();
        this.downStops = new TreeSet<>(Collections.reverseOrder());
    }

    // --- State pattern delegation ---

    public void addRequest(Request request) {
        state.handleRequest(this, request);
    }

    public void move() {
        state.move(this);
    }

    // --- Stop management ---

    public void addStop(int floor, Direction dir) {
        if (dir == Direction.UP) {
            upStops.add(floor);
        } else {
            downStops.add(floor);
        }
    }

    // --- Getters / Setters ---

    public int getId() { return id; }
    public int getFloorsCount() { return floorsCount; }
    public int getCapacity() { return capacity; }
    public int getCurrentFloor() { return currentFloor; }
    public void setCurrentFloor(int f) { this.currentFloor = f; }
    public Direction getDirection() { return direction; }
    public void setDirection(Direction d) { this.direction = d; }
    public ElevatorState getState() { return state; }
    public void setState(ElevatorState s) { this.state = s; }
    public List<Request> getRequests() { return requests; }
    public TreeSet<Integer> getUpStops() { return upStops; }
    public TreeSet<Integer> getDownStops() { return downStops; }

    public int getOnboardCount() {
        return (int) requests.stream().filter(r -> r.isPickedUp() && !r.isCompleted()).count();
    }

    public boolean hasStops() {
        return !upStops.isEmpty() || !downStops.isEmpty();
    }

    @Override
    public String toString() {
        return "Elevator[" + id + "]{floor=" + currentFloor + ", dir=" + direction +
                ", state=" + state.getStateName() +
                ", onboard=" + getOnboardCount() + "/" + capacity +
                ", upStops=" + upStops + ", downStops=" + downStops + "}";
    }
}
