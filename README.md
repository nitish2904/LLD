# LLD - Low Level Design Problems

Java implementations of common Low Level Design (LLD) interview problems.

## Problems

### 1. Job Scheduler
Design a scheduler for a massively parallel distributed system.
- **Strategy Pattern** for machine selection criteria (extensible)
- Machines have capabilities, jobs require capabilities
- Multiple selection criteria: Least Unfinished Jobs, Most Finished Jobs

[Problem Link](https://codezym.com/question/22)

## How to Run

```bash
cd JobScheduler
javac -d out $(find . -name "*.java")
java -cp out JobScheduler.JobSchedulerMain
```
