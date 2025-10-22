package persistence;

import model.Food;
import model.Fridge;
import model.Frozen;
import model.Fruit;

import java.io.*;

// constructs print writer to write fridge object to file

public class JsonWriter {

    private final int tab = 4;
    private PrintWriter writer;
    private String file;


    //EFFECT: constructs writer to write to file
    public JsonWriter(String file) {
        //stub
    }

    // MODIFIES: this
    //EFFECTS: constructs printwriter to the destination file, throws FileNotFoundException
    // if the file cannot be opened or found
    public void writeOn() throws FileNotFoundException {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of fridge to file
    public void write(Fridge f) {
        //stub
    }

}
