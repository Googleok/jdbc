package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

	public List<EmployeeVo> getList(String keyword){
		List<EmployeeVo> result = new ArrayList<EmployeeVo>();
		
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC Driver (MariaDB) 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.123:3307/employees";
			conn = DriverManager.getConnection(url, "hr", "hr");
		
			// 3. statement 객체 생성
			//stmt = conn.createStatement();
	
			// 3-1 Prepared Statement 객체 생성
			
			
			String sql = 
					"select emp_no, first_name, last_name, hire_date" + 
					"	from employees" + 
					"   where first_name like ?" + 
					"		or last_name like ?";
			
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
		
			// 5. SQL문 실행
			rs = pstmt.executeQuery();
			
			// 6. 결과 가져오기
			while ( rs.next() ) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);				
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setHireDate(hireDate);
				
				result.add(vo);
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
				if( pstmt != null) {
					pstmt.close();
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
