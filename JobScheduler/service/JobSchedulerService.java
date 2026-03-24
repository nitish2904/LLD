package JobScheduler.service;

import JobScheduler.model.Job;
import JobScheduler.model.Machine;
import JobScheduler.repository.JobRepository;
import JobScheduler.repository.MachineRepository;
import JobScheduler.strategy.MachineSelectionStrategy;
import JobScheduler.strategy.StrategyFactory;

import java.util.List;

/**
 * Main orchestrator for the Job Scheduler system.
 * Implements the 3 required methods:
 *   1. addMachine
 *   2. assignMachineToJob
 *   3. jobCompleted
 */
public class JobSchedulerService {
    private final MachineRepository machineRepository;
    private final JobRepository jobRepository;
    private final StrategyFactory strategyFactory;

    public JobSchedulerService() {
        this.machineRepository = new MachineRepository();
        this.jobRepository = new JobRepository();
        this.strategyFactory = new StrategyFactory();
    }

    /**
     * Register a machine with given capabilities.
     * machineId is always unique and non-blank.
     * capabilities is a list of unique, non-blank tokens (case-insensitive).
     */
    public void addMachine(String machineId, String[] capabilities) {
        Machine machine = new Machine(machineId, capabilities);
        machineRepository.addMachine(machine);
        System.out.println("  Added: " + machine);
    }

    /**
     * Assign the best machine to a job based on the given criteria.
     *
     * @param jobId                unique job identifier
     * @param capabilitiesRequired capabilities the machine must have
     * @param criteria             0 = least unfinished, 1 = most finished
     * @return assigned machineId, or "" if no compatible machine
     */
    public String assignMachineToJob(String jobId, String[] capabilitiesRequired, int criteria) {
        // 1. Find all capable machines
        List<Machine> candidates = machineRepository.findCapableMachines(capabilitiesRequired);

        if (candidates.isEmpty()) {
            System.out.println("  No compatible machine for job '" + jobId + "'");
            return "";
        }

        // 2. Use strategy to pick the best one
        MachineSelectionStrategy strategy = strategyFactory.getStrategy(criteria);
        Machine selected = strategy.select(candidates);

        if (selected == null) {
            return "";
        }

        // 3. Create job and assign
        Job job = new Job(jobId, capabilitiesRequired);
        job.setAssignedMachineId(selected.getMachineId());
        jobRepository.addJob(job);

        // 4. Update machine counters
        selected.incrementUnfinished();

        System.out.println("  Assigned job '" + jobId + "' → machine '" + selected.getMachineId()
                + "' (criteria=" + criteria + ", unfinished=" + selected.getUnfinishedJobs()
                + ", finished=" + selected.getFinishedJobs() + ")");

        return selected.getMachineId();
    }

    /**
     * Mark a previously assigned job as completed.
     * Updates the machine's unfinished/finished counters.
     */
    public void jobCompleted(String jobId) {
        Job job = jobRepository.getJob(jobId);
        if (job == null) {
            System.out.println("  Job '" + jobId + "' not found!");
            return;
        }
        if (job.isCompleted()) {
            System.out.println("  Job '" + jobId + "' already completed!");
            return;
        }

        job.setCompleted(true);

        Machine machine = machineRepository.getMachine(job.getAssignedMachineId());
        if (machine != null) {
            machine.decrementUnfinished();
            machine.incrementFinished();
        }

        System.out.println("  Completed job '" + jobId + "' on machine '"
                + job.getAssignedMachineId() + "' → " + machine);
    }

    public StrategyFactory getStrategyFactory() {
        return strategyFactory;
    }
}
