package app;

import java.util.ArrayList;

/**
 * Manages the store inventory.
 */
public class InventoryManager {

    private ArrayList<SalableProduct> products;
    private FileService fileService;

    /**
     * Creates an inventory manager and loads products from the JSON file.
     */
    public InventoryManager() {
        fileService = new FileService();
        products = new ArrayList<>();
        loadInventory();
    }

    /**
     * Loads inventory from file.
     */
    public void loadInventory() {
        products = fileService.readInventory();
    }

    /**
     * Saves inventory to file.
     */
    public void saveInventory() {
        fileService.writeInventory(products);
    }

    /**
     * Adds new products to the inventory.
     *
     * @param newProducts products to add
     */
    public synchronized void addProducts(ArrayList<SalableProduct> newProducts) {
        products.addAll(newProducts);
        saveInventory();
    }

    /**
     * Returns all products in inventory.
     *
     * @return list of products
     */
    public synchronized ArrayList<SalableProduct> getAllProducts() {
        return products;
    }

    /**
     * Displays all inventory products.
     */
    public void displayInventory() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i));
            }
        }
    }
}