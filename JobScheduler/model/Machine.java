package JobScheduler.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a machine in the distributed system.
 * Each machine has a unique ID, a set of capabilities,
 * and counters for unfinished and finished jobs.
 */
public class Machine {
    private final String machineId;
    private final Set<String> capabilities; // stored lowercase for case-insensitive matching
    private int unfinishedJobs;
    private int finishedJobs;

    public Machine(String machineId, String[] capabilities) {
        this.machineId = machineId;
        this.capabilities = new HashSet<>();
        for (String cap : capabilities) {
            this.capabilities.add(cap.toLowerCase());
        }
        this.unfinishedJobs = 0;
        this.finishedJobs = 0;
    }

    /**
     * Check if this machine has ALL the required capabilities.
     */
    public boolean hasCapabilities(String[] required) {
        for (String cap : required) {
            if (!capabilities.contains(cap.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public void incrementUnfinished() {
        unfinishedJobs++;
    }

    public void decrementUnfinished() {
        unfinishedJobs--;
    }

    public void incrementFinished() {
        finishedJobs++;
    }

    public String getMachineId() {
        return machineId;
    }

    public Set<String> getCapabilities() {
        return capabilities;
    }

    public int getUnfinishedJobs() {
        return unfinishedJobs;
    }

    public int getFinishedJobs() {
        return finishedJobs;
    }

    @Override
    public String toString() {
        return "Machine{id='" + machineId + "', caps=" + capabilities +
                ", unfinished=" + unfinishedJobs + ", finished=" + finishedJobs + "}";
    }
}
