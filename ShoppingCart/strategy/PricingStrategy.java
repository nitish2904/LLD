package ShoppingCart.strategy;

import ShoppingCart.model.CartItem;
import java.util.List;

/**
 * Strategy Pattern: pluggable discount/pricing algorithm. (OCP)
 */
public interface PricingStrategy {
    double calculateTotal(List<CartItem> items);
    String getName();
}
