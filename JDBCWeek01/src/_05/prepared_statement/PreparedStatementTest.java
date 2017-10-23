package _05.prepared_statement;
import java.sql.*;

public class PreparedStatementTest {
	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// 1. Get a connection to database
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","admin","1234");
		
			// 2. Prepare statement
			ps =  conn.prepareStatement("select * from employees where salary > ? and department=?");
			
			// 3. Set the parameters
			ps.setDouble(1, 50000);
			ps.setString(2, "Engineering");
			
			// 4. Execute SQL query
			rs = ps.executeQuery();
			
			
			// 5. Display the result set
			display(rs);
		
			//
			// Reuse the prepared statement:  salary > 25000,  department = HR
			//

			System.out.println("\n\nReuse the prepared statement:  salary > 25000,  department = HR");
			
			// 6. Set the parameters
			ps.setDouble(1, 25000);
			ps.setString(2, "HR");
			
			// 7. Execute SQL query
			rs = ps.executeQuery();
			
			// 8. Display the result set
			display(rs);


		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (rs != null) {
				rs.close();
			}
			
			if (ps != null) {
				ps.close();
			}
			
			if (conn != null) {
				conn.close();
			}
		}
	}

	private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");
			System.out.println(firstName+" "+lastName+" "+salary+" "+department);
		}
	}
}
