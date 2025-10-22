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

// Reader that reads in fridge object from designated file
public class JsonReader {
    private String file;
    

    //EFFECTS: constructs reader to read object in from file
    public JsonReader(String file) {
        this.file = file;
    }

    //EFFECTS: constructs reader, throws IOException if any error
    // occurs during the reading process
    public Fridge read() throws IOException {
        return null;
        //stub

    }

    //EFFECTS: read source file as a string and returns it
    private String readFile(String file) throws IOException {
        //stub
        return null;
    }

    //EFFECTS: parses data from JSON object and returns fridge object
    private Fridge parseJSON(JSONObject json) {
        //stub
        return null;
    }

    //MODIFIES: fridge
    //EFFECTS: parses food objects from JSON object and adds them to fridge
    private void addMultipleFood(Fridge f, JSONObject json) {
        //stub
    }

    //MODIFIES: fridge
    //EFFECTS: parses a single food object from JSON object and add it to fridge
    private void addFood(Fridge f, JSONObject json) {
        //stub
    }
}
