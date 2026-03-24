# 🚆 Train Platform Management — LLD

Design a train platform assignment system with conflict detection using the **Strategy Pattern**.

**Problem Link:** [CodeZym #32](https://codezym.com/question/32)

## Design Patterns Used

| Pattern | Purpose | Classes |
|---------|---------|---------|
| **Strategy** | Pluggable platform assignment algorithm | `PlatformAssignmentStrategy`, `FirstAvailableStrategy`, `LeastLoadedPlatformStrategy` |

## 🔑 Key Concepts

- **Trains** with arrival/departure times; overlap detection
- **Platforms** with schedule conflict checking
- **Min platforms** calculation using greedy interval partitioning
- **Strategy**: FirstAvailable (lowest number) or LeastLoaded (balance)

## 📂 Package Structure

```
TrainPlatform/
├── model/
│   ├── Train.java     — id, name, arrival, departure, overlapsWith()
│   └── Platform.java  — number, scheduled trains, canAccommodate()
├── strategy/
│   ├── PlatformAssignmentStrategy.java   — interface
│   ├── FirstAvailableStrategy.java       — lowest number first
│   └── LeastLoadedPlatformStrategy.java  — fewest trains
├── service/
│   └── TrainPlatformService.java — schedule, depart, min platforms
└── TrainPlatformMain.java
```

## 🚀 How to Run

```bash
javac -d out $(find TrainPlatform -name "*.java")
java -cp out TrainPlatform.TrainPlatformMain
```
