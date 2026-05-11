package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Sends administration commands to the Store Front Administration Service.
 */
public class AdminClient {

    private String host;
    private int port;

    /**
     * Creates an admin client.
     *
     * @param host server host
     * @param port server port
     */
    public AdminClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Sends a command and payload to the administration service.
     *
     * @param command admin command
     * @param payload JSON payload
     * @return server response
     */
    public String sendCommand(String command, String payload) {
        try (
            Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println(command);
            out.println(payload);

            return in.readLine();

        } catch (IOException e) {
            return "Error connecting to Administration Service: " + e.getMessage();
        }
    }
}