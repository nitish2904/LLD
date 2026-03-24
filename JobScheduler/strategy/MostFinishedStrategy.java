package JobScheduler.strategy;

import JobScheduler.model.Machine;

import java.util.List;

/**
 * Criteria 1: Select the machine with the MOST number of finished jobs.
 * Tie-breaking: lexicographically smallest machineId.
 */
public class MostFinishedStrategy implements MachineSelectionStrategy {

    @Override
    public Machine select(List<Machine> candidates) {
        if (candidates == null || candidates.isEmpty()) {
            return null;
        }

        Machine best = null;
        for (Machine m : candidates) {
            if (best == null) {
                best = m;
            } else if (m.getFinishedJobs() > best.getFinishedJobs()) {
                best = m;
            } else if (m.getFinishedJobs() == best.getFinishedJobs()
                    && m.getMachineId().compareTo(best.getMachineId()) < 0) {
                best = m;
            }
        }
        return best;
    }
}
