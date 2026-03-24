package PubSub.observer;

import PubSub.model.Message;

public class PrintSubscriber implements Subscriber {
    private final String name;

    public PrintSubscriber(String name) { this.name = name; }

    @Override
    public void onMessage(String topic, Message message) {
        System.out.println("    [" + name + "] received on '" + topic + "': " + message);
    }

    @Override
    public String getName() { return name; }
}
