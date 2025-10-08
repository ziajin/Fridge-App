package model;

import java.util.ArrayList;
import java.util.List;

public class Fridge {

    private int maxSize;
    private int size;
    protected int expiry;

    protected ArrayList<Food> fridge;

    public Fridge() {
        maxSize = 30;
        size = 0;
        fridge = new ArrayList<>(maxSize);
    }

    /*
     * MODIFIES: this
     * EFFECTS: add item to fridgeArray if there is enough space, returns true if succesful, false if not.
     */
    public boolean addItem(Food food) {
        if (size + food.quantity > maxSize) {
            return false;
        } else {
            fridge.add(food);
            size += food.quantity;
        }

        return false;
    }

    /*
     * MODIFIES: this 
     * EFFECTS: removes specified food item from fridgeArray, increases space in fridge
     */
    public boolean removeItem(Food food) {
        if (fridge.contains(food)) {
        fridge.remove(food);
        return true;
        } else {
            return false;
        }
    }

    //EFFECTS: returns how many days the item has before expiring
    public int ExpiryDate(Food food) {
        return food.expiryDate;
    }

    //MODIFIES: this
     //EFFECTS: changes how many days the item has before expiring
    public boolean ChangeExpiryData(Food food, int days) {
        if (food.expiryDate - Math.abs(days) < 0) {
            return false;
        } else { 
            food.setExpiryDate(food.getExpiryDate() + days);
            return true;
        }
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
