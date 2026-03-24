package ElevatorRequestFeasibility.model;

import java.util.*;

/**
 * Single lift with capacity and maxStops constraints.
 * Tracks current passengers (picked up) and pending passengers (waiting to be picked).
 */
public class Elevator {
    private final int floorsCount;
    private final int liftCapacity;
    private final int maxStops;
    private int currentFloor;
    private Direction direction;

    // Passengers currently inside the lift (picked up, not yet dropped)
    private final List<Request> onboard;
    // Passengers waiting to be picked up
    private final List<Request> pending;
    // All scheduled stop floors (sorted set for optimal route)
    private final TreeSet<Integer> scheduledStops;

    public Elevator(int floorsCount, int liftCapacity, int maxStops) {
        this.floorsCount = floorsCount;
        this.liftCapacity = liftCapacity;
        this.maxStops = maxStops;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.onboard = new ArrayList<>();
        this.pending = new ArrayList<>();
        this.scheduledStops = new TreeSet<>();
    }

    public int getFloorsCount() { return floorsCount; }
    public int getLiftCapacity() { return liftCapacity; }
    public int getMaxStops() { return maxStops; }
    public int getCurrentFloor() { return currentFloor; }
    public void setCurrentFloor(int f) { this.currentFloor = f; }
    public Direction getDirection() { return direction; }
    public void setDirection(Direction d) { this.direction = d; }
    public List<Request> getOnboard() { return onboard; }
    public List<Request> getPending() { return pending; }
    public TreeSet<Integer> getScheduledStops() { return scheduledStops; }

    public int getOccupancy() { return onboard.size(); }
    public boolean isFull() { return onboard.size() >= liftCapacity; }
    public boolean isIdle() { return onboard.isEmpty() && pending.isEmpty(); }

    @Override
    public String toString() {
        return "Elevator{floor=" + currentFloor + ", dir=" + direction +
                ", onboard=" + onboard.size() + "/" + liftCapacity +
                ", pending=" + pending.size() + ", stops=" + scheduledStops + "}";
    }
}
