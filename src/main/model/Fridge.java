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
        if (size + food.getQuantity() > maxSize) {
            return false;
        } 

        Food exist = contains(food.getName());
        if (exist != null) {
            exist.setQuantity(exist.getQuantity() + food.getQuantity());
        }
        else {
            fridge.add(food);
        }
        size += food.getQuantity();
        return true;
    }

    /*
     * MODIFIES: this 
     * EFFECTS: removes specified food item from fridgeArray, increases space in fridge
     */
    public boolean removeItem(String name, int quantity) {
        if (fridge.contains(name)) {
            DecreaseQuantity(name, quantity);
            size -= quantity;
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: returns how many days the item has before expiring
    public int ExpiryDate(Food food) {
        return food.getExpiryDate();
    }

    //MODIFIES: this
    //EFFECTS: changes how many days the item has before expiring
    public boolean ChangeExpiryDate(String name, int days) {
        Food food = contains(name);
        if (!(food instanceof Food)) {
            return false;
        }
        if (food.getExpiryDate() - Math.abs(days) < 0) {
            return false;
        } else { 
            food.setExpiryDate(food.getExpiryDate() + days);
            return true;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if a food is already in the fridge, add the quantities together
     */
    public Food contains(String name) {
        for (Food f : fridge) {
            if(f.getName().equalsIgnoreCase(name)) {
                return f;
            }
        }
        return null;
    }

    /*
     * REQUIRES: name of valid food in fridge
     * MODIFIES: this
     * EFFECTS: decreases the number of a certain food
     */
    public void DecreaseQuantity(String name, int quantity) {
        Food food = contains(name);
        if (food != null) {
            if (food.getQuantity() >= quantity) {
                 food.setQuantity(food.getQuantity() - quantity);
                size -= food.getQuantity() - quantity;
                } //throw exception?    
        }
    }

    /* REQUIRES: name of valid food in fridge
     * MODIFIES: this
     * EFFECTS: decreases the number of a certain food
     */
    public void IncreaseQuantity(String name, int quantity) {
        //stub
    }

    public ArrayList<Food> getFridgeContents() {
        return fridge;
    }

    public int getSize() {
        return size;
    }



}
