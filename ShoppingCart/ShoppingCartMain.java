package ShoppingCart;

import ShoppingCart.model.Product;
import ShoppingCart.service.CartService;
import ShoppingCart.strategy.*;

/**
 * Shopping Cart — LLD (#41)
 * Strategy Pattern for pricing/discounts.
 */
public class ShoppingCartMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Shopping Cart — LLD Demo                ║");
        System.out.println("╚════════════════════════════════════════╝");

        Product p1 = new Product("1", "Laptop", 999.99, "Electronics");
        Product p2 = new Product("2", "Mouse", 29.99, "Electronics");
        Product p3 = new Product("3", "Book", 14.99, "Books");

        CartService cart = new CartService(new NoPricingStrategy());

        System.out.println("\n===== Add items =====");
        cart.addItem(p1, 1);
        cart.addItem(p2, 2);
        cart.addItem(p3, 3);
        cart.printCart();
        cart.checkout();

        System.out.println("\n===== 10% Discount =====");
        cart.setStrategy(new PercentageDiscountStrategy(10));
        cart.checkout();

        System.out.println("\n===== Buy 2 Get 1 Free =====");
        cart.setStrategy(new BuyNGetFreeStrategy(2));
        cart.checkout();

        System.out.println("\n===== Update & Remove =====");
        cart.updateQuantity("2", 5);
        cart.removeItem("3");
        cart.printCart();
        cart.setStrategy(new NoPricingStrategy());
        cart.checkout();

        System.out.println("\n✅ Done.");
    }
}
