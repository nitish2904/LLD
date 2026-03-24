package PubSub.observer;

import PubSub.model.Message;

/**
 * Observer Pattern: subscriber receives messages from topics.
 */
public interface Subscriber {
    void onMessage(String topic, Message message);
    String getName();
}
