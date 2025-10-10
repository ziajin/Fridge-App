package model;

/*
 * represents a general food item with properties such as name, quantity, and the
 * days until the expiry. provides basic food methods for its subclasses (Frozen, Fruit) such as getters
 * and setters for the variables and a toString.
 */

public class Food {

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

    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS: prints summary of item
    public String toString() {
        return "Name: " + getName() + "\tQuantity: " + getQuantity() + "\tExpiry Date: " + getExpiryDate() + "\n";
    }
}
