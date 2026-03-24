package ElevatorManagement.model;

/**
 * A request from a person: entry floor → destination floor.
 */
public class Request {
    private final int entryFloor;
    private final int destinationFloor;
    private boolean pickedUp;
    private boolean completed;

    public Request(int entryFloor, int destinationFloor) {
        this.entryFloor = entryFloor;
        this.destinationFloor = destinationFloor;
        this.pickedUp = false;
        this.completed = false;
    }

    public int getEntryFloor() { return entryFloor; }
    public int getDestinationFloor() { return destinationFloor; }
    public boolean isPickedUp() { return pickedUp; }
    public void setPickedUp(boolean pickedUp) { this.pickedUp = pickedUp; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public Direction getDirection() {
        return destinationFloor > entryFloor ? Direction.UP : Direction.DOWN;
    }

    @Override
    public String toString() {
        return "Req(" + entryFloor + "→" + destinationFloor + 
               (pickedUp ? ",onboard" : ",waiting") + 
               (completed ? ",done" : "") + ")";
    }
}
