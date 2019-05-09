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
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class ReviewDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextArea textField_1;

	
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReviewDialog dialog = new ReviewDialog();
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
	public ReviewDialog() throws SQLException {
		connection = Driver.dbConnector();
		setBounds(100, 100, 798, 655);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setForeground(new Color(240, 248, 255));
		contentPanel.setBackground(new Color(0, 1, 32));
		contentPanel.setLayout(null);
		{
			JLabel lblRegistrationForm = new JLabel("Create Review");
			lblRegistrationForm.setBounds(207, 5, 300, 39);
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
			JLabel lblFirstName = new JLabel("Rating (out of 10):");
			lblFirstName.setBounds(78, 146, 250, 29);
			lblFirstName.setForeground(new Color(240, 255, 255));
			lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(lblFirstName);
		}
		{
			textField = new JTextField();
			textField.setBounds(300, 143, 250, 35);
			textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
			textField.setColumns(10);
			contentPanel.add(textField);
		}
		{
			JLabel lblLastName = new JLabel("Detailed Review:");
			lblLastName.setBounds(80, 186,300, 29);
			lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
			lblLastName.setForeground(new Color(240, 255, 255));
			lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 24));
			contentPanel.add(lblLastName);
		}
		{
			textField_1 = new JTextArea();
			textField_1.setBounds(300, 183, 450, 100);
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textField_1.setColumns(10);
			contentPanel.add(textField_1);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Ok");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Statement stmt;
						try {
							Scanner sc = new Scanner(new File("review_data.txt"));
							String isbn = sc.next();
							String sellerID = sc.next();
							Scanner scanner = new Scanner(new File("username_info.txt"));
							String id = scanner.next();
							stmt = connection.createStatement();
							
							String sql = "INSERT INTO mydb.review" +
									" VALUES ("+isbn+","+id+","+sellerID+","+textField_1.getText()+", "+textField.getText()+")";;
						    stmt.executeUpdate(sql);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					      
					}
				});
				okButton.setActionCommand("Ok");
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