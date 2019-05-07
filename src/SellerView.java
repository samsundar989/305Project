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

public class SellerView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellerView frame = new SellerView();
					frame.setTitle("Kipling");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SellerView() throws SQLException {
		connection = Driver.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setForeground(new Color(240, 248, 255));
		contentPane.setBackground(new Color(0, 1, 32));
		contentPane.setLayout(null);
		setBounds(100, 100, 1400, 800);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(15, 45, 1225, 545);
		JPanel p1=new JPanel();  
		p1.setBackground(new Color(0, 1, 32));
		p1.setForeground(new Color(0, 1, 32));
		tabbedPane.add("Items",p1); 
		
		String query = "select * from mydb.item;";
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
		
		textField = RowFilterUtil.createRowFilter(table);//JTextField();
		textField.setBounds(25, 52, 146, 26);
		p1.add(textField);
		textField.setColumns(10);
		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		p1.setLayout(null);
		p1.add(scrollPane);
		
		JButton btnAddRow = new JButton("Add Row");
		btnAddRow.setBounds(442, 40, 115, 29);
		p1.add(btnAddRow);
		
		JButton btnSaveRow = new JButton("Save Row");
		btnSaveRow.setBounds(612, 40, 115, 29);
		p1.add(btnSaveRow);
		
		JButton btnShowOnlyMy = new JButton("Show Only My Items");
		btnShowOnlyMy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnShowOnlyMy.setBounds(783, 40, 207, 29);
		p1.add(btnShowOnlyMy);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setBounds(15, 16, 69, 20);
		p1.add(lblSearch);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(271, 40, 115, 29);
		p1.add(btnRemove);
		
		JPanel p2=new JPanel();
		tabbedPane.add("Orders",p2); 
		JPanel p3=new JPanel();
		tabbedPane.add("Shipmennts",p3); 
		
		contentPane.add(tabbedPane);
		
		
	}
}
