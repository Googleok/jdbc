package test.pg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC Driver (MariaDB) 로딩
			Class.forName("org.postgresql.Driver");
			
			// 2. 연결하기
			String url = "jdbc:postgresql://192.168.1.123:5432/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		
			// 3. statement 객체 생성
			stmt = conn.createStatement();
			
			// 4. SQL문 실행
			String sql = "select no, name from author order by no asc";
			rs = stmt.executeQuery(sql);
			
			// 5. 결과 가져오기
			while ( rs.next() ) {
				Long no = rs.getLong(1);
				
				String name = rs.getString(2);
				System.out.println(no + " : " + name);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading failed..." + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if( rs != null) {
					rs.close();
				}
				if( stmt != null) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
