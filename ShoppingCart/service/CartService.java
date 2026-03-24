package ShoppingCart.service;

import ShoppingCart.model.*;
import ShoppingCart.strategy.PricingStrategy;

import java.util.*;

public class CartService {
    private final Map<String, CartItem> items;
    private PricingStrategy pricingStrategy;

    public CartService(PricingStrategy strategy) {
        this.items = new LinkedHashMap<>();
        this.pricingStrategy = strategy;
    }

    public void addItem(Product product, int qty) {
        items.merge(product.getId(), new CartItem(product, qty),
                (old, newItem) -> { old.setQuantity(old.getQuantity() + qty); return old; });
        System.out.println("  Added " + product + " x" + qty);
    }

    public void removeItem(String productId) {
        CartItem removed = items.remove(productId);
        System.out.println("  " + (removed != null ? "Removed " + removed.getProduct() : "Not found"));
    }

    public void updateQuantity(String productId, int qty) {
        CartItem item = items.get(productId);
        if (item != null) { item.setQuantity(qty); System.out.println("  Updated " + item); }
    }

    public void setStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
        System.out.println("  Pricing: " + strategy.getName());
    }

    public double checkout() {
        double total = pricingStrategy.calculateTotal(new ArrayList<>(items.values()));
        System.out.printf("  Checkout (%s): $%.2f%n", pricingStrategy.getName(), total);
        return total;
    }

    public void printCart() {
        System.out.println("\n  Cart (" + items.size() + " items):");
        items.values().forEach(i -> System.out.println("    " + i));
        double subtotal = items.values().stream().mapToDouble(CartItem::getTotal).sum();
        System.out.printf("    Subtotal: $%.2f%n", subtotal);
    }
}
