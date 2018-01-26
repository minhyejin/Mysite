package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {

	public void insert(GuestbookVo gVo) {
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
		    String query = "insert into guestbook values (seq_guestbook_no.nextval, ? , ? , ? , sysdate )";
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1,gVo.getName());
			pstmt.setString(2,gVo.getPassword());
			pstmt.setString(3,gVo.getContent());
			
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
	
	public List<GuestbookVo>getList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 List<GuestbookVo> gList = new ArrayList<GuestbookVo>();
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = " select no, name , password , content , reg_date "
		    		+ " from guestbook ";
		    pstmt = conn.prepareStatement(query);
		    rs = pstmt.executeQuery();
	
		    // 4.결과처리
		    while(rs.next()) {
		    	
		    	GuestbookVo gVo = new GuestbookVo();//실제 데이타는 디비안에 있으니까 디비에서 조회해야함 
		    	
		    int no = rs.getInt("no");
		    String name = rs.getString("name");
		    String password = rs.getString("password");
		    String content = rs.getString("content");
		    String date = rs.getString("reg_date");
		  
		    
		    
		    gVo.setNo(no);
		    gVo.setName(name);
		    gVo.setPassword(password);
		    gVo.setContent(content);
		    gVo.setRegDate(date);
		 
		    gList.add(gVo);//메모리에만 넣은거니까 add해줘야함 
		    
		    
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
		return gList;
				
	}	
	public void delete(int no, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "DELETE FROM guestbook "
						 + "WHERE no = ? AND password = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			int result = pstmt.executeUpdate();

			System.out.println("처리결과 : " + result);

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
