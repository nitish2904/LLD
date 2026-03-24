package MovieTicketBooking.model;

public enum SeatType {
    REGULAR(150), PREMIUM(250), VIP(500);

    private final double price;
    SeatType(double price) { this.price = price; }
    public double getPrice() { return price; }
}
