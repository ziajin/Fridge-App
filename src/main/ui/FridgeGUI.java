package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.event.*;
import java.io.IOException;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * FridgeGUI class creates a Swing GUI that handles the user input and displays options in a 
 * window. Allows user to interact with the digital fridge.
 */

 @ExcludeFromJacocoGeneratedReport

public class FridgeGUI extends JFrame {

    private Fridge fridge;
    private Fridge freezer;
    private JTextArea fridgeContents;
    private JTextArea freezerContents;

    private String fileNameFridge = "./data/FridgeSave";
    private String fileNameFreezer = "./data/FreezerSave";

    private final int WIDTH = 500;
    private final int HEIGHT = 700;


    public FridgeGUI() {
        super("FridgeApp");

        fridge = new Fridge();
        freezer = new Fridge();

        setTitle("Fridge App");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        mainMenu();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                save();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: sets main menu before fridge interface. prompts user to load saved fridge or not
    private void mainMenu() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Fridge App", JLabel.CENTER);
        welcome.setFont(new Font("Roboto Condensed", Font.PLAIN, 30));

        JButton start = new JButton("Start");
        start.addActionListener(e -> start());

        main.add(welcome);
        main.add(start, BorderLayout.SOUTH);

        setContentPane(main);
        revalidate();
        repaint();
    }

    private void start() { 
        int load = JOptionPane.showConfirmDialog(this, "Load previously saved fridge?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (load == JOptionPane.YES_OPTION) {
            loadFridge();
        } 
        
        setUp();
    }

    //EFFECTS: sets up and assigns actions to buttons
    private JPanel setButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(e -> addItem());

            JButton removeButton = new JButton("Remove Item");
        removeButton.addActionListener(e -> removeItem());

        JButton viewFridgeButton = new JButton("View Fridge");
        viewFridgeButton.addActionListener(e -> viewFridge());

        JButton viewFreezerButton = new JButton("View Freezer");
        viewFreezerButton.addActionListener(e -> viewFreezer());

        buttonPanel.add(viewFreezerButton);
        buttonPanel.add(viewFridgeButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        return buttonPanel;
    }

    //EFFECTS: sets up text area to display fridge contents
    private JScrollPane setFridgeDisplay() {
        fridgeContents = new JTextArea();
        fridgeContents.setEditable(false);
        JScrollPane scroll = new JScrollPane(fridgeContents);

        return scroll;
    }

    private void setUp() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(setButtons(), BorderLayout.NORTH);

        JTabbedPane tab = new JTabbedPane();
        tab.add("Fridge", setFridgeDisplay());
        tab.add("Freezer", setFreezerDisplay());
        panel.add(tab, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void save() {
        int save = JOptionPane.showConfirmDialog(this, "Save fridge?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (save == JOptionPane.YES_OPTION) {
            writeFridge();
            JOptionPane.showMessageDialog(this, "Saved!");
            System.exit(0);
            SwingUtilities.invokeLater(() -> System.exit(0));
        } else {
            System.exit(0);
        }
    }

    //EFFECTS: sets up text area to display freezer contents
    private JScrollPane setFreezerDisplay() {
        freezerContents = new JTextArea();
        freezerContents.setEditable(false);
        JScrollPane scroll = new JScrollPane(freezerContents);

        return scroll;
    }

    //MODIFIES: this
    //EFFECTS: prompts user for values, creates food, adds to fridge
    private void addItem() {
        String name = JOptionPane.showInputDialog("Whats the food name?: ");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("How many of this item?: "));
        int expiry = Integer.parseInt(JOptionPane.showInputDialog("In how many days does this item expire?: "));

        fridge.addItem(new Food(name, quantity, expiry));
        JOptionPane.showMessageDialog(this, "Added!");
    }

    //MODIFIES: this
    //EFFECTS: removes specified qauntity from specified item in fridge. if all quantities of the 
    // item is removed, remove the item entirely.
    private void removeItem() {
        String name = JOptionPane.showInputDialog("What item would you like to remove?: ");

        viewFridge();

        Food temp = fridge.fridgeContains(name);

        if (temp != null) {

            String msg = "How many of this item would you like to remove?\n" + "Current: " + temp.getQuantity();
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(msg));
            fridge.decreaseQuantity(temp, quantity);

        } else {
            JOptionPane.showMessageDialog(this, "Item not found.");
        }

    }

    //EFFECTS: prints out contents of fridge onto panel
    private void viewFridge() {
        fridgeContents.setText("");
        fridgeContents.append("Fridge Contents: \n");


        for (Food f : fridge.getFridgeContents()) {

            String name = f.getName();
            int quantity = f.getQuantity();
            int expiry = f.getExpiryDate();

            fridgeContents.append("Name: " + name + " Quantity: " + quantity + " Expiry Date: " + expiry + "\n");
        }
    }

    // EFFECTS: prints out contents of freezer onto panel
    private void viewFreezer() {
        freezerContents.setText("");
        freezerContents.append("Freezer Contents: \n");

        for (Food f : freezer.getFridgeContents()) {

            String name = f.getName();
            int quantity = f.getQuantity();
            int expiry = f.getExpiryDate();

            freezerContents.append("Name: " + name + " Quantity: " + quantity + " Expiry Date: " + expiry + "\n");
        }

    }

    //MODIFIES: this
    //EFFECTS: changes item's expiry date
    private void changeExpiryDate() {

    }

    //MODIFIES: this
    //EFFECTS: load the saved fridge from file
    private void loadFridge() {
        try {

            JsonReader in = new JsonReader(fileNameFridge);
            fridge = in.read();

            in = new JsonReader(fileNameFreezer);
            freezer = in.read();

        } catch (IOException e) {
            System.out.println("Error occurred during loading.");
        }
    }

    private void writeFridge() {
        try {

            JsonWriter write = new JsonWriter(fileNameFridge);
            write.writeOn();
            write.writeToFile(fridge);

            write = new JsonWriter(fileNameFreezer);
            write.writeToFile(freezer);

            write.close();

            System.out.println("Succesfully saved!");

        } catch (IOException e) {
            System.out.println("Ran into an issue while saving.");
        }
    }
}
