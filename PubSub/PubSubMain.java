package PubSub;

import PubSub.observer.*;
import PubSub.service.PubSubService;

/**
 * Single-Queue Publish Subscribe System — LLD (#33)
 * Observer Pattern for topic subscriptions.
 */
public class PubSubMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Pub/Sub System — LLD Demo              ║");
        System.out.println("╚════════════════════════════════════════╝");

        PubSubService pubsub = new PubSubService();

        pubsub.createTopic("orders");
        pubsub.createTopic("notifications");

        Subscriber alice = new PrintSubscriber("Alice");
        Subscriber bob = new PrintSubscriber("Bob");
        Subscriber charlie = new PrintSubscriber("Charlie");

        System.out.println("\n===== Subscribe =====");
        pubsub.subscribe("orders", alice);
        pubsub.subscribe("orders", bob);
        pubsub.subscribe("notifications", bob);
        pubsub.subscribe("notifications", charlie);

        System.out.println("\n===== Publish to 'orders' =====");
        pubsub.publish("orders", "Order #101 placed");
        pubsub.publish("orders", "Order #102 placed");

        System.out.println("\n===== Publish to 'notifications' =====");
        pubsub.publish("notifications", "System maintenance at 2AM");

        System.out.println("\n===== Unsubscribe Bob from orders =====");
        pubsub.unsubscribe("orders", bob);
        pubsub.publish("orders", "Order #103 placed");

        pubsub.printStatus();
        System.out.println("\n✅ Done.");
    }
}
