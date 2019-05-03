import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.Font;
import java.awt.Panel;

public class AdminView extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView frame = new AdminView();
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
	public AdminView() throws SQLException {
		connection = Driver.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setForeground(new Color(240, 248, 255));
		contentPane.setBackground(new Color(0, 1, 32));
		setBounds(100, 100, 1400, 800);
		
		
		JButton btnLoadTable = new JButton("Load Table");
		btnLoadTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLoadTable.setBounds(521, 37, 163, 36);
		//contentPane.add(btnLoadTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 109, 1220, 402);
		//contentPane.add(scrollPane);
		
		
		DefaultTableModel model = new DefaultTableModel();
		
		table = new JTable(model);
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(15, 45, 1225, 545);
		JPanel p1=new JPanel();  

		p1.setLayout(null);
		p1.add(btnLoadTable);
		p1.add(scrollPane);
		tabbedPane.add("Workspace",p1); 
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 24));
		comboBox.setBounds(236, 38, 270, 35);
		comboBox.addItem("employee");
		comboBox.addItem("customer");
		comboBox.addItem("seller");
		comboBox.addItem("databaseuser");
		comboBox.setSelectedItem(null);
		p1.add(comboBox);
		
		JButton btnRemmove = new JButton("Remove Row");
		btnRemmove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRemmove.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnRemmove.setBounds(723, 37, 174, 36);
		p1.add(btnRemmove);

		p1.setBackground(new Color(0, 1, 32));
		p1.setForeground(new Color(0, 1, 32));
		tabbedPane.setBackground(new Color(0, 1, 32));
		
		JButton btnAdd = new JButton("Add Row");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				}catch (Exception ex) {
					
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAdd.setBounds(924, 37, 187, 36);
		p1.add(btnAdd);
		
		Panel panelSearch = new Panel();
		panelSearch.setBounds(10, 37, 203, 35);
		p1.add(panelSearch);
		
		JTextField textFieldTemp = RowFilterUtil.createRowFilter(table);
		
		panelSearch.add(textFieldTemp);
		textFieldTemp.setSize(150, 30);
		
		contentPane.add(tabbedPane);
		
		
		
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					p1.removeAll();
					p1.add(btnRemmove);
					p1.add(btnAdd);
					p1.add(btnLoadTable);
					p1.add(scrollPane);
					p1.add(comboBox);
					p1.add(panelSearch);
					
					String query = "select * from mydb.";
					query = query+comboBox.getSelectedItem().toString();
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					JTextField textField = RowFilterUtil.createRowFilter(table);
					
					panelSearch.removeAll();
					panelSearch.revalidate();
					panelSearch.repaint();
					panelSearch.add(textField);
					textField.setSize(150, 30);
					//p1.add(textField);
					textField.setColumns(10);
					
					p1.revalidate();
					p1.repaint();
				}catch (Exception ex) {
					
				}
			}
		});


	}
}
