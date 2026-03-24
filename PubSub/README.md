# 📢 Publish-Subscribe System — LLD

Design a single-queue pub/sub system using the **Observer Pattern**.

**Problem Link:** [CodeZym #33](https://codezym.com/question/33)

## Design Patterns Used

| Pattern | Purpose | Classes |
|---------|---------|---------|
| **Observer** | Subscribers notified when topic receives messages | `Subscriber`, `PrintSubscriber`, `Topic` |

## 🔑 Key Concepts

- **Topics** as subjects; **Subscribers** as observers
- **Publish** sends message to all topic subscribers
- **Subscribe/Unsubscribe** dynamically
- **Message queue** per topic for history

## 📂 Package Structure

```
PubSub/
├── model/
│   ├── Message.java  — content + timestamp
│   └── Topic.java    — subscribers set + message queue + notify
├── observer/
│   ├── Subscriber.java       — interface: onMessage()
│   └── PrintSubscriber.java  — prints received messages
├── service/
│   └── PubSubService.java    — create topics, subscribe, publish
└── PubSubMain.java
```

## 🚀 How to Run

```bash
javac -d out $(find PubSub -name "*.java")
java -cp out PubSub.PubSubMain
```
