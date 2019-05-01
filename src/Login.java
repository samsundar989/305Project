import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JTextField textFieldUN;
	private JPasswordField passwordField;
	
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Login() throws SQLException {
		initialize();
		connection = Driver.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(240, 248, 255));
		frame.getContentPane().setBackground(new Color(0, 0, 139));
		frame.setBounds(100, 100, 633, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(240, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(102, 52, 146, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(240, 248, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(102, 87, 129, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		textFieldUN = new JTextField();
		textFieldUN.setBounds(211, 49, 146, 26);
		frame.getContentPane().add(textFieldUN);
		textFieldUN.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(240, 248, 255));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from mydb.databaseuser where username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUN.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()) {
						count = count+1;
					}
					
					if(count == 1) {
						//JOptionPane.showMessageDialog(null, "Username and password is correct");
						frame.dispose();
						AdminView av = new AdminView();
						av.setVisible(true);
					}else if(count >1) {
						JOptionPane.showMessageDialog(null, "Duplicate username and password");
					}else {
						JOptionPane.showMessageDialog(null, "Username or/amd password incorrect");
					}
					
					rs.close();
					pst.close();
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Username and password error");
				}
				
				
			}
		});
		btnNewButton.setBounds(242, 162, 146, 42);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(211, 84, 146, 26);
		frame.getContentPane().add(passwordField);
	}
}
