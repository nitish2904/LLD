package ElevatorManagement;

import ElevatorManagement.service.ElevatorService;

/**
 * =====================================================================
 *  Elevator Management System — Single Lift LLD (CodeZym #24)
 * =====================================================================
 *
 *  Design Patterns:
 *    STATE PATTERN — Elevator delegates behavior to its current state
 *      (Idle, MovingUp, MovingDown, DoorOpen)
 *
 *  Key rules:
 *    - Single lift in a building with floorsCount floors (0..N-1)
 *    - Lift capacity: liftsCapacity people
 *    - LOOK algorithm: serve all requests in current direction, then reverse
 *
 *  Link: https://codezym.com/question/24
 * =====================================================================
 */
public class ElevatorManagementMain {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  Elevator Management System — LLD Demo       ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // Building: 10 floors, capacity 4
        ElevatorService service = new ElevatorService(10, 4);

        // ===== Scenario 1: Simple UP requests =====
        System.out.println("\n===== Scenario 1: Simple UP requests =====");
        service.addRequest(0, 5);
        service.addRequest(2, 7);
        service.addRequest(3, 9);
        service.processAll();

        // ===== Scenario 2: Mixed UP and DOWN =====
        System.out.println("\n\n===== Scenario 2: Mixed UP and DOWN =====");
        ElevatorService service2 = new ElevatorService(10, 3);
        service2.addRequest(0, 8);
        service2.addRequest(6, 2);
        service2.addRequest(4, 9);
        service2.processAll();

        // ===== Scenario 3: Capacity limit =====
        System.out.println("\n\n===== Scenario 3: Capacity limit =====");
        ElevatorService service3 = new ElevatorService(10, 2);
        service3.addRequest(0, 5);
        service3.addRequest(1, 6);
        service3.addRequest(2, 7); // should wait — capacity is 2
        service3.processAll();

        // ===== Scenario 4: DOWN first then UP =====
        System.out.println("\n\n===== Scenario 4: Starting with DOWN =====");
        ElevatorService service4 = new ElevatorService(10, 3);
        // Elevator at 0, person wants to go from 5→1 (DOWN)
        service4.addRequest(5, 1);
        service4.addRequest(3, 8);
        service4.processAll();

        System.out.println("\n✅ All scenarios completed.");
    }
}
