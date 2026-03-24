package ElevatorRequestFeasibility.service;

import ElevatorRequestFeasibility.model.*;

import java.util.*;

/**
 * Core logic: determines if a new request is feasible given the
 * maxStops constraint for ALL passengers (existing + new).
 *
 * Algorithm:
 *   1. Collect ALL entry and destination floors (existing + new)
 *   2. Build the optimal stop sequence from current floor
 *   3. For each passenger, count stops between their pickup and dropoff
 *   4. If ALL passengers ≤ maxStops and capacity never exceeded → feasible
 */
public class FeasibilityChecker {

    public boolean isFeasible(Elevator elevator, int entryFloor, int destFloor) {
        // Basic validation
        if (entryFloor < 0 || entryFloor >= elevator.getFloorsCount() ||
            destFloor < 0 || destFloor >= elevator.getFloorsCount() ||
            entryFloor == destFloor) {
            return false;
        }

        // Gather all requests: pending + onboard + new
        List<Request> allRequests = new ArrayList<>();
        allRequests.addAll(elevator.getPending());
        allRequests.addAll(elevator.getOnboard());
        Request newReq = new Request(entryFloor, destFloor);
        allRequests.add(newReq);

        // Collect ALL stop floors from all requests
        TreeSet<Integer> allStops = new TreeSet<>();
        for (Request req : allRequests) {
            allStops.add(req.getEntryFloor());
            allStops.add(req.getDestinationFloor());
        }

        // Build the route from current floor
        List<Integer> route = buildRoute(elevator.getCurrentFloor(), allStops);

        // For each passenger, count how many stops occur between pickup and dropoff
        for (Request req : allRequests) {
            int pickup, dropoff;

            if (elevator.getOnboard().contains(req)) {
                // Already onboard: pickup is at start of route (index 0 or before)
                pickup = 0;
                dropoff = route.indexOf(req.getDestinationFloor());
                if (dropoff == -1) return false;
                int stopsForPassenger = dropoff + req.getStopsUsed();
                if (stopsForPassenger > elevator.getMaxStops()) return false;
            } else {
                pickup = route.indexOf(req.getEntryFloor());
                dropoff = route.indexOf(req.getDestinationFloor());
                if (pickup == -1 || dropoff == -1) return false;
                if (pickup > dropoff) return false; // can't pick up after drop off
                int stopsForPassenger = dropoff - pickup;
                if (stopsForPassenger > elevator.getMaxStops()) return false;
            }
        }

        // Check capacity: simulate ride and count onboard at each stop
        int[] boardCount = new int[route.size()];
        for (Request req : allRequests) {
            int pickup, dropoff;
            if (elevator.getOnboard().contains(req)) {
                pickup = 0;
            } else {
                pickup = route.indexOf(req.getEntryFloor());
            }
            dropoff = route.indexOf(req.getDestinationFloor());
            for (int i = pickup; i < dropoff; i++) {
                boardCount[i]++;
            }
        }
        for (int count : boardCount) {
            if (count > elevator.getLiftCapacity()) return false;
        }

        return true;
    }

    /**
     * Build an optimal route visiting all stops from the current floor.
     * Uses sweep: go UP first, then DOWN. Current floor included if it's a stop.
     */
    private List<Integer> buildRoute(int currentFloor, TreeSet<Integer> stops) {
        List<Integer> route = new ArrayList<>();
        if (stops.isEmpty()) return route;

        // Current floor is a stop
        if (stops.contains(currentFloor)) {
            route.add(currentFloor);
        }

        NavigableSet<Integer> above = stops.tailSet(currentFloor, false);
        NavigableSet<Integer> below = stops.headSet(currentFloor, false);

        // Go UP first, then DOWN
        route.addAll(above);
        route.addAll(below.descendingSet());

        return route;
    }
}
