package PubSub.service;

import PubSub.model.*;
import PubSub.observer.Subscriber;
import java.util.*;

public class PubSubService {
    private final Map<String, Topic> topics = new HashMap<>();

    public Topic createTopic(String name) {
        Topic topic = new Topic(name);
        topics.put(name, topic);
        System.out.println("  Topic '" + name + "' created");
        return topic;
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        Topic topic = topics.get(topicName);
        if (topic == null) { System.out.println("  Topic '" + topicName + "' not found"); return; }
        topic.subscribe(subscriber);
    }

    public void unsubscribe(String topicName, Subscriber subscriber) {
        Topic topic = topics.get(topicName);
        if (topic != null) topic.unsubscribe(subscriber);
    }

    public void publish(String topicName, String content) {
        Topic topic = topics.get(topicName);
        if (topic == null) { System.out.println("  Topic '" + topicName + "' not found"); return; }
        topic.publish(new Message(content));
    }

    public void printStatus() {
        System.out.println("\n  Topics:");
        topics.forEach((name, topic) ->
            System.out.println("    " + name + " — " + topic.getSubscriberCount() + " subs, " + topic.getMessageCount() + " msgs"));
    }
}
