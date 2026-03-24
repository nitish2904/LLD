package RateLimiter.strategy;

/**
 * Strategy Pattern: pluggable rate limiting algorithm.
 */
public interface RateLimitStrategy {
    boolean allowRequest(String clientId);
    String getName();
}
