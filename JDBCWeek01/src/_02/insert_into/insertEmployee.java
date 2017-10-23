package _02.insert_into;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class insertEmployee {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement st = null;
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcmysql","admin","1234");
			st=conn.createStatement();
			String sql="insert into employees (first_name,last_name,email) values ('Java','Turk','javaci@gmail.com')";
			st.executeUpdate(sql);
			
			System.out.println("Insert tamamlandi");
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if (st != null) {
				st.close();
			}
			
			if (conn != null) {
				conn.close();
			}
		}			



	}

}
