# 🏗️ LLD - Low Level Design Problems

Java implementations of common Low Level Design (LLD) interview problems with clean architecture, design patterns, and working demos.

## 📋 Problems (22)

| # | Problem | Design Patterns | Folder |
|---|---------|----------------|--------|
| 1 | 🔧 **Job Scheduler** | Strategy | [JobScheduler](JobScheduler/) |
| 2 | 🛗 **Elevator Request Feasibility** | — | [ElevatorRequestFeasibility](ElevatorRequestFeasibility/) |
| 3 | 🛗 **Elevator Management** | State, Strategy | [ElevatorManagement](ElevatorManagement/) |
| 4 | 📝 **Text Editor** | Flyweight | [TextEditor](TextEditor/) |
| 5 | 📊 **Spreadsheet** | Flyweight | [Spreadsheet](Spreadsheet/) |
| 6 | 📖 **Dictionary** | HashMap, Trie | [Dictionary](Dictionary/) |
| 7 | ✏️ **Text Editor Undo/Redo** | Command | [TextEditorUndoRedo](TextEditorUndoRedo/) |
| 8 | 📈 **Hit Counter** | Sliding Window | [HitCounter](HitCounter/) |
| 9 | ❌⭕ **Tic Tac Toe** | — | [TicTacToe](TicTacToe/) |
| 10 | 🎬 **Movie Ticket Booking** | SRP, Atomic booking | [MovieTicketBooking](MovieTicketBooking/) |
| 11 | 🅿️ **Parking Lot** | Strategy | [ParkingLot](ParkingLot/) |
| 12 | 🗺️ **Custom HashMap** | Hashing, Chaining | [CustomHashMap](CustomHashMap/) |
| 13 | 🛒 **Shopping Cart** | Strategy | [ShoppingCart](ShoppingCart/) |
| 14 | 📞 **Phone Directory** | Trie | [PhoneDirectory](PhoneDirectory/) |
| 15 | 🔍 **Search Autocomplete** | Trie | [SearchAutocomplete](SearchAutocomplete/) |
| 16 | 🏢 **Meeting Room Reservation** | Strategy | [MeetingRoom](MeetingRoom/) |
| 17 | 🏆 **Leaderboard** | TreeSet + HashMap | [Leaderboard](Leaderboard/) |
| 18 | ⏱️ **Rate Limiter** | Strategy | [RateLimiter](RateLimiter/) |
| 19 | 📢 **Pub/Sub System** | Observer | [PubSub](PubSub/) |
| 20 | 🚆 **Train Platform Management** | Strategy | [TrainPlatform](TrainPlatform/) |
| 21 | 🚗 **Car Rental System** | Strategy | [CarRental](CarRental/) |
| 22 | 📨 **Kafka-like Messaging** | Partitioned Log, Offset tracking | [KafkaMessaging](KafkaMessaging/) |

## 🎯 Design Patterns Used

| Pattern | Problems |
|---------|----------|
| **Strategy** | JobScheduler, ElevatorManagement, ParkingLot, ShoppingCart, MeetingRoom, RateLimiter, TrainPlatform, CarRental |
| **State** | ElevatorManagement |
| **Command** | TextEditorUndoRedo |
| **Observer** | PubSub |
| **Flyweight** | TextEditor, Spreadsheet |

## 🚀 How to Run Any Problem

```bash
# General pattern:
javac -d out $(find <FolderName> -name "*.java")
java -cp out <FolderName>.<MainClass>

# Example:
javac -d out $(find MovieTicketBooking -name "*.java")
java -cp out MovieTicketBooking.MovieTicketBookingMain
```

Each folder has its own `README.md` with problem description, design details, and run instructions.
