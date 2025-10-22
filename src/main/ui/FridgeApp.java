package ui;

import model.Food;
import model.Fridge;
import model.Frozen;
import model.Fruit;

import java.util.InputMismatchException;
import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * FridgeApp class handles the user input and displays options on the console. 
 * The fridge app allows the user to interact with the fridge class and modify it
 * as wanted.
 */

@ExcludeFromJacocoGeneratedReport
public class FridgeApp {

    private Scanner input;
    private Fridge fridgeFoods;
    private Fridge freezerFoods;
    private String name;
    private String userInput;

    private int expiry;
    private int quantity;
    private boolean ripe;

    
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
        System.out.println("\nOptions: ");
        System.out.println("a)\t Add Item");
        System.out.println("r)\t Remove Item");
        System.out.println("c)\t Change Item Expiry Date");
        System.out.println("v)\t View Fridge");
        System.out.println("v2)\t View Freezer");
        System.out.println("v3)\t View Soon to Expire");
        System.out.println("q)\t Close Fridge\n");
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
            System.out.println();
        } else if (input.equalsIgnoreCase("v2")) {
            viewFreezer();
        } else if (input.equalsIgnoreCase("v3")) {
            displaySoonExpired();
        } else if (input.equalsIgnoreCase("q")) {
            open = false;
            printGoodbye();
        }
    }

    //EFFECTS: creates food item
    private Food createFood() {
        System.out.println("What category is this food?");
        System.out.println("1) General Food");
        System.out.println("2) Frozen Food");
        System.out.println("3) Fruit/Veg");
        userInput = input.nextLine();
        if (userInput.equalsIgnoreCase("1")) {
            return getParamters();
        } else if (userInput.equalsIgnoreCase("2")) {
            return getParametersFrozen();
        } else if (userInput.equalsIgnoreCase("3")) {
            return getFruitParameters();
        } else {
            return null;
        }
    }

    /*
     * REQUIRES: valid input from the user, name not empty, quantity > 0, expiry date > 0
     * MODIFIES: this
     * EFFECTS: takes user input to create a valid food object
     */
    // private Food getParametersFood() {
    //     while (true) {
    //         try {
    //             System.out.println("What is the foods name?: ");
    //             name = input.nextLine();
    //                 if (name.isBlank()) {
    //                     System.out.println("Name is empty. Try again. \n");
    //                     continue;
    //                 }
    //             System.out.println("How many of this item?: ");
    //             quantity = input.();
    //                 if (quantity <= 0) {
    //                     System.out.println("Quantity cannot be zero. Try again. \n");
    //                     input.nextLine();
    //                     continue;
    //                 }
    //             System.out.println("In how many days does this item expire?: ");
    //             expiry = input.nextInt();
    //                 if (expiry < 0) {
    //                     System.out.println("Expiry days cannot be negative. Try again. \n");
    //                     continue;
    //                 }
    //             input.nextLine();
    //             break;
    //     } catch (InputMismatchException e) {
    //         System.out.println("Invalid input. Try again.");
    //         }
    //     }
    // }

    private Food getParamters() {
        while (true) {
            try {
                System.out.println("What is the foods name?: ");
                name = input.nextLine();
                System.out.println("How many of this item?: ");
                quantity = Integer.parseInt(input.nextLine());
                System.out.println("In how many days does this item expire?: ");
                expiry = Integer.parseInt(input.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.\n");
            }
        }
        Food f = new Food(name, quantity, expiry);
        return f;
    }

    /*
     * REQUIRES: valid input from the user, name not empty, quantity > 0, expiry date > 0
     * MODIFIES: this
     * EFFECTS: takes user input to create a valid frozen object
     */
    private Food getParametersFrozen() {
        while (true) {
            try {
                System.out.println("What is the foods name?: ");
                name = input.nextLine();
                System.out.println("How many of this item?: ");
                quantity = Integer.parseInt(input.nextLine());
                System.out.println("In how many days does this item expire?: ");
                expiry = Integer.parseInt(input.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.\n");
            }
        }
        Food f = new Frozen(name, quantity, expiry, true);
        return f;
    }

    /*
     * REQUIRES: valid parameters
     * MODIFIES: this
     * EFFECTS: creates new fruit item and stores it in fridgeFoods array
     */
    private Food getFruitParameters() {
        while (true) {
            try {
                System.out.println("What is the foods name?: ");
                name = input.nextLine();
                System.out.println("How many of this item?: ");
                quantity = Integer.parseInt(input.nextLine());
                System.out.println("In how many days does this item expire?: ");
                expiry = Integer.parseInt(input.nextLine());
                System.out.println("Is this item ripe? (Y/N): ");
                userInput = input.nextLine();
                if (userInput.equalsIgnoreCase("y")) {
                    ripe = true;
                } else {
                    ripe = false;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.\n");
            }
        }
        Food f = new Fruit(name, quantity, expiry, ripe);
        return f;
    }

    //MODIFIES: this
    //EFFECTS: adds item to fridge if it is a food object, adds to fridge if its a fruit, adds to freezer array
    //if it is a frozen food. prints to console error message if food is not added succesfully.
    private void doAddItem(Food food) {
        boolean added = false;
        if (food instanceof Frozen) {
            added = freezerFoods.addItem(food);
            if (added) {
                System.out.println("Item added! \t Remaining space in fridge: " + freezerFoods.getRemainingSpace());
            }
        } else {
            added = fridgeFoods.addItem(food);
            if (added) {
                System.out.println("Item added! \t Remaining space in fridge: " + fridgeFoods.getRemainingSpace());
            }
        }
        if (!added) {
            System.out.println("Not enough space in the fridge, please remove something.");
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
                quantity = Integer.parseInt(input.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Try again.");
            }
            fridgeFoods.decreaseQuantity(temp, quantity);
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
                expiry = Integer.parseInt(input.nextLine());
                fridgeFoods.changeExpiryDate(name, expiry);
                break;
            } else if (answer.equalsIgnoreCase("d")) {
                System.out.println("Decrease by how many days?: ");
                expiry = Integer.parseInt(input.nextLine());
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
        for (Food f : fridgeFoods.getFridgeContents()) {
            if (f.getExpiryDate() < 2) {
                System.out.println(f);
            }
        }
    }

    //EFFECTS: prints contents of fridge 
    private void viewFridge() {
        System.out.print(fridgeFoods.getFridgeContents());
    }

    //EFFECTS: prints contents of fridge 
    private void viewFreezer() {
        System.out.print(freezerFoods.getFridgeContents());
    }

    //EFFECTS: prints out fridge contents but only fruit
    // private void viewFruit() {
    //     for (Food f : fridgeFoods.getFridgeContents()) {
    //         if (f instanceof Fruit) {
    //             System.out.println(f);
    //         }
    //     }
    // }
     
}
