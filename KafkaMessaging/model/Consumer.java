package KafkaMessaging.model;

import java.util.*;

public class Consumer {
    private final String id;
    private final String groupId;
    private final Map<String, Map<Integer, Integer>> offsets = new HashMap<>(); // topic → (partition → offset)

    public Consumer(String id, String groupId) { this.id = id; this.groupId = groupId; }

    public String getId() { return id; }
    public String getGroupId() { return groupId; }

    public List<Message> poll(KTopic topic, int partitionId) {
        String topicName = topic.getName();
        offsets.computeIfAbsent(topicName, k -> new HashMap<>());
        int offset = offsets.get(topicName).getOrDefault(partitionId, 0);
        Partition partition = topic.getPartitions().get(partitionId);
        List<Message> messages = partition.readFrom(offset);
        offsets.get(topicName).put(partitionId, offset + messages.size());
        return messages;
    }

    public int getOffset(String topicName, int partitionId) {
        return offsets.getOrDefault(topicName, Collections.emptyMap()).getOrDefault(partitionId, 0);
    }

    @Override
    public String toString() { return id + "(group=" + groupId + ")"; }
}
