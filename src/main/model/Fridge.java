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
        else if (contains(food.name).equals(food)) {
            Food temp = contains(food.name);
            temp.setQuantity(temp.getQuantity() + food.getQuantity());
            size += food.getQuantity();
            return true;
        }
        else {
            fridge.add(food);
            size += food.getQuantity();
            return true;
        }
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
    public boolean ChangeExpiryDate(Food food, int days) {
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
    protected Food contains(String name) {
        Food temp = null;
        for (int i = 0; i < fridge.size(); i++) {
            if(fridge.get(i).getName().equalsIgnoreCase(name)) {
                temp = fridge.get(i);
                return temp;
            }
        }
        return temp;
    }

    /*
     * REQUIRES: name of valid food in fridge
     * MODIFIES: this
     * EFFECTS: decreases the number of a certain food
     */
    public void DecreaseQuantity(String name, int quantity) {
        for (int i = 0; i < fridge.size(); i++) {
            if (fridge.get(i).getName().equalsIgnoreCase(name)) {
                Food temp = fridge.get(i);
                if (temp.getQuantity() >= quantity) {
                    temp.setQuantity(temp.getQuantity() - quantity);
                    size -= temp.getExpiryDate() - quantity;
                } //throw exception?
                break;
            }
        }
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

    public int getSize() {
        return size;
    }



}
