package ui;

import model.Food;
import model.Fridge;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FridgeApp {

    private Scanner input;
    private Fridge fridgeFoods;
    private Fridge freezerFoods;
    private String name;
    String userInput;
    private int expiry;
    private int quantity;

    
    private boolean open = true;

    public FridgeApp() {
        fridgeFoods = new Fridge();
        freezerFoods = new Fridge();
        input = new Scanner(System.in);
        runFridge();
    }

    //MODIFIES: this
    // EFFECTS: runs the fridge application and takes user input
    private void runFridge() {

        printWelcome();

        while (open) {
            display();
            userInput = input.nextLine();
            excecuteOptions(userInput);
        }
    }

    //EFFECTS: Prints welcome message
    private void printWelcome() {
        System.out.println("Welcome to your Fridge!");
    }

    //EFFECTS: prints goodbye message
    private void printGoodbye() {
        System.err.println("Fridge closed!");
    }

    //EFFECTS: displays options to user
    private void display() {
        System.out.println("\n\nOptions: ");
        System.out.println("a)\t Add Item");
        System.out.println("r)\t Remove Item");
        System.out.println("c)\t Change Item Expiry Date");
        System.out.println("v)\t View Fridge");
        System.out.println("q)\t Close Fridge");
    }

    //REQUIRES: expected string output
    //EFFECTS: carries out the user's choice from display
    private void excecuteOptions(String input) {
        if (input.equalsIgnoreCase("a")) {
            doAddItem(createFood());
        } else if (input.equalsIgnoreCase("r")) {
            doRemoveItem();
        } else if (input.equalsIgnoreCase("c")) {
            changeExpiryDate();
        } else if (input.equalsIgnoreCase("v")) {
            viewFridge();
        } else if (input.equalsIgnoreCase("q")) {
            open = false;
            printGoodbye();
        }
    }

    //EFFECTS: creates food item
    private Food createFood() {
        while (true) {
        try {
            System.out.println("What is the foods name?: ");
            name = input.nextLine();
            System.out.println("How many of this item?: ");
            quantity = input.nextInt();
            System.out.println("In how many days does this item expire?: ");
            expiry = input.nextInt();
            input.nextLine();
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again.");
            }
        } 
        Food food = new Food(name, quantity, expiry);
        return food;
    }

    /*
     * REQUIRES: valid parameters
     * MODIFIES: this
     * EFFECTS: creates new food item and stores it in freezerFoods array
     */
    private Food createFreezerItem(String name, int quantity, int expiry) {
        Food f = new Food(name, quantity, expiry);
        return f;
        //stub
    }

    /*
     * REQUIRES: valid parameters
     * MODIFIES: this
     * EFFECTS: creates new fruit item and stores it in fridgeFoods array
     */
    private Food createFruit(String name, int quantity, int expiry, boolean ripe) {
        Food f = new Food(name, quantity, expiry);
        return f;
        //stub
    }

    //MODIFIES: this
    //EFFECTS: adds item to fridge
    private void doAddItem(Food food) {
        if (fridgeFoods.addItem(food)) {
            System.out.println("Item added! \t Remaining space in fridge: " + fridgeFoods.getRemainingSpace());
        } else {
            System.out.println("Not enough space in fridge!");
        }
    }

    //MODIFIES: this
    //EFFECTS: removes item from fridge
    private void doRemoveItem() {
        System.out.println("What item would you like to remove?: ");
        viewFridge();
        name = input.nextLine();

        Food temp = fridgeFoods.fridgeContains(name);

        if (temp != null) {
            System.out.println("How many of this item would you like to remove?: ");
            System.out.println("Current item quantity: " + temp.getQuantity());
            try {
                quantity = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Try again.");
            }
            fridgeFoods.decreaseQuantity(name, quantity);
        } else {
            System.out.println("No such item in fridge.");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes expiry date
     */
    private void changeExpiryDate() {
        System.out.println("What item would you like to change?: ");
        viewFridge();
        name = input.nextLine();

        System.out.println("Increase or decrease number of days until expiry? (I/D): ");

        while (true) {
            String answer = input.nextLine();
            if ((answer.equalsIgnoreCase("i"))) {
                System.out.println("Increase by how many days?: ");
                expiry = input.nextInt();
                fridgeFoods.changeExpiryDate(name, expiry);
                break;
            } else if (answer.equalsIgnoreCase("d")) {
                System.out.println("Decrease by how many days?: ");
                expiry = input.nextInt() * -1;
                fridgeFoods.changeExpiryDate(name, expiry);
                break;        
            } else {
                System.out.println("Invalid Input. Try again. (I/D)");
            }
        }
    }

    /*
     * EFFECTS: displays foods with an expiry date of less than 3 days
     */
    public void displaySoonExpired() {

    }

    //EFFECTS: prints contents of fridge 
    private void viewFridge() {
        System.out.print(fridgeFoods.getFridgeContents());
    }
     
}
