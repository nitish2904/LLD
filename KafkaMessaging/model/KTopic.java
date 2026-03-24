package KafkaMessaging.model;

import java.util.*;

public class KTopic {
    private final String name;
    private final List<Partition> partitions;

    public KTopic(String name, int numPartitions) {
        this.name = name;
        this.partitions = new ArrayList<>();
        for (int i = 0; i < numPartitions; i++) partitions.add(new Partition(i));
    }

    public String getName() { return name; }
    public List<Partition> getPartitions() { return partitions; }
    public int getNumPartitions() { return partitions.size(); }

    public Partition getPartitionForKey(String key) {
        int idx = (key != null) ? Math.abs(key.hashCode() % partitions.size()) : 0;
        return partitions.get(idx);
    }
}
