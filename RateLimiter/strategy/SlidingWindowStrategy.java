package RateLimiter.strategy;

import java.util.*;

/**
 * Sliding Window: track timestamps, allow up to maxRequests in windowMs.
 */
public class SlidingWindowStrategy implements RateLimitStrategy {
    private final int maxRequests;
    private final long windowMs;
    private final Map<String, Queue<Long>> clientWindows = new HashMap<>();

    public SlidingWindowStrategy(int maxRequests, long windowMs) {
        this.maxRequests = maxRequests; this.windowMs = windowMs;
    }

    @Override
    public boolean allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        Queue<Long> window = clientWindows.computeIfAbsent(clientId, k -> new LinkedList<>());
        while (!window.isEmpty() && window.peek() <= now - windowMs) window.poll();
        if (window.size() < maxRequests) { window.offer(now); return true; }
        return false;
    }

    @Override
    public String getName() { return "SlidingWindow(" + maxRequests + "/" + windowMs + "ms)"; }
}
