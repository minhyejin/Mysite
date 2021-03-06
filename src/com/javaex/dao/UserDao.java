package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import com.javaex.vo.UserVo;

public class UserDao {

	
	public void insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "insert into users values (seq_users_no.nextval, ? , ? , ? , ? )";
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1,vo.getName());
		    pstmt.setString(2,vo.getEmail());
		    pstmt.setString(3,vo.getPassword());
		    pstmt.setString(4,vo.getGender());
			
			int count = pstmt.executeUpdate();
		   
			// 4.결과처리
			System.out.println(count + "건 저장 완료");
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		       if (rs != null) {
		            rs.close();
		        }             
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
	}

	public UserVo getUser(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo userVo = null;
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = " select no, name "
		    				+ "from users "
		    				+ "where email = ? and password = ? ";
		    
		    pstmt = conn.prepareStatement(query);
		    pstmt.setString(1, email);
		    pstmt.setString(2, password);
		    
		   
		    rs = pstmt.executeQuery();
	
		    // 4.결과처리
		    while(rs.next()) {
	
		    int no = rs.getInt("no");
		    String name = rs.getString("name");
		    userVo = new UserVo();
		    userVo.setNo(no);
		    userVo.setName(name);
	 
		     } 
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
		}
		return userVo;		
	}
	public UserVo getUser(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo userVo = null;
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = " SELECT no, name, email, password, gender "
		    		       + " FROM users WHERE no = ? "
		    		       + " ORDER BY no ";
		    
		    pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				userVo = new UserVo();
				userVo.setNo(rs.getInt("no"));
				userVo.setName(rs.getString("name"));
				userVo.setEmail(rs.getString("email"));
				userVo.setPassword(rs.getString("password"));
				userVo.setGender(rs.getString("gender"));
			}
	
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
		
		return userVo;
	}
	public void update(UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "UPDATE users "
						 + "SET name = ?, email = ?, password = ?, gender = ? "
						 + "WHERE no = ?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getEmail());
			pstmt.setString(3, userVo.getPassword());
			pstmt.setString(4, userVo.getGender());
			pstmt.setInt(5, userVo.getNo());
			int count = pstmt.executeUpdate();

			System.out.println(count + "건 수정완료");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
}
