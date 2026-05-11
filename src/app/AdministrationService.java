package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Background service that receives administration commands over the local network.
 */
public class AdministrationService implements Runnable {

    private int port;
    private InventoryManager inventoryManager;
    private boolean running;
    private FileService fileService;

    /**
     * Creates an administration service.
     *
     * @param port port number for the service
     * @param inventoryManager inventory manager used by the store
     */
    public AdministrationService(int port, InventoryManager inventoryManager) {
        this.port = port;
        this.inventoryManager = inventoryManager;
        this.running = true;
        this.fileService = new FileService();
    }

    /**
     * Starts the service in the background.
     */
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Administration Service running on port " + port);

            while (running) {
                Socket socket = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String command = in.readLine();
                String payload = in.readLine();

                String response = processCommand(command, payload);
                out.println(response);

                socket.close();
            }

        } catch (IOException e) {
            System.out.println("Administration Service error: " + e.getMessage());
        }
    }

    /**
     * Processes administration commands.
     *
     * @param command command sent by the admin user
     * @param payload JSON data payload
     * @return response message
     */
    private String processCommand(String command, String payload) {
        if (command == null) {
            return "Invalid command.";
        }

        if (command.equalsIgnoreCase("U")) {
            ArrayList<SalableProduct> newProducts = fileService.jsonToProducts(payload);
            inventoryManager.addProducts(newProducts);
            return "Inventory updated successfully.";
        } else if (command.equalsIgnoreCase("R")) {
            return fileService.productsToJson(inventoryManager.getAllProducts());
        } else {
            return "Unknown command.";
        }
    }

    /**
     * Stops the administration service.
     */
    public void stop() {
        running = false;
    }
}