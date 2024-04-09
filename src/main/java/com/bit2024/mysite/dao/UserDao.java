package com.bit2024.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bit2024.mysite.vo.UserVo;

public class UserDao {

	public Boolean insert(UserVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 0. JDBC Driver Loading
			Class.forName("org.mariadb.jdbc.Driver2");

			// 1. 연결: (서버IP+port+dbname) 연결URL, 사용자아이디, 비밀번호
			String url = "jdbc:mariadb://192.168.0.112:3307/webdb?chatset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 2. sql 준비
			String sql = "insert into user values(null, ?, ?, password(?), ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 3. 파라미터 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
		
			// 4. sql 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading Fail:" + e);
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public UserVo findByEmailAndPassword(String email, String password) {
		UserVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 0. JDBC Driver Loading
			Class.forName("org.mariadb.jdbc.Driver");

			// 1. 연결: (서버IP+port+dbname) 연결URL, 사용자아이디, 비밀번호
			String url = "jdbc:mariadb://192.168.0.112:3307/webdb?chatset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 2. sql 준비
			String sql = "select no, name from user where email=? and password=password(?)";
			pstmt = conn.prepareStatement(sql);
			
			// 3. 파라미터 바인딩
			pstmt.setString(1, email);
			pstmt.setString(2, password);
		
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 결과 처리
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading Fail:" + e);
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}

}
