package persistence;

import model.Food;
import model.Frozen;
import model.Fruit;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.jupiter.api.Assertions.*;

// Based on the JsonTest class in the Workroom repo provided in phase 2

@ExcludeFromJacocoGeneratedReport

public class JsonTest {

    public void checkFood(String name, int quantity, int expiryDate, Food f) {
        assertEquals(name, f.getName());
        assertEquals(quantity, f.getQuantity());
        assertEquals(expiryDate, f.getExpiryDate());
    }
    

    public void checkFrozen(String name, int quantity, int expiryDate, boolean isFrozen, Frozen f) {
        assertEquals(name, f.getName());
        assertEquals(quantity, f.getQuantity());
        assertEquals(expiryDate, f.getExpiryDate());
        assertEquals(isFrozen, f.getFrozen());
    }
    
    public void checkFruit(String name, int quantity, int expiryDate, boolean ripe, Fruit f) {
        assertEquals(name, f.getName());
        assertEquals(quantity, f.getQuantity());
        assertEquals(expiryDate, f.getExpiryDate());
        assertEquals(ripe, f.isRipe());
    }

}
