import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Copy the following to local terminal if you're using home machine
// ssh -l h3b0b -L localhost:1522:dbhost.ugrad.cs.ubc.ca:1522 remote.ugrad.cs.ubc.ca

public class Main {
	public static void main(String[] args) throws SQLException {
		Connection con = null;
		
		try {
		   System.out.println("Loading driver ...");

		   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

		   System.out.println("Driver loaded.");
	    } catch (Exception e) {
		   System.out.println("Unable to load driver\n" + e);
		   System.exit(-1);
	    }
		
		try {  
		   System.out.println("Connecting to NetDB2 ...");
	 
		   con = DriverManager.getConnection(
					  "jdbc:oracle:thin:@localhost:1522:ug", "ora_h3b0b", "a14558143");
	 
		   System.out.println("Connection successful.");
		    
	    } catch( Exception e ) {
		   System.out.println("Connection failed\n" + e);
	    }
		
		try {
		   System.out.println("Selecting branch_name from branch table");

		   // con is a Connection object 
			Statement stmt = con.createStatement();
			
//			int rowCount = stmt.executeUpdate("INSERT INTO branch VALUES (20, 'Richmond Main', " +
//	      "'18122 No.5 Road', 'Richmond', 5252738)");
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM branch");
			while(rs.next()) {
				System.out.println(rs.getString("branch_name"));
			}
	    } catch (SQLException ex) {
		   System.out.println(ex);
	    }
		
	}	
}