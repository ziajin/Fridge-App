package model;

import java.util.ArrayList;
import java.util.List;

public class Fridge {

    private int maxSize;
    protected int expiry;

    protected ArrayList<Food> fridge = new ArrayList<>(maxSize);


    public Fridge() {
        maxSize = 30;
    }

    /*
     * MODIFIES: this
     * EFFECTS: add item to fridgeArray if there is enough space, returns true if succesful, false if not.
     */
    public boolean addItem(Food food) {
        //stub
        return false;
    }

    /*
     * MODIFIES: this 
     * EFFECTS: removes specified food item from fridgeArray, increases space in fridge
     */
    public boolean removeItem(String name) {
        //stub
        return false;
    }

    //EFFECTS: returns how many days the item has before expiring
    public int ExpiryDate() {
        //stub
        return 0;
    }

    //MODIFIES: this
     //EFFECTS: returns how many days the item has before expiring
    public int ChangeExpiryData(int days) {
        //stub
        return 0;
    }

    /*
     * REQUIRES: name of valid food in fridge
     * MODIFIES: this
     * EFFECTS: decreases the number of a certain food
     */
    public void DecreaseQuantity(String name, int quantity) {
        //stub
    }

    /* REQUIRES: name of valid food in fridge
     * MODIFIES: this
     * EFFECTS: decreases the number of a certain food
     */
    public void IncreaseQuantity(String name, int quantity) {
        //stub
    }

    public List<Food> getFridge() {
        return fridge;
    }



}
