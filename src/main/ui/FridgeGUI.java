package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Event;
import model.EventLog;

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
    private JTextPane fridgeContents;
    private JTextPane freezerContents;

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
        welcome.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));

        JButton start = new JButton("Start");
        start.setBackground(new Color(173, 216, 230));
        start.setFont(new Font("Cooper Black", Font.BOLD, 30));
        start.setAlignmentX(CENTER_ALIGNMENT);
        start.addActionListener(e -> start());

        JPanel startPanel = new JPanel();
        startPanel.setOpaque(false);
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.add((Box.createVerticalStrut(200)));
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


    //EFFECTS: popup ask if user wants to load saved fridge, if yes then call loadFridge() and setUp() 
    // else just call setUp()
    private void start() { 
        String msg = "Load previously saved fridge?";
        int load = JOptionPane.showConfirmDialog(this, msg, "Load", JOptionPane.YES_NO_OPTION);

        if (load == JOptionPane.YES_OPTION) {
            loadFridge();
        } 
        
        setUp();
    }

    //MODIFIES: this
    //EFFECTS: sets up and assigns actions to buttons
    private JPanel setButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Food");
        addButton.addActionListener(e -> addItemFridge());

        JButton addFrozenButton = new JButton("Add Frozen Food");
        addFrozenButton.addActionListener(e -> addItemFreezer());

        JButton removeButton = new JButton("Remove Item");
        removeButton.addActionListener(e -> removeItem());

        JButton changeExpiry = new JButton("Change Expiry");
        changeExpiry.addActionListener(e -> changeExpiryDate());

        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);
        buttonPanel.add(addFrozenButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(changeExpiry);

        return buttonPanel;
    }

    //EFFECTS: sets up text pane to display fridge contents and styles the text so 
    //it is centered and the background is shown
    private JScrollPane setFridgeDisplay() {
        fridgeContents = new JTextPane();
        fridgeContents.setEditable(false);
        fridgeContents.setOpaque(false);
        fridgeContents.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        StyledDocument doc = fridgeContents.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(20, doc.getLength(), center, false);

        fridgeContents.setText(viewFridge());

        JScrollPane scroll = new JScrollPane(fridgeContents);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);

        return scroll;
    }

    //EFFECTS: sets up text pane to display freezer contents and styles the text so 
    //it is centered and the background is shown
    private JScrollPane setFreezerDisplay() {
        freezerContents = new JTextPane();
        freezerContents.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        freezerContents.setEditable(false);
        freezerContents.setOpaque(false);

        StyledDocument doc = freezerContents.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(20, doc.getLength(), center, false);
        
        freezerContents.setText(viewFreezer());

        JScrollPane scroll = new JScrollPane(freezerContents);

        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);

        return scroll;
    }

    //MODIFIES: fridgeContents
    //EFFECTS: updates fridgeContents so the text pane accurately reflects items
    private void updateFridge() {
        fridgeContents.setText(viewFridge());

        StyledDocument doc = fridgeContents.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(20, doc.getLength(), center, false);
    }
    
    //MODIFIES: fridgeContents
    //EFFECTS: updates fridgeContents so the text pane accurately reflects items
    private void updateFreezer() {
        freezerContents.setText(viewFreezer());

        StyledDocument doc = freezerContents.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(20, doc.getLength(), center, false);
    }


    //MODIFIES: this
    //EFFECTS: helper method to override paintComponent and setup background img
    private JPanel setPanel(Image img, JScrollPane scroll) {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            @ExcludeFromJacocoGeneratedReport
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setOpaque(false);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    //MODIFIES: this
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

        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }

        dispose();
        System.exit(0);
    }

    //MODIFIES: fridge
    //EFFECTS: prompts user for values, creates food, adds to fridge
    private void addItemFridge() {
        String name = JOptionPane.showInputDialog("Whats the food name?: ");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("How many of this item?: "));
        int expiry = Integer.parseInt(JOptionPane.showInputDialog("In how many days does this item expire?: "));

        fridge.addItem(new Food(name, quantity, expiry));
        JOptionPane.showMessageDialog(this, "Added!");

        updateFridge();
    }

    //MODIFIES: freezer
    //EFFECTS: prompts user for values, creates food, adds to freezer
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

        updateFreezer();
    }


    /*
     * EFFECTS: asks the user where the item is and what item is to be removed. 
     * calls the respective method and passes in the item name
     */
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

    //REQUIRES: valid name of an item within the fridge
    //MODIFIES: fridge
    //EFFECTS: finds the item within the fridge by name and removes the quantity of the item specified by the user
    //prints removed if succesful and item not found if not
    private void removeFridge(String name) {
        Food temp = fridge.fridgeContains(name);

        if (temp != null) {

            String msg = "How many of this item would you like to remove?\n" + "Current: " + temp.getQuantity();
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(msg));
            fridge.decreaseQuantity(temp, quantity);
            JOptionPane.showMessageDialog(this, "Removed: " + quantity + " of name");
            updateFridge();

        } else {
            JOptionPane.showMessageDialog(this, "Item not found.");
        }
    }

    //REQUIRES: valid name of an item within the freezer
    //MODIFIES: freezer
    //EFFECTS: finds the item within the freezer by name and removes the quantity of the item specified by the user
    //prints removed if succesful and item not found if not
    private void removeFreezer(String name) {
        Food temp = freezer.fridgeContains(name);

        if (temp != null) {

            String msg = "How many of this item would you like to remove?\n" + "Current: " + temp.getQuantity();
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(msg));
            freezer.decreaseQuantity(temp, quantity);
            JOptionPane.showMessageDialog(this, "Removed: " + quantity + " of name");
            updateFreezer();

        } else {
            JOptionPane.showMessageDialog(this, "Item not found.");
        }
    }


    // EFFECTS: gets a string representation of the contents in the freezer
    private String viewFridge() {
        StringBuilder contents = new StringBuilder();
        contents.append("\n\n     Fridge Contents: \n");

        for (Food f : fridge.getFridgeContents()) {
            String name = f.getName();
            int quantity = f.getQuantity();
            int expiry = f.getExpiryDate();
            contents.append("Name: " + name + "    Quantity: " + quantity + "    Expiry Date: " + expiry + "\n");
        }
        return contents.toString();
    }


    // EFFECTS: gets a string representation of the contents in the freezer
    private String viewFreezer() {
        StringBuilder s = new StringBuilder();
        s.append("\n\n      Freezer Contents: \n");

        for (Food f : freezer.getFridgeContents()) {

            String name = f.getName();
            int quantity = f.getQuantity();
            int expiry = f.getExpiryDate();

            s.append("Name: " + name + "    Quantity: " + quantity + "    Expiry Date: " + expiry + "\n");
        }

        return s.toString();

    }


    //MODIFIES: fridge
    //EFFECTS: changes item's expiry date
    private void changeExpiryDate() {
        String[] options = {"Fridge", "Freezer"};
        String m = "Where is the item you want to remove?";
        String t = "Change Expiry Date";

        int choice = JOptionPane.showOptionDialog(
                this, m, t, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String name = JOptionPane.showInputDialog("What item would you like to change?: ");

        if (choice == 0) {
            changeExpiryFridge(name);
        } else if (choice == 1) {
            changeExpiryFreezer(name);
        }
    }

    //MODIFIES: fridge
    //EFFECTS: updates expiry date to user's answer of the specified item
    private void changeExpiryFridge(String name) {
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
            updateFridge();
        } else {
            JOptionPane.showMessageDialog(this, "Item not found.");
        }
    }

    //MODIFIES: fridge
    //EFFECTS: updates expiry date to user's answer of the specified item
    private void changeExpiryFreezer(String name) {
        if (freezer.fridgeContains(name) != null) {

            String[] options = {"Increase", "Decrease"};
            String m = "Increase or decrease number of days until expiry?";
            String t = "Change Expiry Date";
            int choice = JOptionPane.showOptionDialog(
                    this, m, t, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                int days = Integer.parseInt(JOptionPane.showInputDialog("Change by how many days?: "));
                if (freezer.changeExpiryDate(name, days)) {
                    JOptionPane.showMessageDialog(this, "Date changed succesfully!");
                }

            } else if (choice == 1) {
                int days = Integer.parseInt(JOptionPane.showInputDialog("Change by how many days?: "));
                if (freezer.changeExpiryDate(name, -days)) {
                    JOptionPane.showMessageDialog(this, "Date changed succesfully!");
                }
            }
            updateFreezer();
        } else {
            JOptionPane.showMessageDialog(this, "Item not found.");
        }
    }


    //MODIFIES: fridge
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
