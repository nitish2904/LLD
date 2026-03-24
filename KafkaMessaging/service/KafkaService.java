package KafkaMessaging.service;

import KafkaMessaging.model.*;
import java.util.*;

public class KafkaService {
    private final Map<String, KTopic> topics = new HashMap<>();

    public KTopic createTopic(String name, int partitions) {
        KTopic topic = new KTopic(name, partitions);
        topics.put(name, topic);
        System.out.println("  Topic '" + name + "' created with " + partitions + " partitions");
        return topic;
    }

    public void produce(String topicName, String key, String value) {
        KTopic topic = topics.get(topicName);
        if (topic == null) { System.out.println("  Topic not found"); return; }
        Message msg = new Message(key, value);
        Partition partition = topic.getPartitionForKey(key);
        partition.append(msg);
        System.out.println("  Produced to " + topicName + "[P" + partition.getId() + "]: " + msg);
    }

    public List<Message> consume(Consumer consumer, String topicName, int partitionId) {
        KTopic topic = topics.get(topicName);
        if (topic == null) return Collections.emptyList();
        List<Message> msgs = consumer.poll(topic, partitionId);
        if (!msgs.isEmpty())
            System.out.println("  " + consumer.getId() + " consumed " + msgs.size() + " msgs from " + topicName + "[P" + partitionId + "]: " + msgs);
        return msgs;
    }

    public void printTopicInfo(String topicName) {
        KTopic topic = topics.get(topicName);
        if (topic == null) return;
        System.out.println("\n  Topic '" + topicName + "':");
        for (Partition p : topic.getPartitions())
            System.out.println("    P" + p.getId() + ": " + p.size() + " messages");
    }
}
