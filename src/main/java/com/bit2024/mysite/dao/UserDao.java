package com.bit2024.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bit2024.mysite.vo.UserVo;

public class UserDao {

	public Boolean insert(UserVo vo) {
		Boolean result = false;
		
		try {
			// 0. JDBC Driver Loading
			Class.forName("org.mariadb.jdbc.Driver");

			// 1. 연결: (서버IP+port+dbname) 연결URL, 사용자아이디, 비밀번호
			String url = "jdbc:mariadb://192.168.0.112:3307/webdb?chatset=utf8";
			Connection conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 2. sql 준비
			String sql = "insert into user values(null, ?, ?, password(?), ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
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
		}
		
		return result;
	}

}
