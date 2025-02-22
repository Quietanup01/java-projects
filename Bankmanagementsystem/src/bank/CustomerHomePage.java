package bank;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class CustomerHomePage extends JFrame implements ActionListener {
    JLabel label1, label2;
    JButton viewTransactionsButton, depositButton, withdrawButton, closeButton, changePinButton;
     String pin;
     //int final_bakance;
	int balance;
     
	static int getBalance(String pin) {
	    Connn c = new Connn();
	    String sql = "SELECT balance FROM customer WHERE pin = '" + pin + "'";

	    int balance = 0; // Initialize balance to handle cases where pin doesn't match any record

	    try {
	        ResultSet rs = c.statement.executeQuery(sql);
	        if (rs.next()) {
	            balance = rs.getInt("balance");
	        }
	        rs.close(); // Close the result set after retrieving data
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return balance;
	}

    CustomerHomePage( String pin) {
        super("Customer Homepage");
        
        this.pin = pin;
        
        this.balance = getBalance(pin);

        label1 = new JLabel("Welcome to Your Account!");
        label1.setFont(new Font("Ralway", Font.BOLD, 20));
        label1.setForeground(Color.BLACK);
        label1.setBounds(150, 50, 375, 30);
        add(label1);

        label2 = new JLabel("Your Account Balance: " + balance + ""); // Example balance, replace with actual balance
 // Example balance, replace with actual balance
        label2.setFont(new Font("Ralway", Font.BOLD, 16));
        label2.setForeground(Color.BLACK);
        label2.setBounds(150, 100, 375, 30);
        add(label2);

        viewTransactionsButton = new JButton("View Transactions");
        viewTransactionsButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewTransactionsButton.setForeground(Color.WHITE);
        viewTransactionsButton.setBackground(Color.BLACK);
        viewTransactionsButton.setBounds(150, 170, 200, 30);
        viewTransactionsButton.addActionListener(this);
        add(viewTransactionsButton);

        depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.BOLD, 14));
        depositButton.setForeground(Color.WHITE);
        depositButton.setBackground(Color.BLACK);
        depositButton.setBounds(400, 170, 200, 30);
        depositButton.addActionListener(this);
        add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setBackground(Color.BLACK);
        withdrawButton.setBounds(150, 220, 200, 30);
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        closeButton = new JButton("Close Account");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.BLACK);
        closeButton.setBounds(400, 220, 200, 30);
        closeButton.addActionListener(this);
        add(closeButton);

        changePinButton = new JButton("Change PIN");
        changePinButton.setFont(new Font("Arial", Font.BOLD, 14));
        changePinButton.setForeground(Color.WHITE);
        changePinButton.setBackground(Color.BLACK);
        changePinButton.setBounds(275, 270, 250, 30);
        changePinButton.addActionListener(this);
        add(changePinButton);

        getContentPane().setBackground(Color.LIGHT_GRAY);

        setLayout(null);
        setSize(800, 400);
        setLocation(350, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewTransactionsButton) {
            new Transaction(pin);
        } else if (e.getSource() == depositButton)  {
        
           // String amount = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
           new Deposit(pin);
            
        } else if (e.getSource() == withdrawButton) {
        	
//            String amount = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
//            if (amount != null && !amount.isEmpty()) {
//                double withdrawAmount = Double.parseDouble(amount);
//                JOptionPane.showMessageDialog(this, "Withdrawn: " + withdrawAmount);
        	new Withdrawal(pin);
            
        } else if (e.getSource() == closeButton) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to close your account?");
            if (choice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Account Closed Successfully");
                dispose();
            }
        } else if (e.getSource() == changePinButton) {
            String accountNo = JOptionPane.showInputDialog(this, "Enter Account Number:");
            String oldPin = JOptionPane.showInputDialog(this, "Enter Old PIN:");
            String newPin = JOptionPane.showInputDialog(this, "Enter New PIN:");
            
            try {
               
            	Connn c = new Connn();
            	String sql = "UPDATE customer SET pin = '" + newPin + "' WHERE card_number = '" + accountNo + "'";
            	int rowsAffected =  c.statement.executeUpdate(sql);
   
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "PIN updated successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update PIN. Please check your old PIN.");
                }
                setVisible(false);
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred while updating PIN: " + ex.getMessage());
            }
        
//            System.out.println("Account Number: " + accountNo);
//            System.out.println("Old PIN: " + oldPin);
//            System.out.println("New PIN: " + newPin);
        }
    }

    public static void main(String[] args) {
//    	 String pin = "";
//    	 int balance = getBalance();
//        new CustomerHomePage(pin,balance);
    }
}
