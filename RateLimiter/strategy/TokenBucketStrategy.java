package RateLimiter.strategy;

import java.util.*;

/**
 * Token Bucket: tokens refill at a fixed rate; each request consumes one token.
 */
public class TokenBucketStrategy implements RateLimitStrategy {
    private final int maxTokens;
    private final double refillRatePerMs;
    private final Map<String, double[]> clientBuckets = new HashMap<>(); // [tokens, lastRefillTime]

    public TokenBucketStrategy(int maxTokens, double refillPerSecond) {
        this.maxTokens = maxTokens;
        this.refillRatePerMs = refillPerSecond / 1000.0;
    }

    @Override
    public boolean allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        double[] bucket = clientBuckets.computeIfAbsent(clientId, k -> new double[]{maxTokens, now});

        double elapsed = now - bucket[1];
        bucket[0] = Math.min(maxTokens, bucket[0] + elapsed * refillRatePerMs);
        bucket[1] = now;

        if (bucket[0] >= 1) { bucket[0] -= 1; return true; }
        return false;
    }

    @Override
    public String getName() { return "TokenBucket(max=" + maxTokens + ")"; }
}
