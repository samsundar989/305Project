import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class Purchase extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Purchase dialog = new Purchase();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public Purchase() throws SQLException {
		ArrayList<Double> totalPrices = new ArrayList<Double>();
		ArrayList<String> sellers = new ArrayList<String>();
		ArrayList<String> items = new ArrayList<String>();
		connection = Driver.dbConnector();
		setBounds(100, 100, 798, 655);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setForeground(new Color(240, 248, 255));
		contentPanel.setBackground(new Color(0, 1, 32));
		contentPanel.setLayout(null);
		{
			JLabel lblRegistrationForm = new JLabel("Checkout");
			lblRegistrationForm.setBounds(207, 5, 149, 39);
			lblRegistrationForm.setOpaque(false);
			lblRegistrationForm.setForeground(new Color(1, 159, 254));
			lblRegistrationForm.setFont(new Font("Tahoma", Font.BOLD, 32));
			lblRegistrationForm.setBackground(Color.BLACK);
			contentPanel.add(lblRegistrationForm);
		}
		{
			JLabel lblPleaseEnterFollowinf = new JLabel("Please enter following information");
			lblPleaseEnterFollowinf.setBounds(207, 79, 361, 29);
			lblPleaseEnterFollowinf.setForeground(new Color(240, 255, 255));
			lblPleaseEnterFollowinf.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(lblPleaseEnterFollowinf);
		}
		{
			JLabel lblFirstName = new JLabel("First Name:");
			lblFirstName.setBounds(78, 146, 124, 29);
			lblFirstName.setForeground(new Color(240, 255, 255));
			lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(lblFirstName);
		}
		{
			textField = new JTextField();
			textField.setBounds(207, 143, 564, 35);
			textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField.setColumns(10);
			contentPanel.add(textField);
		}
		{
			JLabel lblLastName = new JLabel("Last Name:");
			lblLastName.setBounds(80, 186, 122, 29);
			lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
			lblLastName.setForeground(new Color(240, 255, 255));
			lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(lblLastName);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(207, 183, 564, 35);
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField_1.setColumns(10);
			contentPanel.add(textField_1);
		}
		{
			JLabel lblPhoneNumber = new JLabel("Card Number:");
			lblPhoneNumber.setBounds(53, 226, 149, 29);
			lblPhoneNumber.setHorizontalAlignment(SwingConstants.LEFT);
			lblPhoneNumber.setForeground(new Color(240, 255, 255));
			lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(lblPhoneNumber);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(207, 223, 564, 35);
			textField_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField_2.setColumns(10);
			contentPanel.add(textField_2);
		}
		{
			JLabel lblEmail = new JLabel("CSV:");
			lblEmail.setBounds(153, 266, 49, 29);
			lblEmail.setForeground(new Color(240, 255, 255));
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(lblEmail);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(207, 263, 564, 35);
			textField_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField_3.setColumns(10);
			contentPanel.add(textField_3);
		}
		{
			JLabel lblAddress = new JLabel("Billing Address:");
			lblAddress.setBounds(42, 306, 160, 29);
			lblAddress.setForeground(new Color(240, 255, 255));
			lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(lblAddress);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(207, 303, 564, 35);
			textField_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField_4.setColumns(10);
			contentPanel.add(textField_4);
		}
		{
			// TODO: Retrieve each item in shopping cart, get total price and sellerID
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM mydb.shoppingcart;";
		    ResultSet shoppingCart = stmt.executeQuery(sql);
		    double totalCharge = 0;
		    while(shoppingCart.next()) {
		    	totalCharge = totalCharge + shoppingCart.getDouble(4);
		    	totalPrices.add(shoppingCart.getDouble(4));
		    	items.add(shoppingCart.getString(1));
		    }
		    
		    // Add seller ID to list for each item in shopping Cart
		    for(int i=0;i<items.size();i++) {
		    Statement statement = connection.createStatement();
			String query = "SELECT * FROM mydb.item WHERE ISBN="+items.get(i)+";";
			System.out.println(query);
		    ResultSet inventory = statement.executeQuery(query);
		    while(inventory.next()) {
		    	sellers.add(inventory.getString(4));
		    }
		    } 
			JLabel lblPassword = new JLabel("Amount Charged: $"+totalCharge);
			lblPassword.setBounds(17, 438, 300, 29);
			lblPassword.setForeground(new Color(240, 255, 255));
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(lblPassword);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Buy");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Statement payment;
						Statement check;
						try {    
							Scanner sc = new Scanner(new File("username_info.txt"));
							String customerID = sc.next();
							int confirmationNum =0;
							// To generate ConfirmationNumber
							check = connection.createStatement();
							String num = "SELECT * FROM mydb.orders ORDER BY ConfirmationNumber DESC LIMIT 1;";
							ResultSet confirmation = check.executeQuery(num);
							if(confirmation.next()==false) {
								confirmationNum=1;
							}
							else {
								confirmationNum = confirmation.getInt(2);
							}
							
							
						    // TODO: For each item in shopping cart, add to Makes A and to Payment
							for(int i=0;i<items.size();i++) {
								payment = connection.createStatement();
							    String pay = "INSERT INTO mydb.orders" +
										" VALUES ("+customerID+", "+sellers.get(i)+", "+confirmationNum+");";
							    payment.executeUpdate(pay);
							    confirmationNum++;
							}
							
							dispose();
						} catch (SQLException | FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					      
					}
				});
				okButton.setActionCommand("Buy");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}