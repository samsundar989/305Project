import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Scanner;
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
		
		JTable itemsTable = new JTable();
		itemsTable.setModel(DbUtils.resultSetToTableModel(rs));
		JScrollPane itemsScrollPane = new JScrollPane();
		itemsScrollPane.setBounds(0, 109, 1220, 402);
		itemsScrollPane.setOpaque(false);
		itemsScrollPane.setViewportView(itemsTable);
		
		itemsTable.setOpaque(false);
		((DefaultTableCellRenderer)itemsTable.getDefaultRenderer(Object.class)).setOpaque(false);
		
		itemsTable.setFont(new Font("Tahoma", Font.BOLD, 22));
		itemsTable.setForeground(Color.white);
		
		itemsTable.setSelectionForeground(new Color(1, 159, 254));
		
		itemsTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 28));
		
		itemsTable.setRowHeight(30);
		
		textField = RowFilterUtil.createRowFilter(itemsTable);//JTextField();
		textField.setBounds(25, 52, 146, 26);
		p1.add(textField);
		textField.setColumns(10);
		
		itemsScrollPane.setOpaque(false);
		itemsScrollPane.getViewport().setOpaque(false);
		p1.setLayout(null);
		p1.add(itemsScrollPane);
		
		JButton btnAddRow = new JButton("Add Row");
		btnAddRow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel m = (DefaultTableModel) itemsTable.getModel();
					Vector row = new Vector();
					for(int i = 0; i < itemsTable.getColumnCount(); i++) {
						row.add("Enter data");
					}
					m.addRow(row);
					m.fireTableDataChanged();
					
				}catch (Exception ex) {
					
				}
			}
		});
		btnAddRow.setBounds(401, 40, 115, 29);
		p1.add(btnAddRow);
		
		JButton btnSaveRow = new JButton("Save Row");
		btnSaveRow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSaveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = connection.createStatement();
				    String sql = "INSERT INTO mydb.item";
					sql+=" VALUES ('"+itemsTable.getValueAt(itemsTable.getSelectedRow(), 0)+"'";
					for(int i = 1; i < itemsTable.getColumnCount(); i++) {
						sql+=", '"+itemsTable.getValueAt(itemsTable.getSelectedRow(), i)+"'";
					}
					sql+=");";
					stmt.executeUpdate(sql);
					itemsTable.clearSelection();
				}catch (Exception ex) {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					ex.printStackTrace(pw);
					String sStackTrace = sw.toString(); 
					sStackTrace = sStackTrace.split("\n")[0];
					JOptionPane.showMessageDialog(null, sStackTrace);
					// remove selected row
					DefaultTableModel m = (DefaultTableModel) itemsTable.getModel();
					m.removeRow(itemsTable.getSelectedRow());
					m.fireTableDataChanged();
					itemsTable.clearSelection();
				}
			}
		});
		btnSaveRow.setBounds(531, 40, 155, 29);
		p1.add(btnSaveRow);
		
		JButton btnSeeR = new JButton("See Reviews");
		btnSeeR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSeeR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(itemsTable.getSelectionModel().isSelectionEmpty() == true) {
						JOptionPane.showMessageDialog(null, "Please select item for which you\nwould like to see reviews");
					}else {
						PrintWriter out = new PrintWriter ("review_data.txt");
						out.print(itemsTable.getValueAt(itemsTable.getSelectedRow(), 0)+" "+
								(itemsTable.getValueAt(itemsTable.getSelectedRow(), 3)));
						out.close();
						ReviewSeller rd = new ReviewSeller();
						rd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			            rd.setVisible(true);
					}
					
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnSeeR.setBounds(701, 40, 207, 29);
		p1.add(btnSeeR);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setBounds(15, 16, 69, 20);
		p1.add(lblSearch);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Statement stmt = connection.createStatement();
					String sql = "DELETE FROM mydb.item";
					sql+=" WHERE ";
					sql+=itemsTable.getColumnName(0)+" = '"+itemsTable.getValueAt(itemsTable.getSelectedRow(), 0)+"'";
					for(int i = 1; i < itemsTable.getColumnCount(); i++) {
						sql+=" AND "+itemsTable.getColumnName(i)+" = '"+itemsTable.getValueAt(itemsTable.getSelectedRow(), i)+"'";
					}

				    stmt.executeUpdate(sql);
				    
				    // redraw table
				    p1.removeAll();
					p1.add(btnRemove);
					p1.add(btnAddRow);
					p1.add(itemsScrollPane);
					p1.add(btnSeeR);
					p1.add(textField);
					p1.add(lblSearch);
					p1.add(btnSaveRow);
					
					String query = "select * from mydb.item";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					itemsTable.setModel(DbUtils.resultSetToTableModel(rs));
					
					JTextField textField = RowFilterUtil.createRowFilter(itemsTable);
					
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
		
		JButton btnShowOnlyMy = new JButton("Show Only My Items");
		btnShowOnlyMy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnShowOnlyMy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner sc = new Scanner(new File("username_info.txt"));
					int id = sc.nextInt();
					String query = "select * from mydb.item ";
					query += "where SellerID=" + id;
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					itemsTable.setModel(DbUtils.resultSetToTableModel(rs));

				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnShowOnlyMy.setBounds(923, 40, 251, 29);
		p1.add(btnShowOnlyMy);
		
		JPanel p2 = new JPanel();
		tabbedPane.add("Orders",p2); 
		
		try {
			Scanner sc = new Scanner(new File("username_info.txt"));
			int id = sc.nextInt();
			query = "select * from mydb.orders ";
			query += "where SellerID=" + id;

		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		
		JTable ordersTable = new JTable();
		ordersTable.setModel(DbUtils.resultSetToTableModel(rs));
		JScrollPane ordersScrollPane = new JScrollPane();
		ordersScrollPane.setBounds(0, 109, 1220, 402);
		ordersScrollPane.setOpaque(false);
		ordersScrollPane.setViewportView(ordersTable);
		
		ordersTable.setOpaque(false);
		((DefaultTableCellRenderer)ordersTable.getDefaultRenderer(Object.class)).setOpaque(false);
		
		ordersTable.setFont(new Font("Tahoma", Font.BOLD, 22));
		ordersTable.setForeground(Color.white);
		
		ordersTable.setSelectionForeground(new Color(1, 159, 254));
		
		ordersTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 28));
		
		ordersTable.setRowHeight(30);
		
		p2.setBackground(new Color(0,1,32));
		p2.setForeground(new Color(0,1,32));
		
		ordersScrollPane.setOpaque(false);
		ordersScrollPane.getViewport().setOpaque(false);
		p2.setLayout(null);
		p2.add(ordersScrollPane);
		
		
		JPanel p3 = new JPanel();
		tabbedPane.add("Shipments",p3); 
		
		query = "select * from mydb.shipment;";
		//query += "where sellerID = (the seller ID of current User);";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		
		JTable shipmentsTable = new JTable();
		shipmentsTable.setModel(DbUtils.resultSetToTableModel(rs));
		JScrollPane shipmentsScrollPane = new JScrollPane();
		shipmentsScrollPane.setBounds(0, 109, 1220, 402);
		shipmentsScrollPane.setOpaque(false);
		shipmentsScrollPane.setViewportView(shipmentsTable);
		
		shipmentsTable.setOpaque(false);
		((DefaultTableCellRenderer)shipmentsTable.getDefaultRenderer(Object.class)).setOpaque(false);
		
		shipmentsTable.setFont(new Font("Tahoma", Font.BOLD, 22));
		shipmentsTable.setForeground(Color.white);
		
		shipmentsTable.setSelectionForeground(new Color(1, 159, 254));
		
		shipmentsTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 28));
		shipmentsTable.getColumnModel().getColumn(0).setHeaderValue("Tracking#");
		shipmentsTable.getColumnModel().getColumn(1).setHeaderValue("Shipment");
		shipmentsTable.getColumnModel().getColumn(2).setHeaderValue("Address");
		shipmentsTable.getColumnModel().getColumn(3).setHeaderValue("Type");
		shipmentsTable.getColumnModel().getColumn(4).setHeaderValue("Cost");
		shipmentsTable.getTableHeader().setResizingAllowed(true);
		
		shipmentsTable.setRowHeight(30);
		
		p3.setBackground(new Color(0,1,32));
		p3.setForeground(new Color(0,1,32));
		
		shipmentsScrollPane.setOpaque(false);
		shipmentsScrollPane.getViewport().setOpaque(false);
		p3.setLayout(null);
		p3.add(shipmentsScrollPane);
		
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





