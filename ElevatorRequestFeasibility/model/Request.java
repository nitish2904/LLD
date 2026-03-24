package ElevatorRequestFeasibility.model;

/**
 * Represents a passenger request: entry floor → destination floor.
 */
public class Request {
    private final int entryFloor;
    private final int destinationFloor;
    private int stopsUsed; // how many stops this passenger has experienced so far

    public Request(int entryFloor, int destinationFloor) {
        this.entryFloor = entryFloor;
        this.destinationFloor = destinationFloor;
        this.stopsUsed = 0;
    }

    public int getEntryFloor() { return entryFloor; }
    public int getDestinationFloor() { return destinationFloor; }
    public int getStopsUsed() { return stopsUsed; }
    public void incrementStops() { stopsUsed++; }

    public boolean isPickedUp(int currentFloor) {
        return currentFloor == entryFloor;
    }

    public boolean isDroppedOff(int currentFloor) {
        return currentFloor == destinationFloor;
    }

    @Override
    public String toString() {
        return "Request{" + entryFloor + "→" + destinationFloor + ", stops=" + stopsUsed + "}";
    }
}
