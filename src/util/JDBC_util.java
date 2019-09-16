package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC_util {

	// [ Connection 생성 ]

	public static Connection getConnection() {
		
		// C:\lib\DBinfo.txt에서 정보 가져올꺼야
		// C:\lib\DBinfo2.txt에서 정보 가져올꺼야  for login
		
		Connection con = null;

		try {
			Properties p = new Properties();
			p.load(new FileInputStream("c:\\lib\\DBinfo.txt"));
//			p.load(new FileInputStream("d:\\Dropbox\\응용SW엔지니어링양성과정\\lib\\DBinfo.txt"));
			 // SCOTT/TIGER -> DBinfo.txt  /  admin/1234 -> DBinfo2.txt  /  hr/hr -> DBinfo3.txt 
			
			String driver = p.getProperty("driver");
			String url = p.getProperty("url"); 
			String user = p.getProperty("user"); 
			String pw = p.getProperty("pw");
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pw);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return con;
	}

	// [ 자원 반납 ]

	public static void close(Connection con, Statement st, ResultSet rs) {

		try {
			//connection을 열 때와 닫을 때 순서가 반대로 되야 함!
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
