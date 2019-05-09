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
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;
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
		
		JTable table = new JTable();
		table.getTableHeader().setResizingAllowed(true);
		table.setAutoCreateRowSorter(true);
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
		
		JButton btnAddReview = new JButton("Add Review");
		btnAddReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					Vector row = new Vector();
					row.add(isbn);
					row.add("Enter data");
					row.add(sellerID);
					for(int i = 3; i < table.getColumnCount(); i++) {
						row.add("Enter data");
					}
					m.addRow(row);
					m.fireTableDataChanged();
					
				}catch (Exception ex) {
					
				}
			}
		});
		btnAddReview.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAddReview.setBounds(25, 29, 194, 36);
		panel.add(btnAddReview);
		
		JButton btnSaveReview = new JButton("Save Review");
		btnSaveReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = connection.createStatement();
				    String sql = "INSERT INTO mydb.Review";
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
		btnSaveReview.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSaveReview.setBounds(234, 29, 177, 36);
		panel.add(btnSaveReview);
		
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