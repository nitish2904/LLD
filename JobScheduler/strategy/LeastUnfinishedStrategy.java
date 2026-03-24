package JobScheduler.strategy;

import JobScheduler.model.Machine;

import java.util.List;

/**
 * Criteria 0: Select the machine with the LEAST number of unfinished jobs.
 * Tie-breaking: lexicographically smallest machineId.
 */
public class LeastUnfinishedStrategy implements MachineSelectionStrategy {

    @Override
    public Machine select(List<Machine> candidates) {
        if (candidates == null || candidates.isEmpty()) {
            return null;
        }

        Machine best = null;
        for (Machine m : candidates) {
            if (best == null) {
                best = m;
            } else if (m.getUnfinishedJobs() < best.getUnfinishedJobs()) {
                best = m;
            } else if (m.getUnfinishedJobs() == best.getUnfinishedJobs()
                    && m.getMachineId().compareTo(best.getMachineId()) < 0) {
                best = m;
            }
        }
        return best;
    }
}
