package JobScheduler.strategy;

import JobScheduler.model.Machine;

import java.util.List;

/**
 * Strategy interface for selecting the best machine from a list of candidates.
 * New selection criteria can be added by implementing this interface.
 */
public interface MachineSelectionStrategy {

    /**
     * Select the best machine from the given candidates.
     * All candidates are guaranteed to have the required capabilities.
     *
     * @param candidates list of machines that satisfy capability requirements
     * @return the selected machine, or null if candidates is empty
     */
    Machine select(List<Machine> candidates);
}
