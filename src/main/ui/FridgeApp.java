package ui;

import model.Fridge;

public class FridgeApp {

    public FridgeApp() {
        Fridge fridge = new Fridge();
        printWelcome();
    }

    // EFFECTS: runs the fridge application
    private void runFridge() {
        //stub
    }

    //EFFECTS: Prints welcome message
    private void printWelcome() {
        System.out.println("Welcome to your Fridge!");
    }

    //EFFECTS: displays options to user
    private void display() {
        //stub
    }

    //EFFECTS: carries out the user's choice from display
    private void excecuteOptions(){
        //stub
    }

    //EFFECTS: creates food item
    private void createFood() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: adds item to fridge
    private void doAddItem() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: removes item from fridge
    private void doRemoveItem() {
        //stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes expiry date
     */
    private void ChangeExpiryDate() {
        //stub
    }

    //EFFECTS: prints contents of fridge 
    private void viewFridge() {
        //stub
    }
     
}
