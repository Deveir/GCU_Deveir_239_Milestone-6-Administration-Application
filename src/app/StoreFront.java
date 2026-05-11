package app;

import java.util.Scanner;

/**
 * Main Store Front Application used by the game user.
 */
public class StoreFront {

    /**
     * Runs the Store Front Application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        InventoryManager inventoryManager = new InventoryManager();
        ShoppingCart shoppingCart = new ShoppingCart();

        AdministrationService adminService = new AdministrationService(5000, inventoryManager);
        Thread adminThread = new Thread(adminService);
        adminThread.start();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nSTORE FRONT APPLICATION");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                inventoryManager.displayInventory();

            } else if (choice.equals("2")) {
                inventoryManager.displayInventory();
                System.out.print("Enter product number to add to cart: ");

                int productNumber = Integer.parseInt(scanner.nextLine()) - 1;

                if (productNumber >= 0 && productNumber < inventoryManager.getAllProducts().size()) {
                    shoppingCart.addProduct(inventoryManager.getAllProducts().get(productNumber));
                    System.out.println("Product added to cart.");
                } else {
                    System.out.println("Invalid product number.");
                }

            } else if (choice.equals("3")) {
                shoppingCart.viewCart();

            } else if (choice.equals("4")) {
                running = false;
                System.out.println("Exiting Store Front.");

            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}