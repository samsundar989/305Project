import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;
import java.awt.Font;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.Image;
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
//		table.setModel(DbUtils.resultSetToTableModel(rs));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 109, 1220, 402);
		scrollPane.setOpaque(false);
		scrollPane.setViewportView(table);
		
		DefaultTableModel model_request = ((DefaultTableModel) DbUtils.resultSetToTableModel(rs));
		DefaultTableModel model = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int column) {
                if (column==4) return ImageIcon.class;
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        model.addColumn("ISBN");
        model.addColumn("Genre");
        model.addColumn("Price");
        model.addColumn("SellerID");
        model.addColumn("Picture");
		for(int i=0; i<model_request.getRowCount(); i++) {
			Vector v = new Vector();
			for(int j=0; j<model.getColumnCount(); j++) {
				v.add(null);
			}
			model.addRow(v);
			for(int j=0; j<model_request.getColumnCount(); j++) {
				model.setValueAt(model_request.getValueAt(i, j).toString(), i, j);
			}
			ImageIcon icon = new ImageIcon("untitled.png");
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			model.setValueAt(icon, i, model.getColumnCount()-1);
		}
		table.setModel(model);

		
		table.setOpaque(false);
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
		
		table.setFont(new Font("Tahoma", Font.BOLD, 22));
		table.setForeground(Color.white);
		
		table.setSelectionForeground(new Color(1, 159, 254));
		
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 28));
		
		table.setRowHeight(70);
		
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
		
		
		String id = "";
		
		try {
			Scanner sc = new Scanner(new File("username_info.txt"));
			id = sc.next(); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//TODO Add to Shopping Cart 
		JButton btnAddToShopping = new JButton("Add To Shopping Cart");
		
		btnAddToShopping.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAddToShopping.setBounds(305, 40, 300, 36);
		p1.add(btnAddToShopping);
		
		// TODO: Implement review 
		JButton btnLookAt = new JButton("See/Leave review");
		btnLookAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(table.getSelectionModel().isSelectionEmpty() == true) {
						JOptionPane.showMessageDialog(null, "Please select item for which you\nwould like to see reviews");
					}else {
						PrintWriter out = new PrintWriter ("review_data.txt");
						out.print(table.getValueAt(table.getSelectedRow(), 0)+" "+
								(table.getValueAt(table.getSelectedRow(), 3) +" "));
						out.close();
						Review rd = new Review();
						rd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			            rd.setVisible(true);
					}
					
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnLookAt.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLookAt.setBounds(636, 40, 300, 36);
		p1.add(btnLookAt);
		
		
		JPanel p2=new JPanel();
		p2.setBackground(new Color(0, 1, 32));
		p2.setForeground(new Color(0, 1, 32));
		
		
		
		String query2 = "select * from mydb.shoppingcart ";
		query2 += "where CustomerID=" + id;
		PreparedStatement pst2 = connection.prepareStatement(query2);
		ResultSet rs2 = pst2.executeQuery();
		
		JTable table2 = new JTable();
		table2.setModel(DbUtils.resultSetToTableModel(rs2));
		JScrollPane cartScrollPane = new JScrollPane();
		cartScrollPane.setBounds(0, 109, 1220, 402);
		cartScrollPane.setOpaque(false);
		cartScrollPane.setViewportView(table2);
		
		table2.setOpaque(false);
		((DefaultTableCellRenderer)table2.getDefaultRenderer(Object.class)).setOpaque(false);
		
		table2.setFont(new Font("Tahoma", Font.BOLD, 22));
		table2.setForeground(Color.white);
		
		table2.setSelectionForeground(new Color(1, 159, 254));
		
		table2.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 28));
		
		table2.setRowHeight(30);
		
		cartScrollPane.setOpaque(false);
		cartScrollPane.getViewport().setOpaque(false);
		p2.setLayout(null);
		p2.add(cartScrollPane);
		
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
		btnPurchase.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnPurchase.setBounds(692, 40, 300, 36);
		p2.add(btnPurchase);
		
		JButton btnRemove = new JButton("Remove from shopping cart");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = connection.createStatement();
					String sql = "DELETE FROM mydb.shoppingcart";
					sql+=" WHERE ";
					sql+=table2.getColumnName(0)+" = '"+table2.getValueAt(table2.getSelectedRow(), 0)+"'";

				    stmt.executeUpdate(sql);
				    int id = 0;
				    try {
						Scanner sc = new Scanner(new File("username_info.txt"));
						id = sc.nextInt(); 
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				    
				    String querys = "select * from mydb.shoppingcart ";
					querys += "where CustomerID=" + id;
					PreparedStatement pst = connection.prepareStatement(querys);
					ResultSet rs = pst.executeQuery();
					table2.setModel(DbUtils.resultSetToTableModel(rs));
				    
				} catch (Exception ex) {
					if(ex instanceof SQLException){
						ex.printStackTrace();
					}
					System.out.println("Choose table and row");
				}
			}
		});

		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnRemove.setBounds(286, 40, 364, 36);
		p2.add(btnRemove);
		
		
		tabbedPane.add("Shopping Cart",p2); 
		JPanel p3=new JPanel();
		p3.setBackground(new Color(0, 1, 32));
		p3.setForeground(new Color(0, 1, 32));
		tabbedPane.add("Orders",p3); 
		
		int idNum = 0;
		
		try {
			Scanner sc = new Scanner(new File("username_info.txt"));
			idNum = sc.nextInt();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String query3 = "select * from mydb.orders";
		query3 += " where CustomerID=" + idNum;
		PreparedStatement pst3 = connection.prepareStatement(query3);
		ResultSet rs3 = pst3.executeQuery();
		
		JTable table3 = new JTable();
		table3.setModel(DbUtils.resultSetToTableModel(rs3));
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(0, 109, 1220, 402);
		scrollPane3.setOpaque(false);
		scrollPane3.setViewportView(table3);
		
		table3.setOpaque(false);
		((DefaultTableCellRenderer)table3.getDefaultRenderer(Object.class)).setOpaque(false);
		
		table3.setFont(new Font("Tahoma", Font.BOLD, 22));
		table3.setForeground(Color.white);
		
		table3.setSelectionForeground(new Color(1, 159, 254));
		
		table3.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 28));
		
		table3.setRowHeight(30);
		
		scrollPane3.setOpaque(false);
		scrollPane3.getViewport().setOpaque(false);
		p3.setLayout(null);
		p3.add(scrollPane3);
		
		JLabel label3 = new JLabel("Search");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Tahoma", Font.BOLD, 22));
		label3.setBounds(25, 16, 129, 20);
		p3.add(label3);
		
		JButton btnRefresh = new JButton("Refresh Orders");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner sc = new Scanner(new File("username_info.txt"));
					int id = sc.nextInt();
					
					String query2 = "SELECT * FROM mydb.orders WHERE CustomerID=" + id;
		    		PreparedStatement pst5 = connection.prepareStatement(query2);
		    		ResultSet rs4 = pst5.executeQuery();
		    		
		    		table3.setModel(DbUtils.resultSetToTableModel(rs4));
					
				} catch (Exception ex) {
					ex.printStackTrace();
					
				}
			}
		});

		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnRefresh.setBounds(286, 40, 364, 36);
		p3.add(btnRefresh);
		
		textField = RowFilterUtil.createRowFilter(table);//JTextField();
		textField.setBounds(25, 52, 146, 26);
		p3.add(textField);
		textField.setColumns(10);
		
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
		
		btnAddToShopping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					String isbn = ""+table.getValueAt(table.getSelectedRow(), 0);
					String price = (String) table.getValueAt(table.getSelectedRow(), 2);
					int sellerID = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 3));
					
					Scanner sc = new Scanner(new File("username_info.txt"));
					int customerID = sc.nextInt();
					
					String query = "INSERT INTO mydb.shoppingcart"
							+ " VALUES ("+isbn+","+price+","+customerID+","+sellerID+")";
					query += "on duplicate key update price=" + price;
					PreparedStatement pst = connection.prepareStatement(query);
					
					Statement statement = connection.createStatement();
					statement.executeUpdate(query);
					
					ResultSet rs3 = pst2.executeQuery();
					table2.setModel(DbUtils.resultSetToTableModel(rs3));
				} catch(Exception t) {
					t.printStackTrace();
				}
				
			}
		});
		
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					Purchase rd = new Purchase();
					rd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		            rd.setVisible(true);
		            
		            int id = 0;
		    		Scanner sc = new Scanner(new File("username_info.txt"));
		    		id = sc.nextInt(); 
		    		
		    		
					String toDelete = "DELETE FROM mydb.shoppingcart ";
					toDelete += "where CustomerID=" + id;
		    		PreparedStatement pst3 = connection.prepareStatement(toDelete);
		    		pst3.executeUpdate();
		    		
					String query = "SELECT * FROM mydb.shoppingcart WHERE CustomerID=" + id;
		    		PreparedStatement pst4 = connection.prepareStatement(query);
		    		ResultSet rs3 = pst4.executeQuery();
		    		
		    		table2.setModel(DbUtils.resultSetToTableModel(rs3));
		    		
		    		
		    		
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		
	}

}