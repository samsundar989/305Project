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
		
		textField = new JTextField();
		textField.setBounds(25, 52, 146, 26);
		p1.add(textField);
		textField.setColumns(10);
		
		JButton btnAddToShopping = new JButton("Add To Shopping Cart");
		btnAddToShopping.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAddToShopping.setBounds(305, 40, 300, 36);
		p1.add(btnAddToShopping);
		
		JButton btnLookAt = new JButton("See/Leave review");
		btnLookAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLookAt.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLookAt.setBounds(636, 40, 300, 36);
		p1.add(btnLookAt);
		
		
		JPanel p2=new JPanel();
		tabbedPane.add("Shopping Cart",p2); 
		JPanel p3=new JPanel();
		tabbedPane.add("Orders",p3); 
		
		contentPane.add(tabbedPane);
		
		
	}
}