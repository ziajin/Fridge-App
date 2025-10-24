package persistence;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Food;
import model.Fridge;
import model.Frozen;
import model.Fruit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

// Based on the ReaderTest in the Workroom repo provided in phase 2

@ExcludeFromJacocoGeneratedReport

public class JsonReaderTest extends JsonTest {
    
    private String file = "./data/testWriterGen.json";
    private File test;

    @BeforeEach
    void runBefore() {
        test = new File(file);
        if (test.exists()) {
            test.delete();
        }
    }
    
    @Test
    void testNonExistentFile() {
        JsonReader read = new JsonReader(file);
        try {
            Fridge f = read.read();
            fail("IOException expecteed");
        } catch (IOException e) {
            //expect exception
        }
    }

    @Test
    void testEmptyFridge() {
        try {
            JsonReader read = new JsonReader("./data/testEmpty.json");
            Fridge f = read.read();
            assertTrue(f.getFridgeContents().isEmpty());
        } catch (IOException e) {
            fail("No exception expected");
        }

    }

    @Test
    void testReadInFridgeWithItems() {
        try {
            JsonReader read = new JsonReader("./data/testWriteFridge.json");
            Fridge f = read.read();
            List<Food> fridge = f.getFridgeContents();
            
            assertEquals(3, fridge.size());

            checkFood("milk", 1, 10, fridge.get(0));
            checkFrozen("ice cream", 3, 40, true, (Frozen) fridge.get(1));
            checkFruit("apple", 7, 5, false, (Fruit) fridge.get(2));

        } catch (IOException e) {
            System.out.println("Could not read");
        }

    }
}
