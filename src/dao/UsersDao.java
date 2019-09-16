package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBC_util;
import vo.UsersVO;

public class UsersDao {

	// 회원 등록
	public int insertUser(UsersVO user) {
		String sql = "insert into users(id, password, name, role) values(?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;

		try {

			con = JDBC_util.getConnection();
			ps = con.prepareStatement(sql);
			// ? setting
			ps.setString(1, user.getId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getRole());

			// 실행 및 결과 값 handling
			result = ps.executeUpdate();
			
			System.out.printf("ID %s 님이 회원가입 하셨습니다.",user.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_util.close(con, ps, null);
		}
		return result;
	}
	// 회원 삭제
	
	public int deleteUser(String id) {
		
		String sql = "delete from users where id = ?";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = JDBC_util.getConnection();
			ps = con.prepareStatement(sql);
			
			// ? setting
			ps.setString(1, id);
			
			// 실행 및 결과 값 handling
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_util.close(con, ps, null);
		}
		return result;
	}
		
		
	
	
	// 회원 정보 수정
	public int updateUser(UsersVO user) {
		String sql = "update users set password = ?, name = ?, role = ? where id = ?";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;

		try {

			con = JDBC_util.getConnection();
			ps = con.prepareStatement(sql);
			// ? setting
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getName());
			ps.setString(3, user.getRole());
			ps.setString(4, user.getId());

			// 실행 및 결과 값 handling
			result = ps.executeUpdate();
			System.out.printf("ID %s님이 회원 정보를 수정 하셨습니다.",user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_util.close(con, ps, null);
		}
		return result;
	}

	// 로그인
	public UsersVO login(String id, String pw) {
		
		String sql = "select * from users where id = ? and password = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		UsersVO data = null;
		
		try {

			con = JDBC_util.getConnection();
			ps = con.prepareStatement(sql);
			// ? setting
			ps.setString(1, id);
			ps.setString(2, pw);

			// 실행 및 결과 값 handling
			rs = ps.executeQuery();

			if(rs.next()) {	
				data= new UsersVO();
				data.setId(rs.getString("id"));
				data.setPassword(rs.getString("password"));
				data.setName(rs.getString("name"));
				data.setRole(rs.getString("role"));
			} else {
				System.out.println("로그인 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_util.close(con, ps, rs);
		}
		return data;
	}
}
