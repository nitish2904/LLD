package CarRental.service;

import CarRental.model.*;
import CarRental.strategy.CarSelectionStrategy;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CarRentalService {
    private final List<Car> fleet = new ArrayList<>();
    private final Map<Integer, Reservation> reservations = new HashMap<>();
    private CarSelectionStrategy strategy;

    public CarRentalService(CarSelectionStrategy strategy) { this.strategy = strategy; }

    public void addCar(Car car) { fleet.add(car); }

    public Reservation rent(String customer, CarType preferred, LocalDate start, LocalDate end) {
        List<Car> available = fleet.stream().filter(Car::isAvailable).collect(Collectors.toList());
        Car selected = strategy.selectCar(available, preferred);
        if (selected == null) { System.out.println("  No " + preferred + " available for " + customer); return null; }
        selected.setAvailable(false);
        Reservation res = new Reservation(selected, customer, start, end);
        reservations.put(res.getId(), res);
        System.out.println("  Rented: " + res);
        return res;
    }

    public void returnCar(int reservationId) {
        Reservation res = reservations.get(reservationId);
        if (res == null || !res.isActive()) { System.out.println("  Reservation not found"); return; }
        res.getCar().setAvailable(true);
        res.cancel();
        System.out.println("  Returned: " + res.getCar() + " | Bill: $" + res.getTotalCost());
    }

    public void setStrategy(CarSelectionStrategy strategy) {
        this.strategy = strategy;
        System.out.println("  Strategy: " + strategy.getClass().getSimpleName());
    }

    public void printFleet() {
        System.out.println("\n  Fleet:");
        fleet.forEach(c -> System.out.println("    " + c));
    }
}
