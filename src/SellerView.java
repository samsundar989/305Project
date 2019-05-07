import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.Font;
import java.awt.Panel;

public class SellerView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String sellerID;

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
		
		tabbedPane.setBounds(6, 50, 1225, 545);
		JPanel p1=new JPanel();  
		p1.setBackground(new Color(0, 1, 32));
		p1.setForeground(new Color(0, 1, 32));
		tabbedPane.add("Items",p1); 
		
		//String ID = Login.ID;
		
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
		
		textField = RowFilterUtil.createRowFilter(table);//JTextField();
		textField.setBounds(25, 52, 146, 26);
		p1.add(textField);
		textField.setColumns(10);
		
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		p1.setLayout(null);
		p1.add(scrollPane);
		
		JButton btnAddRow = new JButton("Add Row");
		btnAddRow.addActionListener(new ActionListener() {
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
		btnAddRow.setBounds(442, 40, 115, 29);
		p1.add(btnAddRow);
		
		JButton btnSaveRow = new JButton("Save Row");
		btnSaveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = connection.createStatement();
				    String sql = "INSERT INTO mydb.item";
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
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Statement stmt = connection.createStatement();
					String sql = "DELETE FROM mydb.item";
					sql+=" WHERE ";
					sql+=table.getColumnName(0)+" = '"+table.getValueAt(table.getSelectedRow(), 0)+"'";
					for(int i = 1; i < table.getColumnCount(); i++) {
						sql+=" AND "+table.getColumnName(i)+" = '"+table.getValueAt(table.getSelectedRow(), i)+"'";
					}

				    stmt.executeUpdate(sql);
				    
				    // redraw table
				    p1.removeAll();
					p1.add(btnRemove);
					p1.add(btnAddRow);
					p1.add(scrollPane);
					p1.add(btnShowOnlyMy);
					p1.add(textField);
					p1.add(lblSearch);
					p1.add(btnSaveRow);
					
					String query = "select * from mydb.item";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					JTextField textField = RowFilterUtil.createRowFilter(table);
					
					//panelSearch.removeAll();
					//panelSearch.revalidate();
					//panelSearch.repaint();
					textField.setSize(150, 30);
					//panelSearch.add(textField);
					
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
		
		btnRemove.setBounds(271, 40, 115, 29);
		p1.add(btnRemove);
		
		JPanel p2=new JPanel();
		tabbedPane.add("Orders",p2); 
		JPanel p3=new JPanel();
		tabbedPane.add("Shipmennts",p3); 
		
		contentPane.add(tabbedPane);
		
		JButton signOutButton = new JButton("Sign out");
		signOutButton.addActionListener(new ActionListener() {
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
		signOutButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		signOutButton.setBounds(1121, 34, 157, 29);
		contentPane.add(signOutButton);
		
	}
}






