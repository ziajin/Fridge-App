package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class FrozenTest {

    private Fridge test;
    private Food testFood;
    private Frozen testFood2;
    
    @BeforeEach
    void runBefore() {
        test = new Fridge();
        testFood = new Food("Chicken", 4, 2);
        testFood2 = new Frozen("Potato", 10, 20, true);
    }

    @Test
    void testThaw() {
        assertTrue(testFood2.getFrozen());
        assertEquals(20, testFood2.getExpiryDate());
        testFood2.thaw(testFood);
        assertFalse(testFood2.getFrozen());
        assertEquals(19, testFood2.getExpiryDate());
        testFood2.thaw(testFood);
        assertFalse(testFood2.getFrozen());
        assertEquals(19, testFood2.getExpiryDate());
    }

    @Test
    void testRefreeze() {
        assertTrue(testFood2.getFrozen());
        testFood2.refreeze(testFood);
        assertTrue(testFood2.getFrozen());
        testFood2.refreeze(testFood);
        assertTrue(testFood2.getFrozen());
    }

    @Test
    void testAddToFridge() {
        test.addItem(testFood2);
        assertEquals(testFood2, test.getFridgeContents().get(0));
        test.addItem(testFood2);
        assertEquals(20, test.getFridgeContents().get(0).getQuantity());
    }
}
