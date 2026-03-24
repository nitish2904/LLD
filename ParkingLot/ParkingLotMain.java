package ParkingLot;

import ParkingLot.model.*;
import ParkingLot.service.ParkingLotService;
import ParkingLot.strategy.*;

/**
 * Parking Lot — LLD (#7)
 * Patterns: STRATEGY (spot allocation), SRP, OCP
 */
public class ParkingLotMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Parking Lot — LLD Demo                 ║");
        System.out.println("╚════════════════════════════════════════╝");

        ParkingLotService lot = new ParkingLotService("Downtown Parking", new NearestSpotStrategy());
        lot.addSpots(1, VehicleType.BIKE, 3);
        lot.addSpots(1, VehicleType.CAR, 3);
        lot.addSpots(2, VehicleType.TRUCK, 2);

        System.out.println("\n===== Park vehicles (Nearest Strategy) =====");
        lot.parkVehicle(new Vehicle("KA-01-1234", VehicleType.CAR));
        lot.parkVehicle(new Vehicle("KA-02-5678", VehicleType.BIKE));
        lot.parkVehicle(new Vehicle("KA-03-9999", VehicleType.TRUCK));
        lot.parkVehicle(new Vehicle("KA-04-1111", VehicleType.CAR));
        lot.printStatus();

        System.out.println("\n===== Unpark =====");
        lot.unparkVehicle("KA-01-1234");
        lot.printStatus();

        System.out.println("\n===== Switch to ExactFit Strategy =====");
        lot.setStrategy(new ExactFitStrategy());
        lot.parkVehicle(new Vehicle("KA-05-2222", VehicleType.BIKE));
        lot.parkVehicle(new Vehicle("KA-06-3333", VehicleType.CAR));
        lot.printStatus();

        System.out.println("\n===== Fill up — no space =====");
        lot.parkVehicle(new Vehicle("KA-07-4444", VehicleType.TRUCK));
        lot.parkVehicle(new Vehicle("KA-08-5555", VehicleType.TRUCK)); // no spot

        System.out.println("\n===== Duplicate park =====");
        lot.parkVehicle(new Vehicle("KA-05-2222", VehicleType.BIKE));

        System.out.println("\n✅ Done.");
    }
}
