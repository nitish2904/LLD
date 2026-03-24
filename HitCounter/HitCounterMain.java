package HitCounter;

import HitCounter.model.HitCounter;

/**
 * Hit Counter — LLD (#10362)
 * Sliding window approach using a Queue.
 */
public class HitCounterMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Hit Counter — LLD Demo                 ║");
        System.out.println("╚════════════════════════════════════════╝");

        HitCounter counter = new HitCounter(5); // 5-second window

        long base = System.currentTimeMillis();

        System.out.println("\n===== Recording hits =====");
        counter.hit(base);
        counter.hit(base + 1000);
        counter.hit(base + 1000);
        counter.hit(base + 2000);
        counter.hit(base + 3000);
        System.out.println("  5 hits recorded at t=0,1,1,2,3");
        System.out.println("  Hits in window at t=3s: " + counter.getHits(base + 3000));

        System.out.println("\n===== After 4 seconds =====");
        counter.hit(base + 4000);
        System.out.println("  Hits at t=4s: " + counter.getHits(base + 4000));

        System.out.println("\n===== After 6 seconds (t=0 hit expired) =====");
        System.out.println("  Hits at t=6s: " + counter.getHits(base + 6000));

        System.out.println("\n===== After 8 seconds (t=0,1,1,2 expired) =====");
        System.out.println("  Hits at t=8s: " + counter.getHits(base + 8000));

        System.out.println("\n===== After 10 seconds (all expired) =====");
        System.out.println("  Hits at t=10s: " + counter.getHits(base + 10000));

        System.out.println("\n✅ Done.");
    }
}
