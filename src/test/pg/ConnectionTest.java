package test.pg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		
		Connection conn = null;
		
		try {
			// 1. JDBC Driver (MariaDB) 로딩
			Class.forName("org.postgresql.Driver");
			
			// 2. 연결하기
			String url = "jdbc:postgresql://192.168.1.123:5432/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		
			System.out.println("연결성공");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading failed..." + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
