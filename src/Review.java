import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Review extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Review dialog = new Review();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 */
	public Review() throws SQLException, FileNotFoundException {
		connection = Driver.dbConnector();
		setBounds(100, 100, 1261, 655);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setForeground(new Color(240, 248, 255));
		contentPanel.setBackground(new Color(0, 1, 32));
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(15, 110, 1209, 419);
		panel.setBackground(new Color(0, 1, 32));
		panel.setForeground(new Color(0, 1, 32));
		contentPanel.add(panel);
		
		Scanner sc = new Scanner(new File("review_data.txt"));
		int isbn = sc.nextInt();
		
		int sellerID = sc.nextInt();
		
		System.out.println(isbn);
		System.out.println(sellerID);
		
		String query = "select * from mydb.review where ISBN = ";
		query = query+ isbn +" AND SellerID = "+sellerID+";";
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		JButton btnAddReview = new JButton("Add Review");
		btnAddReview.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAddReview.setBounds(305, 40, 300, 36);
		panel.add(btnAddReview);
		
		// Functionality for Add Review button
		btnAddReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReviewDialog dialog = new ReviewDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
//					Statement stmt = connection.createStatement();
//					String sql = "INSERT INTO mydb.shoppingcart";
//					
//				    stmt.executeUpdate(sql);
//				    int id = 0;
//				    try {
//						Scanner sc = new Scanner(new File("username_info.txt"));
//						id = sc.nextInt(); 
//					}catch(Exception ex) {
//						ex.printStackTrace();
//					}
//				    
//				    String querys = "select * from mydb.shoppingcart ";
//					querys += "where CustomerID=" + id;
//					PreparedStatement pst = connection.prepareStatement(querys);
//					ResultSet rs = pst.executeQuery();
////					table2.setModel(DbUtils.resultSetToTableModel(rs));
				    
				} catch (Exception ex) {
					if(ex instanceof SQLException){
						ex.printStackTrace();
					}
					System.out.println("Choose table and row");
				}
			}
		});
		
		
		JTable table = new JTable();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 109, 1220, 402);
		scrollPane.setOpaque(false);
		scrollPane.setViewportView(table);
		
		
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
		
		table.setFont(new Font("Tahoma", Font.BOLD, 22));
		table.setForeground(Color.white);
		
		table.setSelectionForeground(new Color(1, 159, 254));
		
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 28));
		
		table.setRowHeight(30);
		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		panel.setLayout(null);
		panel.add(scrollPane);
		
		JLabel lblReviews = new JLabel("Reviews");
		lblReviews.setOpaque(false);
		lblReviews.setForeground(new Color(1, 159, 254));
		lblReviews.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblReviews.setBackground(Color.BLACK);
		lblReviews.setBounds(525, 26, 351, 93);
		contentPanel.add(lblReviews);
	
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("OK");
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