package model;

import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Writable;

/*
 * represents a fridge object that stores food objects in an ArrayList. provides implementation
 * of methods such as add/remove item, change expiry date, view contents of the ArrayList, etc.
 */

public class Fridge implements Writable{

    private int maxSize;
    private int size;

    private ArrayList<Food> fridge;

    public Fridge() {
        maxSize = 60;
        size = 0;
        fridge = new ArrayList<>(maxSize);
    }

    /*
     * REQUIRE: valid food obejct
     * MODIFIES: this
     * EFFECTS: add item to fridgeArray if there is enough space, returns true if succesful, false if not.
     */
    public boolean addItem(Food food) {
        if (food.getQuantity() > getRemainingSpace()) {
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
     * REQUIRES: valid input for name nad quantity
     * MODIFIES: this 
     * EFFECTS: checks that the name is a valid item in the fridge. checks that the quantity to remove is
     * valid (eg. not more than the number of that item), returns false if it is. calls the decreaseQuantity 
     * method if valid quantity
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

    //REQUIRES: valid input for name and days
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
     * REQUIRES: valid item name
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
     * REQUIRES: valid food object in fridge and quantity >0
     * MODIFIES: this
     * EFFECTS: decreases the quantity variable of a certain food
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

    /*
     * REQUIRES: name of valid food in fridge and quantity >0
     * MODIFIES: this
     * EFFECTS: decreases the quantity variable of a certain food
     */
    // public void decreaseQuantity(String name, int quantity) {
    //     Food food = fridgeContains(name);
    //     if (food.getQuantity() >= quantity) {
    //         food.setQuantity(food.getQuantity() - quantity);
    //         size -= quantity;

    //         if (food.getQuantity() == 0) {
    //             fridge.remove(food);
    //         }
    //     } //throw exception?    
    // }

    public ArrayList<Food> getFridgeContents() {
        return fridge;
    }

    public int getSize() {
        return size;
    }

    public int getRemainingSpace() {
        return maxSize - size;
    }

	@Override
	public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Fridge", foodToJson());
        return json;
	}

    private JSONArray foodToJson() {
    	JSONArray jsonArray = new JSONArray();

        for (Food f : fridge) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }

}
