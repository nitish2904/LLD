# 🎬 Movie Ticket Booking (BookMyShow) — LLD

Design a movie ticket booking system with seat selection, booking, and cancellation.

**Problem Link:** [CodeZym #10](https://codezym.com/question/10)

## Design Patterns & Concepts

| Concept | Purpose | Classes |
|---------|---------|---------|
| **SRP** | Separate models from booking logic | `Show`, `Seat`, `Booking` vs `BookingService` |
| **Seat layout** | 2D grid with seat types | `Show.seats[][]` |
| **Atomic booking** | All-or-nothing seat reservation | `BookingService.bookSeats()` |

## 🔑 Key Concepts

- **Seat types** with prices: REGULAR($150), PREMIUM($250), VIP($500)
- **2D seat layout**: rows A-F, columns 1-8; first 2 rows VIP, next 2 PREMIUM, rest REGULAR
- **Booking**: select multiple seats atomically — if any taken, whole booking fails
- **Cancellation**: frees seats back to available
- **Show layout visualization**: [X] for booked, [ ] for available

## 📂 Package Structure

```
MovieTicketBooking/
├── model/
│   ├── SeatType.java  — enum with prices
│   ├── Seat.java      — row, col, type, booked
│   ├── Show.java      — movie, screen, time, 2D seat grid
│   └── Booking.java   — show, customer, seats, total cost
├── service/
│   └── BookingService.java — bookSeats, cancel, layout
└── MovieTicketBookingMain.java
```

## 🚀 How to Run

```bash
javac -d out $(find MovieTicketBooking -name "*.java")
java -cp out MovieTicketBooking.MovieTicketBookingMain
```
