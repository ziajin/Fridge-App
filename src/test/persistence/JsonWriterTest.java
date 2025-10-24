package persistence;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Food;
import model.Fridge;
import model.Frozen;
import model.Fruit;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

// Based on the JsonWriterTest class in the Workroom repo provided in phase 2

@ExcludeFromJacocoGeneratedReport

public class JsonWriterTest extends JsonTest {

    private String file = "./data/testWriterGen.json";
    private File test;

    @BeforeEach
    void runBefore() {
        File direct = new File("./data");
        if (!direct.exists()) {
            direct.mkdirs();
        }

        test = new File(file);
        if (test.exists()) {
            test.delete();
        }
    }

    
    @Test
    void testWriteToInvalidFile() {
        try {
            JsonWriter write = new JsonWriter("./data/invalid/\0Name.json");
            write.writeOn();
            fail("FileNotFoundException/IOException expected");
        } catch (IOException e) {
            
        }

    }

    @Test
    void testWriteEmptyFridge() {
        try {
            Fridge empty = new Fridge();
            JsonWriter write = new JsonWriter(file);
            write.writeOn();
            write.writeToFile(empty);
            write.close();
            System.out.println(new String(Files.readAllBytes(Paths.get(file))));


            System.out.println("file exists:" + test.exists());
            JsonReader read = new JsonReader(file);
            empty = read.read();
            assertTrue(empty.getFridgeContents().isEmpty());
        } catch (IOException e) {
            fail("No exception expected");
        }
    }

    @Test 
    void testWriteFridge() {
        //create fridge with food items
        Fridge f = new Fridge();
        Food food = new Food("milk", 1, 10);
        Frozen frozen = new Frozen("ice cream", 3, 40, true);
        Fruit fruit = new Fruit("apple", 7, 0, false);
        //add food to fridge
        f.addItem(food);
        f.addItem(frozen);
        f.addItem(fruit);

        //test Writer
        try {
            JsonWriter write = new JsonWriter("./data/testWriteFridge.json");
            write.writeOn();
            write.writeToFile(f);
            write.close();
        } catch (IOException e) {
            fail("No exception expected");
        }

        //check written contents
        try {
            JsonReader read = new JsonReader("./data/testWriteFridge.json");
            Fridge readFridge = read.read();
            List<Food> readIn = readFridge.getFridgeContents();
            assertEquals(3, readIn.size());
            // assertEquals(food, readFridge.getFridgeContents().get(0));
            // assertEquals(frozen, readFridge.getFridgeContents().get(1));
            // assertEquals(fruit, readFridge.getFridgeContents().get(2));
            checkFood(food.getName(), food.getQuantity(), food.getExpiryDate(), food);
            checkFrozen(frozen.getName(), frozen.getQuantity(), frozen.getExpiryDate(), frozen.getFrozen(), frozen);
            checkFruit(fruit.getName(), fruit.getQuantity(), fruit.getExpiryDate(), fruit.isRipe(), fruit);

        } catch (IOException e) {
            fail("No exception expected");
        }
        
    }
}
