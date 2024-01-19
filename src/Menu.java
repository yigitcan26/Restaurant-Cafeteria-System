
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Menu extends JFrame {
    private List<FoodAndDrink> items;
    private JTextArea menuTextArea;

    public Menu() {
        items = new ArrayList<>();
        initializeGUI();
    }
    public List<FoodAndDrink> getItems() {
        return items;
    }
    
    private void initializeGUI() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        menuTextArea = new JTextArea();
        menuTextArea.setEditable(false);
        JScrollPane menuScrollPane = new JScrollPane(menuTextArea);
        menuScrollPane.setPreferredSize(new Dimension(400, 300)); // Set preferred size
        
        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CafeManagementSystem.isBossScreenActive) {
                    String itemName = JOptionPane.showInputDialog("Enter item name:");
                    if (itemName != null && !itemName.isEmpty()) {
                        String itemPriceStr = JOptionPane.showInputDialog("Enter item price:");
                        if (itemPriceStr != null && !itemPriceStr.isEmpty()) {
                            try {
                                float itemPrice = Float.parseFloat(itemPriceStr);
                                FoodAndDrink newItem = new FoodAndDrink(itemName, itemPrice);
                                addFoodAndDrink(newItem);
                                updateMenuTextArea();
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(Menu.this, "Invalid price format.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(Menu.this, "Item price is required.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Menu.this, "Item name is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        JButton removeItemButton = new JButton("Remove Item");
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CafeManagementSystem.isBossScreenActive) {
                    String itemName = JOptionPane.showInputDialog("Enter item name to remove:");
                    boolean removed = removeFoodAndDrink(itemName);
                    if (removed) {
                        updateMenuTextArea();
                        JOptionPane.showMessageDialog(Menu.this, "Item removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(Menu.this, "Item not found in the menu.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        JButton changePricesButton = new JButton("Change Prices");
        changePricesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CafeManagementSystem.isBossScreenActive) {
                    String itemName = JOptionPane.showInputDialog("Enter item name to change price:");
                    if (itemName != null && !itemName.isEmpty()) {
                        String newPriceStr = JOptionPane.showInputDialog("Enter new price:");
                        if (newPriceStr != null && !newPriceStr.isEmpty()) {
                            try {
                                float newPrice = Float.parseFloat(newPriceStr);
                                boolean updated = changePrices(itemName, newPrice);
                                if (updated) {
                                    updateMenuTextArea();
                                    JOptionPane.showMessageDialog(Menu.this, "Price updated successfully.");
                                } else {
                                    JOptionPane.showMessageDialog(Menu.this, "Item not found in the menu.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(Menu.this, "Invalid price format.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(Menu.this, "Please enter a new price.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Menu.this, "Please enter an item name.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	if(CafeManagementSystem.isBossScreenActive==true) CafeManagementSystem.showBossOptions();
    	        if(CafeManagementSystem.isCashierScreenActive==true) CafeManagementSystem.showCashierOptions();
    	        if(CafeManagementSystem.isChefScreenActive==true) CafeManagementSystem.showChefOptions();
    	        if(CafeManagementSystem.isWaitressScreenActive==true) CafeManagementSystem.showWaitressOptions();
            }
        });

        JPanel buttonPanel = new JPanel();
        if (CafeManagementSystem.isBossScreenActive) {
            buttonPanel.add(addItemButton);
            buttonPanel.add(removeItemButton);
            buttonPanel.add(changePricesButton);
        }
        buttonPanel.add(backButton);

        add(new JLabel("Menu"), BorderLayout.NORTH);
        add(menuScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public void addFoodAndDrink(FoodAndDrink item) {
        items.add(item);
    }
    //remove the food in list
    public boolean removeFoodAndDrink(String itemName) {
        for (FoodAndDrink item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return true;
            }
        }
        return false;
    }
    public boolean changePrices(String itemName, float newPrice) {
        for (FoodAndDrink item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }
    public void removeFoodAndDrink(FoodAndDrink item) {
    	
        items.remove(item);
    }

    public void displayMenu() {
        updateMenuTextArea();
        setVisible(true);
    }

    public void updateMenuTextArea() {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu:\n");
        for (FoodAndDrink item : items) {
            sb.append("- ").append(item.getName()).append(" - $").append(item.getPrice()).append("\n");
        }
        menuTextArea.setText(sb.toString());
    }
}
