package persistence;

import model.Food;
import model.Fridge;
import model.Frozen;
import model.Fruit;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Based on the persistence package in the Workroom repo provided in phase 2
// Reader that reads in fridge object from designated file

public class JsonReader {
    private String file;

    // EFFECTS: constructs reader to read object in from file
    public JsonReader(String file) {
        this.file = file;
    }

    // EFFECTS: constructs reader, throws IOException if any error
    // occurs during the reading process
    public Fridge read() throws IOException {
        String fileContent = readFile(file);
        JSONObject json = new JSONObject(fileContent);
        return parseJson(json);
        // stub

    }

    // EFFECTS: read source file as a string and returns it
    private String readFile(String file) throws IOException {
        StringBuilder fileContent = new StringBuilder();

        try (Stream<String> in = Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
            in.forEach(s -> fileContent.append(s));
        }

        return fileContent.toString();
    }

    // EFFECTS: parses data from JSON object and returns fridge object
    private Fridge parseJson(JSONObject json) {
        Fridge fridge = new Fridge();
        addMultipleFood(fridge, json);
        return fridge;
    }

    // MODIFIES: fridge
    // EFFECTS: parses food objects from JSON object and adds them to fridge
    private void addMultipleFood(Fridge f, JSONObject json) {
        JSONArray ar = json.getJSONArray("Fridge");
        for (Object o : ar) {
            JSONObject next = (JSONObject) o;
            addFood(f, next);
        }
    }

    // MODIFIES: fridge
    // EFFECTS: parses a single food object from JSON object and add it to fridge
    private void addFood(Fridge f, JSONObject json) {
        String name = json.getString("Name");
        int quantity = json.getInt("Quantity");
        int expiryDate = json.getInt("Expiry Date");

        if (json.get("Type").equals("Frozen")) {
            boolean isFrozen = json.getBoolean("Frozen");
            Frozen frozen = new Frozen(name, quantity, expiryDate, isFrozen);
            f.addItem(frozen);
        } else if (json.get("Type").equals("Fruit")) {
            boolean isRipe = json.getBoolean("Ripe");
            Fruit fruit = new Fruit(name, quantity, expiryDate, isRipe);
            f.addItem(fruit);
        } else {
            Food food = new Food(name, quantity, expiryDate);
            f.addItem(food);
        }
    }
}
