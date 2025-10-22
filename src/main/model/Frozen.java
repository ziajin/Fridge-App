package model;

import org.json.JSONObject;

import netscape.javascript.JSObject;

/*
 * represents a food type that is frozen, inheriting properties such as name, quantity
 * and expiry date from the Food superclass. The frozen class tracks whether the food is 
 * frozen or thawed.
 */
public class Frozen extends Food {

    boolean frozen;

    /*
     * REQUIRES: valid name (0<length), quantity > 0, expiry date > 0
     * MODIFIES: this
     * EFFECTS: creates a frozen item with the values name, quantity, expiry, and
     * frozen state
     */
    public Frozen(String name, int quantity, int expiryDate, boolean frozen) {
        super(name, quantity, expiryDate);
        expiryDate = 100;
        this.frozen = frozen;
    }

    /*
     * REQUIRES: valid frozen food item
     * MODIFIES: this
     * EFFECTS: changes frozen boolean value to false and decrements the expiry date
     */
    public void thaw(Food f) {
        if (frozen) {
            frozen = false;
            expiryDate--;
        }
    }

    /*
     * REQUIRES: valid thawed food item
     * MODIFIES: this
     * EFFECTS: changes frozen boolean value to true
     */
    public void refreeze(Food f) {
        if (!frozen) {
            frozen = true;
        }
    }

    public boolean getFrozen() {
        return frozen;
    }

    /*
     * EFFECTS: returns string representation of the frozen object
     */
    public String toString() {
        String name = "Name: " + getName();
        String quantity = "\tQuantity: " + getQuantity();
        String expiry = "\tExpiry Date: " + getExpiryDate();
        String frozen = " Frozen: " + getFrozen() + "\n";
        return name + quantity + expiry + frozen;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("Frozen", getFrozen());
        return json;
    }

}
