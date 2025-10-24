package persistence;

import model.Food;
import model.Fridge;
import model.Frozen;
import model.Fruit;

import java.io.*;
import org.json.*;

// Based on the persistence package in the Workroom repo provided in phase 2
// constructs print writer to write fridge object to file

public class JsonWriter {

    private final int tab = 4;
    private PrintWriter writer;
    private String file;


    //EFFECT: constructs writer to write to file
    public JsonWriter(String file) {
        this.file = file;
    }

    //MODIFIES: this
    //EFFECTS: constructs printwriter to the destination file, throws FileNotFoundException
    // if the file cannot be opened or found
    public void writeOn() throws FileNotFoundException {
        writer = new PrintWriter(new File(file));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of fridge to file
    public void writeToFile(Fridge f) {
        JSONObject json = f.toJson();
        String line = json.toString(tab);
        writer.print(line);
    }

    //MODIFIES: this
    //EFFECTS: closees writer stream
    public void close() {
        writer.close();
    }

}
