package app;

/**
 * Represents a product that can be sold in the store.
 */
public class SalableProduct {

    private String name;
    private String description;
    private double price;
    private int quantity;

    /**
     * Default constructor.
     */
    public SalableProduct() {
    }

    /**
     * Creates a salable product.
     *
     * @param name product name
     * @param description product description
     * @param price product price
     * @param quantity product quantity
     */
    public SalableProduct(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * Returns product information as a String.
     *
     * @return product details
     */
    @Override
    public String toString() {
        return name + " - " + description + " - $" + price + " - Qty: " + quantity;
    }
}