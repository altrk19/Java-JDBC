package _01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			//Class.forName("com.mysql.jdbc.Driver"); //driver'� dinamik olarak y�klemeyi sa�lar.  JDBC'nin 6. versiyonu ile bunu yazmaya gerek yoktur. 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcmysql", "admin", "1234");  // JDBC DriverManager sayesinde farkl� veritabanlar�na baglanabilmekteyiz.
        
		    st = conn.createStatement();        //Statement : Bu fonksiyon her �a��r�ld���nda SQL�e gidip i�lem yapan fonksiyondur. O an �al���r ve biter
		    
		    String sorgu = "select * from student";
		    
			rs = st.executeQuery(sorgu);
        
			while (rs.next()){
				String s_ad = rs.getString("ad");
				String s_soyad = rs.getString("soyad");
          
				System.out.print(s_ad + " " + s_soyad + "\n");
          
			}
			st.close();
			}catch (Exception e){
				System.err.println("Hata ! ");
				System.err.println(e.getMessage());
			}
			finally {
				if (rs != null) {
					rs.close();
				}
			
				if (st != null) {
					st.close();
				}
			
				if (conn != null) {
					conn.close();
				}
			}
	}
}
