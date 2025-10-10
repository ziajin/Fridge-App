package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


public class FridgeTest {

    private Fridge test;
    private Food testFood;
    private Food testFood2;
    private Food testFood3;

    @BeforeEach 
    void RunBefore() {
        test = new Fridge();
        testFood = new Food("Berry", 4, 2);
        testFood2 = new Food("Celery", 2, 6);
        testFood3 = new Food("Potato", 10, 20);
    }

    @Test
    void TestAddItem() {
        test.addItem(testFood);
        assertEquals(testFood, test.getFridgeContents().get(0));
        test.addItem(testFood2);
        assertEquals(testFood2, test.getFridgeContents().get(1));
    }

    @Test
    void TestAddSameItem() {
        test.addItem(testFood);
        assertEquals(testFood, test.getFridgeContents().get(0));
        test.addItem(testFood);
        assertEquals(testFood, test.getFridgeContents().get(0));
        assertEquals(8, test.getFridgeContents().get(0).getQuantity());
    }

    @Test
    void TestAddOverMaxSize() {
        test.addItem(testFood);
        assertEquals(4, test.getSize());
        test.addItem(testFood3); //14
        test.addItem(testFood3); //24
        assertFalse(test.addItem(testFood3));
    }

    @Test
    void TestRemoveItem() {
        test.addItem(testFood);
        assertEquals(testFood, test.getFridgeContents().get(0));
        test.removeItem(testFood.getName(), 0);
        assertEquals(4, test.getFridgeContents().get(0).getQuantity());
        test.removeItem(testFood.getName(), 2);
        assertEquals(2, test.getFridgeContents().get(0).getQuantity());
        assertFalse(test.removeItem(testFood.getName(), 100));
        assertFalse(test.removeItem("fakeName", 1));
    }
    
    @Test
    void TestExpriryDate() {
        test.addItem(testFood);
        assertEquals(2, test.getFridgeContents().get(0).getExpiryDate());
        assertFalse(test.ChangeExpiryDate(testFood.getName(), 4));
        assertFalse(test.ChangeExpiryDate("notFood", 1));
        assertTrue(test.ChangeExpiryDate(testFood.getName(), 1));
        assertEquals(3, test.getFridgeContents().get(0).getExpiryDate());
    }

    @Test
    void TestChangeQuantity() {
        test.addItem(testFood);
        assertEquals(4, test.getFridgeContents().get(0).getQuantity());
        test.DecreaseQuantity(testFood.getName(), 1);
        assertEquals(3, test.getFridgeContents().get(0).getQuantity());
        test.DecreaseQuantity(testFood.getName(), 10);
        assertEquals(3, test.getFridgeContents().get(0).getQuantity());
        test.DecreaseQuantity("test", 1);
    }


    
}
