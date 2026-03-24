package ShoppingCart.strategy;

import ShoppingCart.model.CartItem;
import java.util.List;

/**
 * Buy N items, get 1 free on each product.
 */
public class BuyNGetFreeStrategy implements PricingStrategy {
    private final int buyN;

    public BuyNGetFreeStrategy(int buyN) { this.buyN = buyN; }

    @Override
    public double calculateTotal(List<CartItem> items) {
        double total = 0;
        for (CartItem item : items) {
            int freeItems = item.getQuantity() / (buyN + 1);
            int paidItems = item.getQuantity() - freeItems;
            total += paidItems * item.getProduct().getPrice();
        }
        return total;
    }
    @Override
    public String getName() { return "Buy " + buyN + " Get 1 Free"; }
}
