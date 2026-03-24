# ⏱️ Rate Limiter — LLD

Design a rate limiter with pluggable algorithms using the **Strategy Pattern**.

**Problem Link:** [CodeZym #34](https://codezym.com/question/34)

## Design Patterns Used

| Pattern | Purpose | Classes |
|---------|---------|---------|
| **Strategy** | Pluggable rate limiting algorithms | `RateLimitStrategy`, `SlidingWindowStrategy`, `TokenBucketStrategy` |

## 🔑 Key Concepts

- **Sliding Window**: track request timestamps per client, reject when window is full
- **Token Bucket**: tokens refill at fixed rate, each request consumes one token
- **Per-client** rate limiting using client IDs
- **Runtime strategy swap**: switch between algorithms

## 📂 Package Structure

```
RateLimiter/
├── strategy/
│   ├── RateLimitStrategy.java      — interface
│   ├── SlidingWindowStrategy.java  — sliding window counter
│   └── TokenBucketStrategy.java    — token bucket with refill
├── service/
│   └── RateLimiterService.java     — facade
└── RateLimiterMain.java
```

## 🚀 How to Run

```bash
javac -d out $(find RateLimiter -name "*.java")
java -cp out RateLimiter.RateLimiterMain
```
