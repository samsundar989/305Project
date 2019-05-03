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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class RegisterDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	Connection connection = null;
	private JPasswordField passwordField;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegisterDialog dialog = new RegisterDialog();
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
	public RegisterDialog() throws SQLException {
		connection = Driver.dbConnector();
		setBounds(100, 100, 798, 655);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setForeground(new Color(240, 248, 255));
		contentPanel.setBackground(new Color(0, 1, 32));
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{202, 290, 0};
		gbl_contentPanel.rowHeights = new int[]{39, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblRegistrationForm = new JLabel("Registration Form");
			lblRegistrationForm.setOpaque(false);
			lblRegistrationForm.setForeground(new Color(1, 159, 254));
			lblRegistrationForm.setFont(new Font("Tahoma", Font.BOLD, 32));
			lblRegistrationForm.setBackground(Color.BLACK);
			GridBagConstraints gbc_lblRegistrationForm = new GridBagConstraints();
			gbc_lblRegistrationForm.insets = new Insets(0, 0, 5, 0);
			gbc_lblRegistrationForm.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblRegistrationForm.gridx = 1;
			gbc_lblRegistrationForm.gridy = 0;
			contentPanel.add(lblRegistrationForm, gbc_lblRegistrationForm);
		}
		{
			JLabel lblPleaseEnterFollowinf = new JLabel("Please enter following information");
			lblPleaseEnterFollowinf.setForeground(new Color(240, 255, 255));
			lblPleaseEnterFollowinf.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblPleaseEnterFollowinf = new GridBagConstraints();
			gbc_lblPleaseEnterFollowinf.gridwidth = 2;
			gbc_lblPleaseEnterFollowinf.insets = new Insets(0, 0, 5, 0);
			gbc_lblPleaseEnterFollowinf.gridx = 0;
			gbc_lblPleaseEnterFollowinf.gridy = 2;
			contentPanel.add(lblPleaseEnterFollowinf, gbc_lblPleaseEnterFollowinf);
		}
		{
			JLabel lblFirstName = new JLabel("First Name:");
			lblFirstName.setForeground(new Color(240, 255, 255));
			lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
			gbc_lblFirstName.anchor = GridBagConstraints.EAST;
			gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFirstName.gridx = 0;
			gbc_lblFirstName.gridy = 4;
			contentPanel.add(lblFirstName, gbc_lblFirstName);
		}
		{
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField.setColumns(10);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 4;
			contentPanel.add(textField, gbc_textField);
		}
		{
			JLabel lblLastName = new JLabel("Last Name:");
			lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
			lblLastName.setForeground(new Color(240, 255, 255));
			lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblLastName = new GridBagConstraints();
			gbc_lblLastName.anchor = GridBagConstraints.EAST;
			gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
			gbc_lblLastName.gridx = 0;
			gbc_lblLastName.gridy = 5;
			contentPanel.add(lblLastName, gbc_lblLastName);
		}
		{
			textField_1 = new JTextField();
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField_1.setColumns(10);
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 5;
			contentPanel.add(textField_1, gbc_textField_1);
		}
		{
			JLabel lblPhoneNumber = new JLabel("Phone Number:");
			lblPhoneNumber.setHorizontalAlignment(SwingConstants.LEFT);
			lblPhoneNumber.setForeground(new Color(240, 255, 255));
			lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
			gbc_lblPhoneNumber.anchor = GridBagConstraints.EAST;
			gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhoneNumber.gridx = 0;
			gbc_lblPhoneNumber.gridy = 6;
			contentPanel.add(lblPhoneNumber, gbc_lblPhoneNumber);
		}
		{
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField_2.setColumns(10);
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 0);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 6;
			contentPanel.add(textField_2, gbc_textField_2);
		}
		{
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setForeground(new Color(240, 255, 255));
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.anchor = GridBagConstraints.EAST;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.gridx = 0;
			gbc_lblEmail.gridy = 7;
			contentPanel.add(lblEmail, gbc_lblEmail);
		}
		{
			textField_3 = new JTextField();
			textField_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField_3.setColumns(10);
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.insets = new Insets(0, 0, 5, 0);
			gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_3.gridx = 1;
			gbc_textField_3.gridy = 7;
			contentPanel.add(textField_3, gbc_textField_3);
		}
		{
			JLabel lblAddress = new JLabel("Address:");
			lblAddress.setForeground(new Color(240, 255, 255));
			lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblAddress = new GridBagConstraints();
			gbc_lblAddress.anchor = GridBagConstraints.EAST;
			gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblAddress.gridx = 0;
			gbc_lblAddress.gridy = 8;
			contentPanel.add(lblAddress, gbc_lblAddress);
		}
		{
			textField_4 = new JTextField();
			textField_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField_4.setColumns(10);
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.insets = new Insets(0, 0, 5, 0);
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 1;
			gbc_textField_4.gridy = 8;
			contentPanel.add(textField_4, gbc_textField_4);
		}
		{
			JLabel lblUsername = new JLabel("Username:");
			lblUsername.setForeground(new Color(240, 255, 255));
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.anchor = GridBagConstraints.EAST;
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.gridx = 0;
			gbc_lblUsername.gridy = 9;
			contentPanel.add(lblUsername, gbc_lblUsername);
		}
		{
			textField_5 = new JTextField();
			textField_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField_5.setColumns(10);
			GridBagConstraints gbc_textField_5 = new GridBagConstraints();
			gbc_textField_5.insets = new Insets(0, 0, 5, 0);
			gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_5.gridx = 1;
			gbc_textField_5.gridy = 9;
			contentPanel.add(textField_5, gbc_textField_5);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setForeground(new Color(240, 255, 255));
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.EAST;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 0;
			gbc_lblPassword.gridy = 10;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			passwordField = new JPasswordField();
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.insets = new Insets(0, 0, 5, 0);
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.gridx = 1;
			gbc_passwordField.gridy = 10;
			contentPanel.add(passwordField, gbc_passwordField);
		}
		{
			JLabel label = new JLabel("Are you seller?");
			label.setForeground(new Color(240, 255, 255));
			label.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(0, 0, 0, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 11;
			contentPanel.add(label, gbc_label);
		}
		JCheckBox checkBox;
		{
			checkBox = new JCheckBox("");
			checkBox.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_checkBox = new GridBagConstraints();
			gbc_checkBox.anchor = GridBagConstraints.WEST;
			gbc_checkBox.gridx = 1;
			gbc_checkBox.gridy = 11;
			contentPanel.add(checkBox, gbc_checkBox);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Register");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Statement stmt;
						try {
							String view = "customer";
							if(checkBox.isSelected()) {
								view = "seller";
							}
							stmt = connection.createStatement();
							String sql = "INSERT INTO mydb.databaseuser(Username, Password, View) " +
									"VALUES ('"+textField_5.getText()+"', '"+passwordField.getText()+"', '"+view+"');";
						    stmt.executeUpdate(sql);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					      
					}
				});
				okButton.setActionCommand("Register");
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
