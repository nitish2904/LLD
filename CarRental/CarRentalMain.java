package CarRental;

import CarRental.model.*;
import CarRental.service.CarRentalService;
import CarRental.strategy.*;
import java.time.LocalDate;

public class CarRentalMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Car Rental System — LLD Demo           ║");
        System.out.println("╚════════════════════════════════════════╝");

        CarRentalService service = new CarRentalService(new CheapestCarStrategy());
        service.addCar(new Car("C1", "Swift", CarType.HATCHBACK));
        service.addCar(new Car("C2", "City", CarType.SEDAN));
        service.addCar(new Car("C3", "Creta", CarType.SUV));
        service.addCar(new Car("C4", "i20", CarType.HATCHBACK));
        service.addCar(new Car("C5", "BMW 3", CarType.LUXURY));

        LocalDate today = LocalDate.of(2026, 3, 25);

        System.out.println("\n===== Rent cars (CheapestFirst) =====");
        Reservation r1 = service.rent("Alice", CarType.SEDAN, today, today.plusDays(3));
        Reservation r2 = service.rent("Bob", CarType.SUV, today, today.plusDays(5));
        Reservation r3 = service.rent("Charlie", CarType.HATCHBACK, today, today.plusDays(2));
        service.printFleet();

        System.out.println("\n===== Return a car =====");
        service.returnCar(r1.getId());
        service.printFleet();

        System.out.println("\n===== Switch to ExactType strategy =====");
        service.setStrategy(new ExactTypeStrategy());
        service.rent("Diana", CarType.LUXURY, today, today.plusDays(7));
        service.rent("Eve", CarType.LUXURY, today, today.plusDays(2)); // no more luxury

        System.out.println("\n✅ Done.");
    }
}
