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
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class CustomerView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerView frame = new CustomerView();
					frame.setTitle("Kipling");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JTextField textField;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public CustomerView() throws SQLException {
		connection = Driver.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setForeground(new Color(240, 248, 255));
		contentPane.setBackground(new Color(0, 1, 32));

		setBounds(100, 100, 1400, 800);
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(15, 45, 1225, 545);
		JPanel p1=new JPanel();  
		p1.setBackground(new Color(0, 1, 32));
		p1.setForeground(new Color(0, 1, 32));
		tabbedPane.add("Inventory",p1); 
		
		String query = "select * from mydb.item";
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
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
		p1.setLayout(null);
		p1.add(scrollPane);
		
		JLabel label = new JLabel("Search");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 22));
		label.setBounds(25, 16, 129, 20);
		p1.add(label);
		
		textField = RowFilterUtil.createRowFilter(table);//JTextField();
		textField.setBounds(25, 52, 146, 26);
		p1.add(textField);
		textField.setColumns(10);
		
		//TODO Add to Shopping Cart 
		JButton btnAddToShopping = new JButton("Add To Shopping Cart");
		btnAddToShopping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					int selectedRow = table.getSelectedRow();
					String isbn = (String) table.getValueAt(table.getSelectedRow(), 0);
					String title = (String) table.getValueAt(table.getSelectedRow(), 1);
					String author = (String) table.getValueAt(table.getSelectedRow(), 2);
					String price = (String) table.getValueAt(table.getSelectedRow(), 4);
					String query = "insert into mydb.shoppingcart (isbn, title, author, price, quantity)"
							+ "VALUES ('"+isbn+"', '"+title+"', '"+author+"', '"+price+"', '"+1+"');";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					rs.close();
					pst.close();
					
				}catch(Exception t) {
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnAddToShopping.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAddToShopping.setBounds(305, 40, 300, 36);
		p1.add(btnAddToShopping);
		
		// TODO: Implement review 
		JButton btnLookAt = new JButton("See/Leave review");
		btnLookAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLookAt.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLookAt.setBounds(636, 40, 300, 36);
		p1.add(btnLookAt);
		
		
		JPanel p2=new JPanel();
		p2.setBackground(new Color(0, 1, 32));
		p2.setForeground(new Color(0, 1, 32));
		
		
		String query2 = "select * from mydb.shoppingcart";
		PreparedStatement pst2 = connection.prepareStatement(query2);
		ResultSet rs2 = pst2.executeQuery();
		
		JTable table2 = new JTable();
		table2.setModel(DbUtils.resultSetToTableModel(rs2));
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(0, 109, 1220, 402);
		scrollPane2.setOpaque(false);
		scrollPane2.setViewportView(table2);
		
		table2.setOpaque(false);
		((DefaultTableCellRenderer)table2.getDefaultRenderer(Object.class)).setOpaque(false);
		
		table2.setFont(new Font("Tahoma", Font.BOLD, 22));
		table2.setForeground(Color.white);
		
		table2.setSelectionForeground(new Color(1, 159, 254));
		
		table2.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 28));
		
		table2.setRowHeight(30);
		
		scrollPane2.setOpaque(false);
		scrollPane2.getViewport().setOpaque(false);
		p2.setLayout(null);
		p2.add(scrollPane2);
		
		JLabel label2 = new JLabel("Search");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Tahoma", Font.BOLD, 22));
		label2.setBounds(25, 16, 129, 20);
		p2.add(label2);
		
		JTextField textField2 = RowFilterUtil.createRowFilter(table2);//JTextField();
		textField2.setBounds(25, 52, 146, 26);
		p2.add(textField2);
		textField2.setColumns(10);
		
		JButton btnPurchase = new JButton("Complete Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Purchase rd = new Purchase();
					rd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		            rd.setVisible(true);
					
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnPurchase.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnPurchase.setBounds(692, 40, 300, 36);
		p2.add(btnPurchase);
		
		JButton btnRemove = new JButton("Remove from shopping cart");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnRemove.setBounds(286, 40, 364, 36);
		p2.add(btnRemove);
		
		
		tabbedPane.add("Shopping Cart",p2); 
		JPanel p3=new JPanel();
		tabbedPane.add("Orders",p3); 
		
		contentPane.add(tabbedPane);
		
		JButton button = new JButton("Sign out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame av;
				try {
					av = new LoginFrame();
					av.setVisible(true);
					dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button.setBounds(1183, 16, 157, 29);
		contentPane.add(button);
		
		
	}

}