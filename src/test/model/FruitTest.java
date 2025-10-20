package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FruitTest {

    private Fridge test;
    private Fruit testFood;
    
    @BeforeEach 
    public void beforeEach() {
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
    public void testExpriryDate() {
        assertTrue(testFood.isRipe());
        assertEquals(2, testFood.getExpiryDate()); // must match constructor argument
        testFood.setRipe(false);
        assertFalse(testFood.isRipe());
    }

    @Test
    void toStringTest() {
        int quantity = testFood.getQuantity();
        int date =  testFood.getExpiryDate();
        String name = testFood.getName();
        String test = "Name: " + name + "\tQuantity: " + quantity + "\tExpiry Date: " + date + "\n";
        assertEquals(test, testFood.toString());

    }
}
