package CarRental.model;

public class Car {
    private final String id;
    private final String model;
    private final CarType type;
    private boolean available;

    public Car(String id, String model, CarType type) {
        this.id = id; this.model = model; this.type = type; this.available = true;
    }

    public String getId() { return id; }
    public String getModel() { return model; }
    public CarType getType() { return type; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() { return id + "(" + model + ", " + type + ", " + (available ? "FREE" : "RENTED") + ")"; }
}
