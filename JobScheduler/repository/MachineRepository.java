package JobScheduler.repository;

import JobScheduler.model.Machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory storage and lookup for machines.
 */
public class MachineRepository {
    private final Map<String, Machine> machines;

    public MachineRepository() {
        this.machines = new HashMap<>();
    }

    public void addMachine(Machine machine) {
        machines.put(machine.getMachineId(), machine);
    }

    public Machine getMachine(String machineId) {
        return machines.get(machineId);
    }

    /**
     * Find all machines that have ALL the required capabilities.
     */
    public List<Machine> findCapableMachines(String[] capabilitiesRequired) {
        List<Machine> result = new ArrayList<>();
        for (Machine machine : machines.values()) {
            if (machine.hasCapabilities(capabilitiesRequired)) {
                result.add(machine);
            }
        }
        return result;
    }
}
