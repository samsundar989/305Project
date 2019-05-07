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
import java.io.PrintWriter;
import java.io.StringWriter;
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
		btnLoadTable.setBounds(305, 37, 163, 36);
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
		comboBox.setBounds(15, 38, 270, 35);
		comboBox.addItem("employee");
		comboBox.addItem("customer");
		comboBox.addItem("seller");
		comboBox.addItem("databaseuser");
		comboBox.setSelectedItem(null);
		p1.add(comboBox);
		
		JButton btnRemmove = new JButton("Remove Row");
		btnRemmove.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnRemmove.setBounds(485, 37, 174, 36);
		p1.add(btnRemmove);

		p1.setBackground(new Color(0, 1, 32));
		p1.setForeground(new Color(0, 1, 32));
		tabbedPane.setBackground(new Color(0, 1, 32));
		
		JButton btnAdd = new JButton("Add Row");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAdd.setBounds(674, 37, 129, 36);
		p1.add(btnAdd);
		
		JTextField textFieldTemp = RowFilterUtil.createRowFilter(table);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setBounds(1016, 16, 129, 20);
		p1.add(lblSearch);
		
		Panel panelSearch = new Panel();
		panelSearch.setBounds(1004, 38, 206, 46);
		p1.add(panelSearch);
		
		panelSearch.add(textFieldTemp);
		
		JButton btnSaveRow = new JButton("Save Row");
		btnSaveRow.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSaveRow.setBounds(818, 37, 154, 36);
		p1.add(btnSaveRow);
		textFieldTemp.setSize(150, 30);
		
		contentPane.add(tabbedPane);
		
		JButton btnSignOut = new JButton("Sign out");
		btnSignOut.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSignOut.addActionListener(new ActionListener() {
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
		btnSignOut.setBounds(1160, 26, 157, 29);
		contentPane.add(btnSignOut);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					Vector row = new Vector();
					for(int i = 0; i < table.getColumnCount(); i++) {
						row.add("Enter data");
					}
					m.addRow(row);
					m.fireTableDataChanged();
					
				}catch (Exception ex) {
					
				}
			}
		});
		
		btnSaveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = connection.createStatement();
				    String sql = "INSERT INTO mydb."+comboBox.getSelectedItem().toString();
					sql+=" VALUES ('"+table.getValueAt(table.getSelectedRow(), 0)+"'";
					for(int i = 1; i < table.getColumnCount(); i++) {
						sql+=", '"+table.getValueAt(table.getSelectedRow(), i)+"'";
					}
					sql+=");";
					stmt.executeUpdate(sql);
					table.clearSelection();
				}catch (Exception ex) {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					ex.printStackTrace(pw);
					String sStackTrace = sw.toString(); 
					sStackTrace = sStackTrace.split("\n")[0];
					JOptionPane.showMessageDialog(null, sStackTrace);
					// remove selected row
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					m.removeRow(table.getSelectedRow());
					m.fireTableDataChanged();
					table.clearSelection();
				}
			}
		});
		
		btnRemmove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Statement stmt = connection.createStatement();
					String sql = "DELETE FROM mydb."+comboBox.getSelectedItem().toString();
					sql+=" WHERE ";
					sql+=table.getColumnName(0)+" = '"+table.getValueAt(table.getSelectedRow(), 0)+"'";
					for(int i = 1; i < table.getColumnCount(); i++) {
						sql+=" AND "+table.getColumnName(i)+" = '"+table.getValueAt(table.getSelectedRow(), i)+"'";
					}
					//System.out.println(sql);
				    stmt.executeUpdate(sql);
				    
				    
				    // redraw table
				    p1.removeAll();
					p1.add(btnRemmove);
					p1.add(btnAdd);
					p1.add(btnLoadTable);
					p1.add(scrollPane);
					p1.add(comboBox);
					p1.add(panelSearch);
					p1.add(lblSearch);
					p1.add(btnSaveRow);
					
					String query = "select * from mydb.";
					query = query+comboBox.getSelectedItem().toString();
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					JTextField textField = RowFilterUtil.createRowFilter(table);
					
					panelSearch.removeAll();
					panelSearch.revalidate();
					panelSearch.repaint();
					textField.setSize(150, 30);
					panelSearch.add(textField);
					
					p1.revalidate();
					p1.repaint();
				    
				    
				    
				} catch (Exception e) {
					// TODO Auto-generated catch block
					if(e instanceof SQLException){
						e.printStackTrace();
					}
					System.out.println("Choose table and row");
				}
			}
		});
		
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
					p1.add(lblSearch);
					p1.add(btnSaveRow);
					
					String query = "select * from mydb.";
					query = query+comboBox.getSelectedItem().toString();
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					JTextField textField = RowFilterUtil.createRowFilter(table);
					
					panelSearch.removeAll();
					panelSearch.revalidate();
					panelSearch.repaint();
					textField.setSize(150, 30);
					panelSearch.add(textField);
					
					p1.revalidate();
					p1.repaint();
				}catch (Exception ex) {
					
				}
			}
		});


	}
}
