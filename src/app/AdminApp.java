package app;

import java.util.Scanner;

/**
 * Console-based application used by an admin user to manage inventory remotely.
 */
public class AdminApp {

    /**
     * Runs the Administration Application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminClient client = new AdminClient("localhost", 5000);

        boolean running = true;

        while (running) {
            System.out.println("\nADMINISTRATION APPLICATION");
            System.out.println("1. Update Inventory");
            System.out.println("2. Retrieve Inventory");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println("Enter JSON product payload:");
                String payload = scanner.nextLine();

                String response = client.sendCommand("U", payload);
                System.out.println("Server Response: " + response);

            } else if (choice.equals("2")) {
                String response = client.sendCommand("R", "");
                System.out.println("Inventory JSON:");
                System.out.println(response);

            } else if (choice.equals("3")) {
                running = false;

            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}