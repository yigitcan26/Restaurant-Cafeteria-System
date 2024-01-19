
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
@SuppressWarnings("serial")
public class Table extends JFrame {
    private int tableNumber;
    private int tableCost;
    private List<FoodAndDrink> orderedItems;
    
    private JLabel tableNumberLabel;
    private JLabel tableCostLabel;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        orderedItems = new ArrayList<>();
        initializeGUI();
    }	
    public static void showTableCost(int tableNumber) {
        Table table = new Table(tableNumber);
        int tableCost = table.calculateTableCost();
        JOptionPane.showMessageDialog(null, "Table " + tableNumber + " Cost: $" + tableCost);
    }
    public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getTableCost() {
		return tableCost;
	}
	public static List<Table> getAllTables() {
        return CafeManagementSystem.tables;
    }

	public void setTableCost(int tableCost) {
		this.tableCost = tableCost;
	}
	 public void addOrderedItem(FoodAndDrink item) {
	        orderedItems.add(item);
	        tableCost += item.getPrice();
	    }
	 private void initializeGUI() {
		    setTitle("Table " + tableNumber);
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    setSize(300, 150);
		    setResizable(false);
		    setLocationRelativeTo(null);

		    JPanel panel = new JPanel();
		    panel.setLayout(new GridLayout(3, 2));

		    tableNumberLabel = new JLabel("Table Number: " + tableNumber);
		    tableCostLabel = new JLabel("Table Cost: ");

		    JButton calculateButton = new JButton("Calculate");
		    calculateButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            int calculatedCost = calculateTableCost();
		            tableCostLabel.setText("Table Cost: $" + calculatedCost);
		        }
		    });

		    JButton enterButton = new JButton("Enter");
		    enterButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            enterCustomerTable();
		        }
		    });

		    JButton cancelButton = new JButton("Cancel");
		    cancelButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            dispose(); // Close the window
		            if(CafeManagementSystem.isBossScreenActive==true) CafeManagementSystem.showBossOptions();
	    	        if(CafeManagementSystem.isCashierScreenActive==true) CafeManagementSystem.showCashierOptions();
	    	        if(CafeManagementSystem.isChefScreenActive==true) CafeManagementSystem.showChefOptions();
	    	        if(CafeManagementSystem.isWaitressScreenActive==true) CafeManagementSystem.showWaitressOptions();
		        }
		    });

		    panel.add(tableNumberLabel);
		    panel.add(new JLabel()); // Placeholder for spacing
		    panel.add(tableCostLabel);
		    panel.add(calculateButton);
		    panel.add(enterButton);
		    panel.add(cancelButton);

		    add(panel);
		}

	 public int calculateTableCost() {
		    int totalCost = 0;

		    for (FoodAndDrink item : orderedItems) {
		        totalCost += item.getPrice();
		    }

		    JOptionPane.showMessageDialog(null, "Table " + tableNumber + " Cost: $" + totalCost);
		    return totalCost;
		}

    public void enterCustomerTable() {
        // Perform actions when a customer enters the table
        // For example, you can update the status of the table or display a message

        JOptionPane.showMessageDialog(this, "Customer entered Table " + tableNumber);
    }

   
}
