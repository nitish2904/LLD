package JobScheduler.repository;

import JobScheduler.model.Job;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory storage for jobs.
 */
public class JobRepository {
    private final Map<String, Job> jobs;

    public JobRepository() {
        this.jobs = new HashMap<>();
    }

    public void addJob(Job job) {
        jobs.put(job.getJobId(), job);
    }

    public Job getJob(String jobId) {
        return jobs.get(jobId);
    }
}
