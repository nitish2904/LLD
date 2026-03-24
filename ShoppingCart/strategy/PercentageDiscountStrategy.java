package ShoppingCart.strategy;

import ShoppingCart.model.CartItem;
import java.util.List;

public class PercentageDiscountStrategy implements PricingStrategy {
    private final double percent;

    public PercentageDiscountStrategy(double percent) { this.percent = percent; }

    @Override
    public double calculateTotal(List<CartItem> items) {
        double subtotal = items.stream().mapToDouble(CartItem::getTotal).sum();
        return subtotal * (1 - percent / 100);
    }
    @Override
    public String getName() { return percent + "% Discount"; }
}
