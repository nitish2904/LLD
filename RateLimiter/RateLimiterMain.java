package RateLimiter;

import RateLimiter.service.RateLimiterService;
import RateLimiter.strategy.*;

/**
 * Rate Limiter — LLD (#34)
 * Strategy Pattern: SlidingWindow vs TokenBucket.
 */
public class RateLimiterMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Rate Limiter — LLD Demo                ║");
        System.out.println("╚════════════════════════════════════════╝");

        System.out.println("\n===== Sliding Window (3 req / 1000ms) =====");
        RateLimiterService limiter = new RateLimiterService(new SlidingWindowStrategy(3, 1000));
        limiter.handleRequest("user1");
        limiter.handleRequest("user1");
        limiter.handleRequest("user1");
        limiter.handleRequest("user1"); // rejected
        limiter.handleRequest("user2"); // different user — allowed

        System.out.println("\n  Waiting 1.1 seconds...");
        Thread.sleep(1100);
        limiter.handleRequest("user1"); // allowed (window reset)

        System.out.println("\n===== Token Bucket (max=3, refill=2/sec) =====");
        limiter.setStrategy(new TokenBucketStrategy(3, 2));
        limiter.handleRequest("user1");
        limiter.handleRequest("user1");
        limiter.handleRequest("user1");
        limiter.handleRequest("user1"); // rejected

        System.out.println("\n  Waiting 1 second (2 tokens refill)...");
        Thread.sleep(1000);
        limiter.handleRequest("user1"); // allowed
        limiter.handleRequest("user1"); // allowed
        limiter.handleRequest("user1"); // rejected

        System.out.println("\n✅ Done.");
    }
}
