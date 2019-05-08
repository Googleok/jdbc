package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

   public static void main(String[] args) {
      Connection conn = null;
      try {
         // 1. JDBC Driver(MariaDB) 로딩 (직접 코드로)
         Class.forName("driver.MyDriver");

         // 2. 연결하기 url 필요함
         String url = "jdbc:mydb://192.168.1.123:3307/webdb";
         conn = DriverManager.getConnection(url, "webdsb", "webdb");
         
         System.out.println("연결 성공 :" + conn);
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버 로딩 실패" + e);
      } catch (SQLException e) {
         System.out.println("error" + e);
      }
      finally {
         try {
            if(conn != null) {
               conn.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }

}