package model;

/*
 * represents a food object that is of a fuit variety, inheriting all methods form the
 * food superclass with addtional methods determing if the fruit is ripe. 
 */

public class Fruit extends Food {

    private boolean ripe;

    public Fruit(String name, int quantity, int expiryDate, boolean ripe) {
        super(name, quantity, expiryDate);
        this.ripe = ripe;
        determineExpiry(ripe);
    }

    public boolean isRipe() {
        return ripe;
    }

    public void setRipe(boolean ripe) {
        this.ripe = ripe;
        determineExpiry(ripe);
    }

    /*
     * REQUIRES: valid parameter value
     * MODIFIES: this
     * EFFECTS: if the fruit is ripe, return 3 days, if not return 6 days
     */
    private void determineExpiry(boolean ripe) {
        if (ripe) {
            setExpiryDate(2);
        } else {
            setExpiryDate(5);
        }
    }

}
