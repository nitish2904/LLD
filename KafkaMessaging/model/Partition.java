package KafkaMessaging.model;

import java.util.*;

public class Partition {
    private final int id;
    private final List<Message> log = new ArrayList<>();

    public Partition(int id) { this.id = id; }

    public int getId() { return id; }
    public void append(Message msg) { log.add(msg); }
    public Message read(int offset) { return (offset >= 0 && offset < log.size()) ? log.get(offset) : null; }
    public int size() { return log.size(); }
    public List<Message> readFrom(int offset) { return (offset < log.size()) ? log.subList(offset, log.size()) : Collections.emptyList(); }
}
