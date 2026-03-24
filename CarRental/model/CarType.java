package CarRental.model;

public enum CarType {
    HATCHBACK(50), SEDAN(75), SUV(100), LUXURY(200);

    private final double dailyRate;
    CarType(double dailyRate) { this.dailyRate = dailyRate; }
    public double getDailyRate() { return dailyRate; }
}
