package KafkaMessaging.model;

public class Message {
    private final String key;
    private final String value;
    private final long timestamp;

    public Message(String key, String value) {
        this.key = key; this.value = value; this.timestamp = System.currentTimeMillis();
    }

    public String getKey() { return key; }
    public String getValue() { return value; }
    public long getTimestamp() { return timestamp; }

    @Override
    public String toString() { return "{" + key + ": " + value + "}"; }
}
