import java.sql.*;
import javax.swing.*;

public class Driver {
	Connection conn = null;

	public static Connection dbConnector() throws SQLException {//void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Project305!");
		//Statement myStmt = myConn.createStatement();
		
		//JOptionPane.showMessageDialog(null, "Connection Successfull");
		
		return myConn;
		
		//myStmt.executeQuery("INSERT INTO employee VALUES (111111111, NULL, NULL, NULL)");
		
//		ResultSet myRs = myStmt.executeQuery("select * from mydb.employee");
//		
//		while(myRs.next()) {
//			System.out.println(myRs.getString(1));
//		}
		

	}

}
