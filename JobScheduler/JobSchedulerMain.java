package JobScheduler;

import JobScheduler.service.JobSchedulerService;

/**
 * =====================================================================
 *  Job Scheduler — Low Level Design (CodeZym #22)
 * =====================================================================
 *
 *  Problem: Design a scheduler for a massively parallel distributed system.
 *    - Machines have capabilities
 *    - Jobs require capabilities; a job runs only on a machine with ALL required caps
 *    - Strategy-based machine selection (extensible)
 *
 *  Design Patterns:
 *    1. STRATEGY PATTERN — MachineSelectionStrategy interface with
 *       LeastUnfinishedStrategy (criteria=0) and MostFinishedStrategy (criteria=1).
 *       New criteria are added by implementing the interface and registering.
 *
 *    2. FACTORY PATTERN — StrategyFactory maps criteria codes → strategies.
 *
 *  Link: https://codezym.com/question/22
 * =====================================================================
 */
public class JobSchedulerMain {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   Job Scheduler — LLD Demo               ║");
        System.out.println("╚══════════════════════════════════════════╝");

        JobSchedulerService scheduler = new JobSchedulerService();

        // ============================
        // Example 1 — Multi-Capability Match + Criteria 0 (Least Unfinished) + Deterministic Tie
        // ============================
        System.out.println("\n===== SETUP: Adding Machines =====");
        scheduler.addMachine("m-10", new String[]{"image compression", "audio extraction", "video thumbnail generation"});
        scheduler.addMachine("m-2", new String[]{"image compression", "audio extraction"});

        System.out.println("\n===== EXAMPLE 1: Criteria 0 (Least Unfinished) =====");

        // assignMachineToJob("job-A", ["image compression", "audio extraction"], 0)
        // Candidates: m-2, m-10 (both are supersets)
        // Both have unfinishedCount = 0 → tie → lexicographically smaller = "m-10" (because '1' < '2')
        String result1 = scheduler.assignMachineToJob("job-A",
                new String[]{"image compression", "audio extraction"}, 0);
        System.out.println("  → Result: \"" + result1 + "\" (expected: \"m-10\")");

        // ============================
        // Example 2 — Completion Updates → Criteria 1 (Most Finished)
        // ============================
        System.out.println("\n===== EXAMPLE 2: Job Completion + Criteria 1 (Most Finished) =====");

        // jobCompleted("job-A") → m-10: unfinished 1→0, finished 0→1
        scheduler.jobCompleted("job-A");

        // assignMachineToJob("job-B", ["image compression"], 1)
        // Candidates: m-2 (finished=0), m-10 (finished=1)
        // m-10 has more finished → select m-10
        String result2 = scheduler.assignMachineToJob("job-B",
                new String[]{"image compression"}, 1);
        System.out.println("  → Result: \"" + result2 + "\" (expected: \"m-10\")");

        // ============================
        // Example 3 — No Compatible Machine
        // ============================
        System.out.println("\n===== EXAMPLE 3: No Compatible Machine =====");

        // No machine has "speech to text conversion"
        String result3 = scheduler.assignMachineToJob("job-C",
                new String[]{"speech to text conversion"}, 0);
        System.out.println("  → Result: \"" + result3 + "\" (expected: \"\")");

        // ============================
        // Example 4 — Multiple jobs, tiebreaker scenarios
        // ============================
        System.out.println("\n===== EXAMPLE 4: Multiple Jobs & Tiebreakers =====");

        scheduler.addMachine("m-5", new String[]{"pdf thumbnail creator", "plain text compression"});
        scheduler.addMachine("m-1", new String[]{"pdf thumbnail creator", "image compression"});

        // Assign job with criteria 0 (least unfinished)
        // m-5 and m-1 both have pdf thumbnail creator, both unfinished=0
        // tie → lexicographically smaller = m-1
        String result4 = scheduler.assignMachineToJob("job-D",
                new String[]{"pdf thumbnail creator"}, 0);
        System.out.println("  → Result: \"" + result4 + "\" (expected: \"m-1\")");

        // Now m-1 has unfinished=1, m-5 has unfinished=0
        // Assign another pdf job with criteria 0 → m-5 wins (fewer unfinished)
        String result5 = scheduler.assignMachineToJob("job-E",
                new String[]{"pdf thumbnail creator"}, 0);
        System.out.println("  → Result: \"" + result5 + "\" (expected: \"m-5\")");

        // Complete job-D on m-1 → m-1: unfinished=0, finished=1
        scheduler.jobCompleted("job-D");

        // Assign with criteria 1 (most finished) → m-1 has 1 finished, m-5 has 0
        String result6 = scheduler.assignMachineToJob("job-F",
                new String[]{"pdf thumbnail creator"}, 1);
        System.out.println("  → Result: \"" + result6 + "\" (expected: \"m-1\")");

        System.out.println("\n✅ All examples completed.");
    }
}
