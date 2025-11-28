package model;

import org.json.JSONObject;

import persistence.Writable;

/*
 * represents a general food item with properties such as name, quantity, and the
 * days until the expiry. provides basic food methods for its subclasses (Frozen, Fruit) such as getters
 * and setters for the variables and a toString.
 */

public class Food implements Writable {

    protected int quantity;
    protected int expiryDate;
    protected String name;

    public Food(String name, int quantity, int expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(int expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    //EFFECTS: prints summary of item
    public String toString() {
        return "Name: " + getName() + "\tQuantity: " + getQuantity() + "\tExpiry Date: " + getExpiryDate() + "\n";
    }

    @Override
	public JSONObject toJson() {
	JSONObject json = new JSONObject();
        json.put("Type", "Food");
        json.put("Name", getName());
        json.put("Quantity", getQuantity());
        json.put("Expiry Date", getExpiryDate());
        return json;
    }
}
