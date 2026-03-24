# 🏆 Leaderboard for Fantasy Teams — LLD

Design a leaderboard with real-time score updates and top-K retrieval.

**Problem Link:** [CodeZym #31](https://codezym.com/question/31)

## Data Structures

| Concept | Purpose | Classes |
|---------|---------|---------|
| **TreeSet** | Sorted player ranking, O(log n) insert/remove | `LeaderboardService` |
| **HashMap** | O(1) player lookup by ID | `LeaderboardService` |

## 🔑 Key Concepts

- **O(log n)** score update: remove from TreeSet, update, re-insert
- **O(k)** top-k retrieval via TreeSet iteration
- **Rank query** for any player
- **Tie-breaking** by player name (alphabetical)

## 📂 Package Structure

```
Leaderboard/
├── model/
│   └── Player.java             — id, name, score
├── service/
│   └── LeaderboardService.java — TreeSet + HashMap leaderboard
└── LeaderboardMain.java
```

## 🚀 How to Run

```bash
javac -d out $(find Leaderboard -name "*.java")
java -cp out Leaderboard.LeaderboardMain
```
