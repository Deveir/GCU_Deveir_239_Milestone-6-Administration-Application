package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles reading and writing inventory data using JSON files.
 */
public class FileService {

    private final String fileName = "inventory.json";
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Reads inventory products from a JSON file.
     *
     * @return list of salable products
     */
    public ArrayList<SalableProduct> readInventory() {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                return new ArrayList<>();
            }

            return mapper.readValue(file, new TypeReference<ArrayList<SalableProduct>>() {});
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Writes inventory products to a JSON file.
     *
     * @param products list of products to save
     */
    public void writeInventory(ArrayList<SalableProduct> products) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), products);
        } catch (IOException e) {
            System.out.println("Error writing inventory file: " + e.getMessage());
        }
    }

    /**
     * Converts a list of products into a JSON String.
     *
     * @param products list of products
     * @return JSON String
     */
    public String productsToJson(ArrayList<SalableProduct> products) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
        } catch (IOException e) {
            return "[]";
        }
    }

    /**
     * Converts a JSON String into a list of products.
     *
     * @param json JSON product data
     * @return list of salable products
     */
    public ArrayList<SalableProduct> jsonToProducts(String json) {
        try {
            return mapper.readValue(json, new TypeReference<ArrayList<SalableProduct>>() {});
        } catch (IOException e) {
            System.out.println("Error converting JSON to products: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}