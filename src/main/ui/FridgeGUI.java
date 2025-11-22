package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

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

    private static final int WIDTH = 500;
    private static final int HEIGHT = 700;

    private Image backgroundInside;
    private Image backgroundOutside;


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
            @ExcludeFromJacocoGeneratedReport
            @Override
            public void windowClosing(WindowEvent e) {
                save();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: sets main menu before fridge interface. prompts user to load saved fridge or not
    private void mainMenu() {
        JLabel background = new JLabel(getImage(("data\\double-door-refrigerator-icon-in-gray-color-vector.jpg")));
        background.setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Fridge App", JLabel.CENTER);
        welcome.setFont(new Font("Cooper Black", Font.BOLD, 40));

        JButton start = new JButton("Start");
        start.setBackground(new Color(173, 216, 230));
        start.setFont(new Font("Cooper Black", Font.BOLD, 30));
        start.setAlignmentX(CENTER_ALIGNMENT);
        start.addActionListener(e -> start());

        JPanel startPanel = new JPanel();
        startPanel.setOpaque(false);
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.add((Box.createVerticalStrut(400)));
        startPanel.add(start);

        background.add(welcome, BorderLayout.NORTH);
        background.add(startPanel, BorderLayout.CENTER);

        JPanel main = new JPanel();
        main.add(background);

        setContentPane(main);
        revalidate();
        repaint();
    }

    //MODIFIES: helper method to get image icon from file path
    private ImageIcon getImage(String file) {
        ImageIcon backgroundImage = new ImageIcon("data\\double-door-refrigerator-icon-in-gray-color-vector.jpg");
        backgroundOutside = backgroundImage.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon scaledBackground = new ImageIcon(backgroundOutside);
        return scaledBackground;
    }

    //EFFECTS: popup ask if user wants to load saved fridge
    private void start() { 
        String msg = "Load previously saved fridge?";
        int load = JOptionPane.showConfirmDialog(this, msg, "Load", JOptionPane.YES_NO_OPTION);

        if (load == JOptionPane.YES_OPTION) {
            loadFridge();
        } 
        
        setUp();
    }

    //EFFECTS: sets up and assigns actions to buttons
    private JPanel setButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Food");
        addButton.addActionListener(e -> addItem());

        JButton addFrozenButton = new JButton("Add Frozen Food");
        addFrozenButton.addActionListener(e -> addItemFreezer());

        JButton removeButton = new JButton("Remove Item");
        removeButton.addActionListener(e -> removeItem());

        JButton changeExpiry = new JButton("Change Expiry");
        changeExpiry.addActionListener(e -> changeExpiryDate());

        // buttonPanel.add(viewFreezerButton);
        // buttonPanel.add(viewFridgeButton);
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);
        buttonPanel.add(addFrozenButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(changeExpiry);

        return buttonPanel;
    }

    //EFFECTS: sets up text area to display fridge contents
    private JScrollPane setFridgeDisplay() {
        fridgeContents = new JTextArea();
        viewFridge();
        fridgeContents.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fridgeContents.setLineWrap(true);
        fridgeContents.setWrapStyleWord(true);
        fridgeContents.setEditable(false);
        fridgeContents.setOpaque(false);
        fridgeContents.setBackground(new Color(0, 0, 0, 0));

        JScrollPane scroll = new JScrollPane(fridgeContents);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);

        return scroll;
    }


    //EFFECTS: helper method to override paintComponent and setup background img
    private JPanel setPanel(Image img, JScrollPane scroll) {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setOpaque(false);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    //EFFECTS: sets up panel with fridge and freezer display along with displaying
    // the buttons panel. updates the main menu panel to the fridge panel.
    private void setUp() {
        ImageIcon fridgeInside = new ImageIcon("data\\inside.png");
        backgroundInside = fridgeInside.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

        JPanel background1 = setPanel(backgroundInside, setFridgeDisplay());
        JPanel background2 = setPanel(backgroundInside, setFreezerDisplay());

        JTabbedPane tab = new JTabbedPane();
        tab.addTab("Fridge", background1);
        tab.addTab("Freezer", background2);

        for (int i = 0; i < tab.getTabCount(); i++) {
            Component c = tab.getComponentAt(i);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent) c;
                jc.setOpaque(false);
                jc.setBackground(new Color(0,0,0,0));
            }
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(setButtons(), BorderLayout.NORTH);
        panel.add(tab, BorderLayout.CENTER);

        setContentPane(panel);
        revalidate();
        repaint();
        setVisible(true);   
    }

    //EFFEECTS: asks user whether or not to save fridge. if yes write fridge to file
    // else discards. exits either way.
    private void save() {
        int save = JOptionPane.showConfirmDialog(this, "Save fridge?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (save == JOptionPane.YES_OPTION) {
            writeFridge();
        }

        dispose();
        System.exit(0);
    }

    //EFFECTS: sets up text area to display freezer contents
    private JScrollPane setFreezerDisplay() {
        freezerContents = new JTextArea();
        viewFreezer();

        freezerContents.setEditable(false);
        freezerContents.setOpaque(false);
        freezerContents.setBackground(new Color(0, 0, 0, 0));

        JScrollPane scroll = new JScrollPane(freezerContents);

        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);

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

        viewFridge();
    }

    //MODIFIES: this
    //EFFECTS: prompts user for values, creates food, adds to fridge
    private void addItemFreezer() {
        boolean frozen = false;

        String name = JOptionPane.showInputDialog("Whats the food name?: ");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("How many of this item?: "));
        int expiry = Integer.parseInt(JOptionPane.showInputDialog("In how many days does this item expire?: "));

        int result = JOptionPane.showConfirmDialog(this, "Is this item frozen?");

        if (result == JOptionPane.YES_OPTION) {
            frozen = true;
        }

        freezer.addItem(new Frozen(name, quantity, expiry, frozen));
        JOptionPane.showMessageDialog(this, "Added!");

        viewFreezer();
    }

    //MODIFIES: this
    //EFFECTS: removes specified qauntity from specified item in fridge. if all quantities of the 
    // item is removed, remove the item entirely.
    private void removeItem() {
        String[] options = {"Fridge", "Freezer"};
        String m = "Where is the item you want to remove?";
        String t = "Remove Item";

        int choice = JOptionPane.showOptionDialog(
                this, m, t, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String name = JOptionPane.showInputDialog("What item would you like to remove?: ");

        if (choice == 0) {
            removeFridge(name);
        } else if (choice == 1) {
            removeFreezer(name);
        }

    }

    private void removeFridge(String name) {
        Food temp = fridge.fridgeContains(name);

        if (temp != null) {

            String msg = "How many of this item would you like to remove?\n" + "Current: " + temp.getQuantity();
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(msg));
            fridge.decreaseQuantity(temp, quantity);
            JOptionPane.showMessageDialog(this, "Removed: " + quantity + " of name");
            viewFridge();

        } else {
            JOptionPane.showMessageDialog(this, "Item not found.");
        }
    }

    private void removeFreezer(String name) {
        Food temp = freezer.fridgeContains(name);

        if (temp != null) {

            String msg = "How many of this item would you like to remove?\n" + "Current: " + temp.getQuantity();
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(msg));
            freezer.decreaseQuantity(temp, quantity);
            JOptionPane.showMessageDialog(this, "Removed: " + quantity + " of name");
            viewFreezer();

        } else {
            JOptionPane.showMessageDialog(this, "Item not found.");
        }
    }


    //EFFECTS: prints out contents of fridge onto panel
    private void viewFridge() {
        fridgeContents.setText("");
        fridgeContents.append("\n\n     Fridge Contents: \n");

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
        String name = JOptionPane.showInputDialog("What item would you like to change?: ");

        if (fridge.fridgeContains(name) != null) {

            String[] options = {"Increase", "Decrease"};
            String m = "Increase or decrease number of days until expiry?";
            String t = "Change Expiry Date";
            int choice = JOptionPane.showOptionDialog(
                    this, m, t, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                int days = Integer.parseInt(JOptionPane.showInputDialog("Change by how many days?: "));
                if (fridge.changeExpiryDate(name, days)) {
                    JOptionPane.showMessageDialog(this, "Date changed succesfully!");
                }

            } else if (choice == 1) {
                int days = Integer.parseInt(JOptionPane.showInputDialog("Change by how many days?: "));
                if (fridge.changeExpiryDate(name, -days)) {
                    JOptionPane.showMessageDialog(this, "Date changed succesfully!");
                }
            }

            viewFridge();

        } else {
            JOptionPane.showMessageDialog(this, "Item not found.");
        }
         
    }


    //MODIFIES: this
    //EFFECTS: load the saved fridge and freezer from file
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


    //EFFECTS: writes fridge and freezer to file
    private void writeFridge() {
        try {

            JsonWriter write = new JsonWriter(fileNameFridge);
            write.writeOn();
            write.writeToFile(fridge);
            write.close();

            JsonWriter write2 = new JsonWriter(fileNameFreezer);
            write2.writeOn();
            write2.writeToFile(freezer);
            write2.close();

        } catch (IOException e) {
            System.out.println("Ran into an issue while saving.");
        }
    }
}
