import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class Budget extends JFrame {
	private Budget budget;
    private float income;
    private float outcome = 50;
    private Table table;
    public Budget(float income) {
        this.income = income;

        setTitle("Budget Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel incomeLabel = new JLabel("Income: $" + income);
        JLabel outcomeLabel = new JLabel("Outcome: $" + outcome);
        JLabel profitLabel = new JLabel("Profit: $" + calculateProfit());
        

        mainPanel.add(incomeLabel);
        mainPanel.add(outcomeLabel);
        mainPanel.add(profitLabel);
       
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose(); // Close the window
            if(CafeManagementSystem.isBossScreenActive==true) CafeManagementSystem.showBossOptions();
	        if(CafeManagementSystem.isCashierScreenActive==true) CafeManagementSystem.showCashierOptions();
	        if(CafeManagementSystem.isChefScreenActive==true) CafeManagementSystem.showChefOptions();
	        if(CafeManagementSystem.isWaitressScreenActive==true) CafeManagementSystem.showWaitressOptions();
        });

        mainPanel.add(cancelButton);
        add(mainPanel);
        setVisible(true);
    }

    private float calculateProfit() {
    	float profit = income - outcome;
        return profit;
    }

    private float calculateTotalCost() {
        float totalCost = 0;
        List<Table> tables = Table.getAllTables();
        for (Table table : tables) {
            totalCost += table.getTableCost();
        }
        return totalCost;
    }
    // Constructor
    public Budget(float income, float outcome, Table table) {
        this.income = income;
        this.outcome = outcome;
        this.table = table;
    }
    
    public Budget(float income, Table table) {
        this.income = income;       
        this.table = table;
    }
    // Getter and Setter methods for income
    public float getIncome() {
        return income;
    }
    
    public void setIncome(float income) {
        this.income = income;
    }
    
    // Getter and Setter methods for outcome
    public float getOutcome() {
        return outcome;
    }
    
    public void setOutcome(float outcome) {
        this.outcome = outcome;
    }
    
    
    
    
    // Method to display budget information
    public void seeBudgetInformation() {
        float profit = income - outcome;
        
        System.out.println("Income: " + income);
        System.out.println("Outcome: " + outcome);
        System.out.println("Profit: " + profit);
        
    }
}