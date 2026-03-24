package KafkaMessaging;

import KafkaMessaging.model.Consumer;
import KafkaMessaging.service.KafkaService;

public class KafkaMessagingMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Kafka-like Messaging — LLD Demo        ║");
        System.out.println("╚════════════════════════════════════════╝");

        KafkaService kafka = new KafkaService();
        kafka.createTopic("orders", 3);
        kafka.createTopic("logs", 2);

        System.out.println("\n===== Produce messages =====");
        kafka.produce("orders", "user1", "Order 101 created");
        kafka.produce("orders", "user2", "Order 102 created");
        kafka.produce("orders", "user1", "Order 101 updated");
        kafka.produce("orders", "user3", "Order 103 created");
        kafka.produce("logs", "svc-A", "Request received");
        kafka.produce("logs", "svc-B", "Payment processed");

        kafka.printTopicInfo("orders");
        kafka.printTopicInfo("logs");

        System.out.println("\n===== Consume messages =====");
        Consumer c1 = new Consumer("consumer-1", "order-group");
        Consumer c2 = new Consumer("consumer-2", "order-group");

        // c1 reads partition 0, c2 reads partition 1
        for (int p = 0; p < 3; p++) {
            kafka.consume(c1, "orders", p);
        }

        System.out.println("\n===== Produce more, then consume again =====");
        kafka.produce("orders", "user1", "Order 101 shipped");
        kafka.produce("orders", "user2", "Order 102 cancelled");
        for (int p = 0; p < 3; p++) {
            kafka.consume(c1, "orders", p); // only gets new msgs
        }

        System.out.println("\n===== Different consumer reads from beginning =====");
        for (int p = 0; p < 3; p++) {
            kafka.consume(c2, "orders", p); // gets ALL msgs
        }

        System.out.println("\n✅ Done.");
    }
}
