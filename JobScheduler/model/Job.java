package JobScheduler.model;

/**
 * Represents a job that needs to be assigned to a machine.
 */
public class Job {
    private final String jobId;
    private final String[] capabilitiesRequired;
    private String assignedMachineId;
    private boolean completed;

    public Job(String jobId, String[] capabilitiesRequired) {
        this.jobId = jobId;
        this.capabilitiesRequired = capabilitiesRequired;
        this.assignedMachineId = null;
        this.completed = false;
    }

    public String getJobId() {
        return jobId;
    }

    public String[] getCapabilitiesRequired() {
        return capabilitiesRequired;
    }

    public String getAssignedMachineId() {
        return assignedMachineId;
    }

    public void setAssignedMachineId(String assignedMachineId) {
        this.assignedMachineId = assignedMachineId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Job{id='" + jobId + "', assigned='" + assignedMachineId +
                "', completed=" + completed + "}";
    }
}
