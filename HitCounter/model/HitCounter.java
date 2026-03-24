package HitCounter.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Hit Counter — tracks hits in last N seconds using sliding window.
 * Uses a Queue to maintain timestamps within the window.
 * Time complexity: O(1) amortized for hit(), O(k) for getHits() where k = expired entries.
 */
public class HitCounter {
    private final int windowSizeSeconds;
    private final Queue<Long> hits; // timestamps in milliseconds

    public HitCounter(int windowSizeSeconds) {
        this.windowSizeSeconds = windowSizeSeconds;
        this.hits = new LinkedList<>();
    }

    /**
     * Record a hit at the given timestamp (epoch millis).
     */
    public void hit(long timestampMs) {
        hits.offer(timestampMs);
        evictExpired(timestampMs);
    }

    /**
     * Get the number of hits in the last windowSizeSeconds from the given timestamp.
     */
    public int getHits(long timestampMs) {
        evictExpired(timestampMs);
        return hits.size();
    }

    private void evictExpired(long currentMs) {
        long windowStart = currentMs - (windowSizeSeconds * 1000L);
        while (!hits.isEmpty() && hits.peek() <= windowStart) {
            hits.poll();
        }
    }

    public int getWindowSizeSeconds() { return windowSizeSeconds; }
}
