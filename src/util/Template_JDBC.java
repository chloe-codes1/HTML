package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBC_util;

public class Template_JDBC {

	public static void main(String[] args) {

		String sql = "";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
//		int result = 0;
		
		try {
			
			con = JDBC_util.getConnection();
			ps = con.prepareStatement(sql);
			 // ? setting
			
			// 실행 및 결과 값 handling
			ps.executeQuery();
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_util.close(con, ps, rs);
		}
	}
}
