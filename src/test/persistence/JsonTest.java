package persistence;

import model.Food;
import model.Frozen;
import model.Fruit;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport

public class JsonTest {

    // protected Food food = new Food("milk", 1, 10);
    // protected Food frozen = new Frozen("ice cream", 3, 40, true);
    // protected Food fruit = new Fruit("apple", 7, 0, false);
    

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
