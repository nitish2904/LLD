# 🚗 Car Rental System — LLD

Design a car rental system with fleet management, reservations, and pluggable car selection using the **Strategy Pattern**.

**Problem Link:** [CodeZym #21](https://codezym.com/question/21)

## Design Patterns Used

| Pattern | Purpose | Classes |
|---------|---------|---------|
| **Strategy** | Pluggable car selection (CheapestCar, ExactType) | `CarSelectionStrategy`, `CheapestCarStrategy`, `ExactTypeStrategy` |

## 🔑 Key Concepts

- **Car types** with daily rates: HATCHBACK($50), SEDAN($75), SUV($100), LUXURY($200)
- **Reservations** with start/end date, auto-calculated cost
- **Fleet management**: add cars, rent, return, check availability
- **Strategy swap** at runtime

## 📂 Package Structure

```
CarRental/
├── model/
│   ├── CarType.java      — enum with daily rates
│   ├── Car.java           — id, model, type, availability
│   └── Reservation.java   — car, customer, dates, cost
├── strategy/
│   ├── CarSelectionStrategy.java — interface
│   ├── CheapestCarStrategy.java  — cheapest available
│   └── ExactTypeStrategy.java    — exact type match only
├── service/
│   └── CarRentalService.java     — rent, return, fleet management
└── CarRentalMain.java
```

## 🚀 How to Run

```bash
javac -d out $(find CarRental -name "*.java")
java -cp out CarRental.CarRentalMain
```
