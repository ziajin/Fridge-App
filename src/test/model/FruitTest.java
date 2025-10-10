package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FruitTest {

    private Fridge test;
    private Fruit testFood;
    
    @BeforeEach 
    public void BeforeEach() {
        test = new Fridge();
        testFood = new Fruit("fruit", 10, 2, true);
        test.addItem(testFood);
    }

    @Test
    public void testRipe() {
        assertTrue(testFood.isRipe());
        testFood.setRipe(false);
        assertFalse(testFood.isRipe());
    }
    
    @Test
    public void TestExpriryDate() {
        assertTrue(testFood.isRipe());
        assertEquals(2, testFood.getExpiryDate()); // must match constructor argument
        testFood.setRipe(false);
        assertFalse(testFood.isRipe());
    }
}
