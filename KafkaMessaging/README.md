# 📨 Kafka-like Message Streaming Service — LLD

Design a Kafka-like messaging system with topics, partitions, producers, and consumers with offset tracking.

**Problem Link:** [CodeZym #72](https://codezym.com/question/72)

## Key Concepts

| Concept | Purpose | Classes |
|---------|---------|---------|
| **Topics** | Logical message channels | `KTopic` |
| **Partitions** | Ordered append-only logs within a topic | `Partition` |
| **Key-based routing** | Messages with same key go to same partition | `KTopic.getPartitionForKey()` |
| **Consumer offsets** | Track read position per partition | `Consumer.offsets` |

## 🔑 Key Concepts

- **Topics** with configurable partition count
- **Partitions** as append-only log (ordered, immutable)
- **Key-based hashing** routes messages to consistent partition
- **Consumer offsets**: each consumer tracks its own offset per partition
- **Consumer groups**: different consumers in same group can read different partitions
- **Re-read from beginning**: new consumer starts at offset 0

## 📂 Package Structure

```
KafkaMessaging/
├── model/
│   ├── Message.java    — key, value, timestamp
│   ├── Partition.java  — append-only log, read by offset
│   ├── KTopic.java     — name, partitions, key-based routing
│   └── Consumer.java   — id, group, offset tracking per partition
├── service/
│   └── KafkaService.java — createTopic, produce, consume
└── KafkaMessagingMain.java
```

## 🚀 How to Run

```bash
javac -d out $(find KafkaMessaging -name "*.java")
java -cp out KafkaMessaging.KafkaMessagingMain
```
