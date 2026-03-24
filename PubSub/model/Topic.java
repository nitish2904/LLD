package PubSub.model;

import PubSub.observer.Subscriber;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Topic {
    private final String name;
    private final Set<Subscriber> subscribers = new LinkedHashSet<>();
    private final Queue<Message> messageQueue = new ConcurrentLinkedQueue<>();

    public Topic(String name) { this.name = name; }

    public String getName() { return name; }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println("  " + subscriber.getName() + " subscribed to '" + name + "'");
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
        System.out.println("  " + subscriber.getName() + " unsubscribed from '" + name + "'");
    }

    public void publish(Message message) {
        messageQueue.offer(message);
        System.out.println("  Published to '" + name + "': " + message);
        notifySubscribers(message);
    }

    private void notifySubscribers(Message message) {
        for (Subscriber sub : subscribers) {
            sub.onMessage(name, message);
        }
    }

    public int getSubscriberCount() { return subscribers.size(); }
    public int getMessageCount() { return messageQueue.size(); }
}
