package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest {

	public static void main(String[] args) {
		boolean result = delete(6L);
		if(result) {
			System.out.println("삭제 성공!");
		}
	}
	
	public static boolean delete(Long no) {
		
		boolean result = false;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// 1. JDBC Driver (MariaDB) 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.123:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		
			// 3. statement 객체 생성
			stmt = conn.createStatement();
			
			// 4. SQL문 실행
			String sql = "delete from department where no=" + no;
		
			int count = stmt.executeUpdate(sql);
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading failed..." + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
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
		return result;
	}

}
