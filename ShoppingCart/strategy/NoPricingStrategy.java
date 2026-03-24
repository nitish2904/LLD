package ShoppingCart.strategy;

import ShoppingCart.model.CartItem;
import java.util.List;

public class NoPricingStrategy implements PricingStrategy {
    @Override
    public double calculateTotal(List<CartItem> items) {
        return items.stream().mapToDouble(CartItem::getTotal).sum();
    }
    @Override
    public String getName() { return "No Discount"; }
}
