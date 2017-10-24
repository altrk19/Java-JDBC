package _02.stored_procedure_in_parameters;
import java.sql.*;
public class IncreaseSalariesForDepartment {

	public static void main(String[] args) throws SQLException {
		Connection conn=null;
		CallableStatement myStmt=null;
		
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","admin","1234");
			String theDepartment="Engineering";
			int theIncreaseAmount=10000;
			
			System.out.println("Salaries BEFORE");
			showSalaries(conn,theDepartment);
			
			myStmt=conn.prepareCall("{call increase_salaries_for_department(? , ?)}");
			myStmt.setString(1, theDepartment);
			myStmt.setDouble(2, theIncreaseAmount);
			
			System.out.println("\n\nCalling stored procedure.  increase_salaries_for_department('" + theDepartment + "', " + theIncreaseAmount + ")");
			myStmt.execute();
			System.out.println("Finished calling stored procedure");

			// Show salaries AFTER
			System.out.println("\n\nSalaries AFTER\n");
			showSalaries(conn, theDepartment);

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(conn, myStmt, null);
		}
	}

	private static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// Prepare statement
			myStmt = myConn
					.prepareStatement("select * from employees where department=?");

			myStmt.setString(1, theDepartment);
			
			// Execute SQL query
			myRs = myStmt.executeQuery();

			// Process result set
			while (myRs.next()) {
				String lastName = myRs.getString("last_name");
				String firstName = myRs.getString("first_name");
				double salary = myRs.getDouble("salary");
				String department = myRs.getString("department");
				System.out.println(firstName+" "+lastName+" "+department+" "+salary);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myStmt, myRs);
		}

	}

	private static void close(Connection myConn, Statement myStmt,
			ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

	private static void close(Statement myStmt, ResultSet myRs)
			throws SQLException {

		close(null, myStmt, myRs);
	}
}
