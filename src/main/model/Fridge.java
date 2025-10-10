package model;

import java.util.ArrayList;

public class Fridge {

    private int maxSize;
    private int size;

    private ArrayList<Food> fridge;

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
        Food exist = fridgeContains(food.getName());
        if (exist != null) {
            exist.setQuantity(exist.getQuantity() + food.getQuantity());
        } else {
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
        Food food = fridgeContains(name);
        if (food != null) {
            if (food.getQuantity() < quantity) {
                return false;
            }
            decreaseQuantity(food, quantity);
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: changes how many days the item has before expiring
    public boolean changeExpiryDate(String name, int days) {
        Food food = fridgeContains(name);
        if (food == null) {
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
    public Food fridgeContains(String name) {
        for (Food f : fridge) {
            if (f.getName().equalsIgnoreCase(name)) {
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
    public void decreaseQuantity(Food food, int quantity) {
            if (food.getQuantity() >= quantity) {
                food.setQuantity(food.getQuantity() - quantity);
                size -= quantity;
                if (food.getQuantity() == 0) {
                    fridge.remove(food);
                }
            } //throw exception?    
    }

    public ArrayList<Food> getFridgeContents() {
        return fridge;
    }

    public int getSize() {
        return size;
    }

    public int getRemainingSpace() {
        return maxSize - size;
    }



}
