package app;

import java.util.ArrayList;

/**
 * Represents a shopping cart for the store user.
 */
public class ShoppingCart {

    private ArrayList<SalableProduct> cartItems;

    /**
     * Creates an empty shopping cart.
     */
    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    /**
     * Adds a product to the cart.
     *
     * @param product product to add
     */
    public void addProduct(SalableProduct product) {
        cartItems.add(product);
    }

    /**
     * Displays cart contents.
     */
    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (SalableProduct product : cartItems) {
                System.out.println(product);
            }
        }
    }
}