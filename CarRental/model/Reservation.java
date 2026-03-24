package CarRental.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private static int counter = 0;
    private final int id;
    private final Car car;
    private final String customer;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalCost;
    private boolean active;

    public Reservation(Car car, String customer, LocalDate startDate, LocalDate endDate) {
        this.id = ++counter; this.car = car; this.customer = customer;
        this.startDate = startDate; this.endDate = endDate;
        long days = Math.max(1, ChronoUnit.DAYS.between(startDate, endDate));
        this.totalCost = days * car.getType().getDailyRate();
        this.active = true;
    }

    public int getId() { return id; }
    public Car getCar() { return car; }
    public String getCustomer() { return customer; }
    public double getTotalCost() { return totalCost; }
    public boolean isActive() { return active; }
    public void cancel() { this.active = false; }

    @Override
    public String toString() {
        return "Reservation#" + id + "{" + car.getModel() + ", " + customer + ", " + startDate + " to " + endDate + ", $" + totalCost + "}";
    }
}
