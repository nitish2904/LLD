package JobScheduler.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to map criteria integers to strategy implementations.
 * Easily extensible — register new strategies with registerStrategy().
 */
public class StrategyFactory {
    private final Map<Integer, MachineSelectionStrategy> strategyMap;

    public StrategyFactory() {
        this.strategyMap = new HashMap<>();
        // Register default strategies
        strategyMap.put(0, new LeastUnfinishedStrategy());
        strategyMap.put(1, new MostFinishedStrategy());
    }

    /**
     * Register a custom strategy for a given criteria code.
     */
    public void registerStrategy(int criteria, MachineSelectionStrategy strategy) {
        strategyMap.put(criteria, strategy);
    }

    /**
     * Get the strategy for the given criteria.
     * @throws IllegalArgumentException if criteria is not registered
     */
    public MachineSelectionStrategy getStrategy(int criteria) {
        MachineSelectionStrategy strategy = strategyMap.get(criteria);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown criteria: " + criteria);
        }
        return strategy;
    }
}
