import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;

public class LoginFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setTitle("Kipling");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JTextField textFieldUN;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_2;
	private JLabel lblSignInTo;
	private JLabel lblSignInTo_1;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public LoginFrame() throws SQLException {
		connection = Driver.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setForeground(new Color(240, 248, 255));
		contentPane.setBackground(new Color(0, 1, 32));
		setBounds(100, 100, 1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(240, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(481, 310, 146, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(240, 248, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(486, 379, 129, 20);
		contentPane.add(lblNewLabel_1);
		
		textFieldUN = new JTextField();
		textFieldUN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textFieldUN.setBounds(642, 310, 146, 26);
		contentPane.add(textFieldUN);
		textFieldUN.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.setBorder(new EmptyBorder(20,20,20,20));
		btnNewButton.setBackground(new Color(0, 0, 70));
		btnNewButton.setForeground(new Color(1, 159, 254));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from mydb.databaseuser where username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUN.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					String view = "";
					while(rs.next()) {
						view = rs.getString(3);
						count = count+1;
					}
					
					if(count == 1) {
						dispose();
						if(view.equals("admin")) {
							AdminView av = new AdminView();
							av.setVisible(true);
						}else if(view.equals("seller")) {
							SellerView av = new SellerView();
							av.setVisible(true);
						}else {
							CustomerView av = new CustomerView();
							av.setVisible(true);
						}
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
		btnNewButton.setBounds(642, 499, 146, 46);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordField.setBounds(642, 379, 146, 26);
		contentPane.add(passwordField);
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("4k-gif-wallpaper.gif").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
		lblNewLabel_2 = new JLabel(imageIcon);
		lblNewLabel_2.setBounds(1125, 510, 205, 191);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(349, 249, 700, 337);
		panel.setBackground(new Color(255, 255, 255, 10));
		panel.setBorder(new EmptyBorder(15,15,15,15));
		contentPane.add(panel);
		
		lblSignInTo_1 = new JLabel("Sign In to Kipling");
		lblSignInTo_1.setBounds(562, 114, 351, 93);
		lblSignInTo_1.setBackground(new Color(0, 0, 0));
		lblSignInTo_1.setForeground(new Color(1, 159, 254));
		lblSignInTo_1.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblSignInTo_1.setOpaque(false);
		contentPane.add(lblSignInTo_1);
		
		JButton btnYouCanSign = new JButton("You can sign up here!");
		btnYouCanSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					RegisterDialog rd = new RegisterDialog();
					rd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		            rd.setVisible(true);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnYouCanSign.setForeground(new Color(1, 159, 254));
		btnYouCanSign.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnYouCanSign.setBorderPainted(false);
		btnYouCanSign.setBorder(new EmptyBorder(20,20,20,20));
		btnYouCanSign.setBackground(new Color(0, 0, 70));
		btnYouCanSign.setBounds(534, 667, 365, 46);
		contentPane.add(btnYouCanSign);
		
		
	}
}
