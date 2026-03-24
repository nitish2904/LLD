package ElevatorRequestFeasibility;

import ElevatorRequestFeasibility.service.ElevatorService;

/**
 * =====================================================================
 *  Elevator Request Feasibility (Single Lift) — LLD (CodeZym #23)
 * =====================================================================
 *
 *  Problem: Build a controller for a single lift that checks if a new
 *  request is feasible given:
 *    - liftCapacity: max people at a time
 *    - maxStops: each person must reach destination within maxStops
 *    - floorsCount: building has floors 0..floorsCount-1
 *
 *  Link: https://codezym.com/question/23
 * =====================================================================
 */
public class ElevatorFeasibilityMain {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  Elevator Request Feasibility — LLD Demo     ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // Building: 10 floors, capacity 3, maxStops 4
        ElevatorService service = new ElevatorService(10, 3, 4);

        System.out.println("\n===== Scenario 1: Basic Feasibility =====");
        // Lift at floor 0, request 0→5 — should be feasible (1 stop)
        service.acceptRequest(0, 5);
        // Request 2→7 — feasible (entry 2, dest 7, route has stops: 0→2→5→7 = 3 stops for person2)
        service.acceptRequest(2, 7);
        // Request 1→9 — might exceed maxStops for some passengers
        service.acceptRequest(1, 9);

        service.processAll();

        System.out.println("\n===== Scenario 2: Capacity Check =====");
        ElevatorService service2 = new ElevatorService(10, 2, 5);
        // Accept 2 passengers (at capacity)
        service2.acceptRequest(0, 5);
        service2.acceptRequest(1, 6);
        // Third should fail (capacity = 2)
        boolean result = service2.acceptRequest(2, 8);
        System.out.println("  Third request feasible? " + result);

        service2.processAll();

        System.out.println("\n===== Scenario 3: maxStops Constraint =====");
        ElevatorService service3 = new ElevatorService(20, 5, 2);
        // With maxStops=2, a request 0→10 needs at most 2 stops
        // If we have stops at 0, 5, 10 that's 2 stops for person → feasible
        service3.acceptRequest(0, 10);
        // Request 3→15: would add stops 3,15 → route 0,3,10,15 → 3 stops to reach 15 from 3. Exceeds maxStops=2
        boolean result3 = service3.acceptRequest(3, 15);
        System.out.println("  Request 3→15 feasible? " + result3);

        service3.processAll();

        System.out.println("\n===== Scenario 4: Invalid Requests =====");
        ElevatorService service4 = new ElevatorService(10, 3, 4);
        // Same floor
        System.out.println("  Same floor: " + service4.isRequestFeasible(5, 5));
        // Out of bounds
        System.out.println("  Out of bounds: " + service4.isRequestFeasible(-1, 5));
        System.out.println("  Out of bounds: " + service4.isRequestFeasible(0, 10));

        System.out.println("\n✅ All scenarios completed.");
    }
}
