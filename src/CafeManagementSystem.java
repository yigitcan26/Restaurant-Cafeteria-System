import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CafeManagementSystem {
	
    private static JFrame mainFrame;
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private static CafeManagementSystem instance;
    public static Boss boss;
    public static Cashier cashier;
    public static Chef chef;
    public static Waitress waitress;
    //instance definition of type singleton
    public static CafeManagementSystem getInstance() {
        if (instance == null) {
            instance = new CafeManagementSystem();
        }
        return instance;
    }
    //some definitions

    public CafeManagementSystem() {
        boss = new Boss("Boss", "boss@example.com", "023(424)035-82-46");
        cashier = new Cashier("Cashier", "cashier@example.com", "023(424)035-82-46");
        chef = new Chef("Chef", "chef@example.com", "023(424)035-82-46");
        waitress = new Waitress("Waitress", "waitress@example.com", "023(424)035-82-46");
        
        
    }
    public static boolean isBossScreenActive = false;
    public static boolean isCashierScreenActive = false;
    public static boolean isChefScreenActive = false;
    public static boolean isWaitressScreenActive = false;
    //login screen for boss chef cashier and waitress
    private void LoginScreen() {
        isBossScreenActive = false;
        isCashierScreenActive = false;
        isChefScreenActive = false;
        isWaitressScreenActive = false;
        mainFrame = new JFrame("Cafe Management System");
        //close the window
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 200);
        mainFrame.setLayout(new BorderLayout());       
        mainPanel = new JPanel();      
        mainPanel.setLayout(new GridLayout(3, 2));
        //panel is the center
        mainFrame.setLocationRelativeTo(null);
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
        	//user can only make 3 incorrect login attempts
            private int failedAttempts = 0; 
            private final int maxAttempts = 3; 
            @Override
            public void actionPerformed(ActionEvent e) {
            	//get the user input
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals(boss.getName()) && password.equals(boss.getPassword())) {
                    // Boss login successful
                    mainFrame.setVisible(false);
                    isBossScreenActive = true;
                    openBossScreen();
                } else if (username.equals(cashier.getName()) && password.equals(cashier.getPassword())) {
                    // Cashier login successful
                    mainFrame.setVisible(false);
                    isCashierScreenActive = true;
                    openCashierScreen();
                } else if (username.equals(chef.getName()) && password.equals(chef.getPassword())) {
                    // Chef login successful
                    mainFrame.setVisible(false);
                    isChefScreenActive = true;
                    openChefScreen();
                } else if (username.equals(waitress.getName()) && password.equals(waitress.getPassword())) {
                    // Waitress login successful
                    mainFrame.setVisible(false);
                    isWaitressScreenActive = true;
                    openWaitressScreen();
                } else {
                    failedAttempts++; 
                    //if failed attemps is bigger than 3, exit the program
                    if (failedAttempts >= maxAttempts) {
                        JOptionPane.showMessageDialog(mainFrame, "Max login attempts exceeded. Program will exit.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                        System.exit(0); 
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // Clear the fields after login attempt
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);

        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    public static void openBossScreen() {
        // Boss screen implementation
        JOptionPane.showMessageDialog(mainFrame, "Welcome Boss!");
        if (isBossScreenActive) {           
            showBossOptions();
        } 
    }

    public static void openCashierScreen() {
        // Cashier screen implementation
        JOptionPane.showMessageDialog(mainFrame, "Welcome Cashier!");
        if (isCashierScreenActive) {           
            showCashierOptions();
        } 
    }

    public static void openChefScreen() {
        // Chef screen implementation
        JOptionPane.showMessageDialog(mainFrame, "Welcome Chef!");
        if (isChefScreenActive) {           
            showChefOptions();
        } 
    }

    public static void openWaitressScreen() {
        // Waitress screen implementation
        JOptionPane.showMessageDialog(mainFrame, "Welcome Waitress!");
        if (isWaitressScreenActive) {           
            showWaitressOptions();
        } 
    
    }
    
    public static void showBossOptions() {
        String[] options = {"Menu", "Budget", "Table", "Employee", "Logout"};
        int choice = JOptionPane.showOptionDialog(mainFrame, "Select an option:", "Boss Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                // Menu option selected
                showMenuOptions();
                break;
            case 1:
                // Budget option selected
                showBudgetOptions();
                break;
            case 2:
                // Table option selected
                showTableOptions();
                break;
            case 3:
            	showEmployeeInfo();
            	break;
            case 4:
            	mainFrame.dispose(); 
            	
            	CafeManagementSystem main = CafeManagementSystem.getInstance();
                main.setTables(tables);
                main.setMenu(menuInstance);
                main.LoginScreen();
            	break;
        }
    }
  //create instance for usability
    private void setTables(List<Table> tables) {
        this.tables = tables;
    }

    private void setMenu(Menu menu) {
        this.menuInstance = menu;
    }
     
    public static Chef getChefInstance() {
        return chef;
    }

    public static void setChefInstance(Chef chefInstance) {
        CafeManagementSystem.chef = chefInstance;
    }

    public static Waitress getWaitressInstance() {
        return waitress;
    }

    public static void setWaitressInstance(Waitress waitressInstance) {
        CafeManagementSystem.waitress = waitressInstance;
    }

    public static Cashier getCashierInstance() {
        return cashier;
    }

    public static void setCashierInstance(Cashier cashierInstance) {
        CafeManagementSystem.cashier = cashierInstance;
    }
    
    public static void showEmployeeInfo() {
	    	JFrame frame = new JFrame("Employee Information");
	    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	frame.setSize(400, 500);
	    	frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	    	frame.setLocationRelativeTo(null);
	    	//create label for printing the console
    	 	JLabel chefLabel = new JLabel("Chef Information:");
    	    JLabel chefNameLabel = new JLabel("Name: " + chef.getName());
    	    JLabel chefEmailLabel = new JLabel("Email: " + chef.getEmail());
    	    JLabel chefPhoneLabel = new JLabel("Phone Number: " + chef.getPhone_Number());
    	    JLabel chefSalaryLabel = new JLabel("Salary: " + chef.getSalary());
    	    
    	    JLabel waitressLabel = new JLabel("Waitress Information:");
    	    JLabel waitressNameLabel = new JLabel("Name: " + waitress.getName());
    	    JLabel waitressEmailLabel = new JLabel("Email: " + waitress.getEmail());
    	    JLabel waitressPhoneLabel = new JLabel("Phone Number: " + waitress.getPhone_Number());
    	    JLabel waitressSalaryLabel = new JLabel("Salary: " + waitress.getSalary());
    	    
    	    JLabel cashierLabel = new JLabel("Cashier Information:");
    	    JLabel cashierNameLabel = new JLabel("Name: " + cashier.getName());
    	    JLabel cashierEmailLabel = new JLabel("Email: " + cashier.getEmail());
    	    JLabel cashierPhoneLabel = new JLabel("Phone Number: " + cashier.getPhone_Number());
    	    JLabel cashierSalaryLabel = new JLabel("Salary: " + waitress.getSalary());
    	    //add to frame
    	    frame.add(chefLabel);
    	    frame.add(chefNameLabel);
    	    frame.add(chefEmailLabel);
    	    frame.add(chefPhoneLabel);
    	    frame.add(chefSalaryLabel);

    	    frame.add(waitressLabel);
    	    frame.add(waitressNameLabel);
    	    frame.add(waitressEmailLabel);
    	    frame.add(waitressPhoneLabel);
    	    frame.add(waitressSalaryLabel);

    	    frame.add(cashierLabel);
    	    frame.add(cashierNameLabel);
    	    frame.add(cashierEmailLabel);
    	    frame.add(cashierPhoneLabel);
    	    frame.add(cashierSalaryLabel);
    	    //change salary button for boss
    	    JButton changeChefSalaryButton = new JButton("Change Chef Salary");
    	    changeChefSalaryButton.addActionListener(e -> {
    	        if (isBossScreenActive) {
    	            String newSalaryStr = JOptionPane.showInputDialog("Enter new Chef salary:");
    	            if (newSalaryStr != null && !newSalaryStr.isEmpty()) {
    	                try {
    	                    float newSalary = Float.parseFloat(newSalaryStr);
    	                    chef.setSalary(newSalary);
    	                    chefSalaryLabel.setText("Salary: " + chef.getSalary());
    	                } catch (NumberFormatException ex) {
    	                    JOptionPane.showMessageDialog(frame, "Invalid salary format.", "Error", JOptionPane.ERROR_MESSAGE);
    	                }
    	            }
    	        }
    	    });
    	  //change salary button for boss
    	    JButton changeWaitressSalaryButton = new JButton("Change Waitress Salary");
    	    changeWaitressSalaryButton.addActionListener(e -> {
    	        if (isBossScreenActive) {
    	            String newSalaryStr = JOptionPane.showInputDialog("Enter new Waitress salary:");
    	            if (newSalaryStr != null && !newSalaryStr.isEmpty()) {
    	                try {
    	                    float newSalary = Float.parseFloat(newSalaryStr);
    	                    waitress.setSalary(newSalary);
    	                    waitressSalaryLabel.setText("Salary: " + waitress.getSalary());
    	                } catch (NumberFormatException ex) {
    	                    JOptionPane.showMessageDialog(frame, "Invalid salary format.", "Error", JOptionPane.ERROR_MESSAGE);
    	                }
    	            }
    	        }
    	    });
    	  //change salary button for boss
    	    JButton changeCashierSalaryButton = new JButton("Change Cashier Salary");
    	    changeCashierSalaryButton.addActionListener(e -> {
    	        if (isBossScreenActive) {
    	            String newSalaryStr = JOptionPane.showInputDialog("Enter new Cashier salary:");
    	            if (newSalaryStr != null && !newSalaryStr.isEmpty()) {
    	                try {
    	                    float newSalary = Float.parseFloat(newSalaryStr);
    	                    cashier.setSalary(newSalary);
    	                    cashierSalaryLabel.setText("Salary: " + cashier.getSalary());
    	                } catch (NumberFormatException ex) {
    	                    JOptionPane.showMessageDialog(frame, "Invalid salary format.", "Error", JOptionPane.ERROR_MESSAGE);
    	                }
    	            }
    	        }
    	    });
    	    //cancel button
    	    JButton cancelButton = new JButton("Cancel");
    	    cancelButton.addActionListener(e -> {
    	        frame.dispose(); // Close the window
    	        if(isBossScreenActive==true) showBossOptions();
    	        if(isCashierScreenActive==true) showCashierOptions();
    	        if(isChefScreenActive==true) showChefOptions();
    	        if(isWaitressScreenActive==true) showWaitressOptions();
    	        
    	        
    	        
    	    });
    	    //add buttons to frame
    	    frame.add(changeChefSalaryButton);
    	    frame.add(changeWaitressSalaryButton);
    	    frame.add(changeCashierSalaryButton);
    	    frame.add(cancelButton);

    	    // Set frame visible
    	    frame.setVisible(true);
    }
    public static void showCashierOptions() {
    	String[] options = {"Menu", "Table", "Logout"};
        int choice = JOptionPane.showOptionDialog(mainFrame, "Select an option:", "Cashier Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                // Menu option selected
                showMenuOptions();
                break;
            
            case 1:
                // Table option selected
                showTableOptions();
                break;
            
            case 2:
            	mainFrame.dispose(); 

            	CafeManagementSystem main = CafeManagementSystem.getInstance();
                main.setTables(tables);
                main.setMenu(menuInstance);
                main.LoginScreen();
            	break;
        }
    }

    public static void showChefOptions() {
    	String[] options = {"Menu", "Table", "Logout"};
        int choice = JOptionPane.showOptionDialog(mainFrame, "Select an option:", "Chef Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
        case 0:
            // Menu option selected
            showMenuOptions();
            break;
        
        case 1:
            // Table option selected
            showTableOptions();
            break;
        
        case 2:
        	mainFrame.dispose(); 

        	CafeManagementSystem main = CafeManagementSystem.getInstance();
            main.setTables(tables);
            main.setMenu(menuInstance);
            main.LoginScreen();
        	break;
        }
    }

    public static void showWaitressOptions() {
    	String[] options = {"Menu", "Table", "Logout"};
        int choice = JOptionPane.showOptionDialog(mainFrame, "Select an option:", "Waitress Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
        case 0:
            // Menu option selected
            showMenuOptions();
            break;
        
        case 1:
            // Table option selected
            showTableOptions();
            break;
        
        case 2:
        	mainFrame.dispose(); 
        	//get instance for reuse
        	CafeManagementSystem main = CafeManagementSystem.getInstance();
            main.setTables(tables);
            main.setMenu(menuInstance);
            main.LoginScreen();
        	break;
        }
    }

    public static void showMenuOptions() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Menu menu = getMenuInstance();
                menu.displayMenu();
            }
        });
    }

    public static void showBudgetOptions() {
        // Budget options implementation
    	String[] options = { "View Budget Information", "Cancel"  };
        int choice = JOptionPane.showOptionDialog(
            mainFrame,
            "Choose an option:",
            "Budget Options",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        // Budget options implementation
        if (choice == 0) {
            float totalIncome = 0;
            
            for (Table table : tables) {
                totalIncome += table.getTableCost();
            }            
            Budget budget = new Budget(totalIncome);
            //budget.seeBudgetInformation();
        }
        else if (choice == 1) {
            // Cancel button is clicked, return to the main screen
        	mainFrame.setVisible(true);
        	mainFrame.dispose();
        	if(isBossScreenActive==true) showBossOptions();
	        if(isCashierScreenActive==true) showCashierOptions();
	        if(isChefScreenActive==true) showChefOptions();
	        if(isWaitressScreenActive==true) showWaitressOptions();
        }
        
        
    }

    public static List<Table> tables = new ArrayList<>();

    public static void showTableOptions() {
        String[] options = { "Enter Table Information", "Select Table and Display Cost", "Cancel" };
        int choice = JOptionPane.showOptionDialog(
            mainFrame,
            "Choose an option:",
            "Table Options",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        // Table options implementation
        if (choice == 0) {
            String tableNumberInput = JOptionPane.showInputDialog(mainFrame, "Enter the table number:");
            if (tableNumberInput == null || tableNumberInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Table number is required.", "Error", JOptionPane.ERROR_MESSAGE);
                showTableOptions();
                return;
            }
            try {
                int tableNumber = Integer.parseInt(tableNumberInput);

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        Table table = new Table(tableNumber);
                        tables.add(table);

                        // Show menu to select items
                        Menu menu = getMenuInstance();

                        // Keep prompting for adding items until the user cancels
                        while (true) {
                            String itemName = JOptionPane.showInputDialog(mainFrame, "Enter the item name (or cancel to finish):");
                            if (itemName == null || itemName.equalsIgnoreCase("cancel")) {
                                mainFrame.setVisible(false);
                                table.setVisible(true);
                                break;
                            }

                            // Check if the item exists in the menu
                            FoodAndDrink selectedItem = null;
                            for (FoodAndDrink item : menu.getItems()) {
                                if (item.getName().equalsIgnoreCase(itemName)) {
                                    selectedItem = item;
                                    break;
                                }
                            }

                            if (selectedItem != null) {
                                table.addOrderedItem(selectedItem);
                                table.calculateTableCost();
                            } else {
                                JOptionPane.showMessageDialog(mainFrame, "Item not found in the menu.", "Error", JOptionPane.ERROR_MESSAGE);
                                table.setVisible(true);
                            }
                        }
                    }
                });
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainFrame, "Invalid input. Please enter a valid table number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                showTableOptions();
            }
        } else if (choice == 1) {
            // Select Table and Display Cost
            String tableNumberInput = JOptionPane.showInputDialog(mainFrame, "Enter the table number:");
            if (tableNumberInput == null) {
                // Cancel button is clicked, return to the main screen
                mainFrame.setVisible(true);
                mainFrame.dispose();
                if(isBossScreenActive==true) showBossOptions();
                if(isCashierScreenActive==true) showCashierOptions();
                if(isChefScreenActive==true) showChefOptions();
                if(isWaitressScreenActive==true) showWaitressOptions();
                return;
            }

            try {
                int tableNumber = Integer.parseInt(tableNumberInput);
                // check all table and get the cost
                Table selectedTable = null;
                for (Table table : tables) {
                    if (table.getTableNumber() == tableNumber) {
                        selectedTable = table;
                        break;
                    }
                }

                if (selectedTable != null) {
                    selectedTable.setVisible(true);
                    selectedTable.calculateTableCost();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Table not found or invalid selection.", "Error", JOptionPane.ERROR_MESSAGE);
                    showTableOptions();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainFrame, "Invalid input. Please enter a valid table number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                showTableOptions();
            }
            // cancel the table
        }else if (choice == 2) {
        	mainFrame.setVisible(true);
        	mainFrame.dispose();
        	if(isBossScreenActive==true) showBossOptions();
	        if(isCashierScreenActive==true) showCashierOptions();
	        if(isChefScreenActive==true) showChefOptions();
	        if(isWaitressScreenActive==true) showWaitressOptions();
        }
    }
    private static Menu menuInstance;
    //create instance for reuse
    public static Menu getMenuInstance() {
        if (menuInstance == null) {
            menuInstance = new Menu();
        }
        return menuInstance;
    }
    //start the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	CafeManagementSystem main = CafeManagementSystem.getInstance();
            	main.LoginScreen();
            }
        });
    }
}
