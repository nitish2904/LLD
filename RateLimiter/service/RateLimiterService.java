package RateLimiter.service;

import RateLimiter.strategy.RateLimitStrategy;

public class RateLimiterService {
    private RateLimitStrategy strategy;

    public RateLimiterService(RateLimitStrategy strategy) { this.strategy = strategy; }

    public boolean handleRequest(String clientId) {
        boolean allowed = strategy.allowRequest(clientId);
        System.out.println("  [" + clientId + "] " + (allowed ? "✓ ALLOWED" : "✗ RATE LIMITED"));
        return allowed;
    }

    public void setStrategy(RateLimitStrategy strategy) {
        this.strategy = strategy;
        System.out.println("  Strategy: " + strategy.getName());
    }
}
