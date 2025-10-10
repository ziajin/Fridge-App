package model;

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
     * EFFECTS: changes frozen boolean value to false
     */
    public void thaw(Food f) {
        //stub
    }

    /*
     * REQUIRES: valid thawed food item
     * MODIFIES: this
     * EFFECTS: changes frozen boolean value to true
     */
    public void refreeze(Food f) {
        //stub
    }

    /*
     * EFFECTS: returns string representation of the frozen object
     */
    public String toString() {
        //stub
    }


}
