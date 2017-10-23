package _03.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateEmployee {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement st = null;
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcmysql","admin","1234");
			
			st=conn.createStatement();
			
			String sql="update employees set email='javaci61@gmail.com' where id=4";
			
			int rowsAffected = st.executeUpdate(sql);
			System.out.println("Rows affected: " + rowsAffected);
			System.out.println("Update tamamlandi");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Ooops");
		}finally {
			if (st != null) {
				st.close();
			}
			
			if (conn != null) {
				conn.close();
			}
	

		}
	}
}


