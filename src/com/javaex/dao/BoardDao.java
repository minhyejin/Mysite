package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;


public class BoardDao {

	
	public List<BoardVo> getList(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 List<BoardVo> bList = new ArrayList<BoardVo>();
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = " select b.no, b.title, b.writer, u.name, b.content, "
		    		+ "		to_char(b.reg_date,'YYYY-MM-DD HH:MM ') reg_date, b.hit , u.no user_no "
		    		+      " from board b, users u "
		    		+      " where b.user_no = u.no  ";
		    pstmt = conn.prepareStatement(query);
		    rs = pstmt.executeQuery();
	
		    // 4.결과처리
		    while(rs.next()) {
		    	
		    	BoardVo bVo = new BoardVo();//실제 데이타는 디비안에 있으니까 디비에서 조회해야함 
		    	
		   
		    bVo.setNo(rs.getInt("no"));
			bVo.setTitle(rs.getString("title"));
			bVo.setWriter(rs.getString("writer"));
			bVo.setRegDate(rs.getString("reg_date"));
			bVo.setContent(rs.getString("content"));
			bVo.setHit(rs.getInt("hit"));
			//bVo.setName(rs.getString("name"));
			bVo.setUserNo(rs.getInt("user_no"));
			
		    
		    bList.add(bVo);//메모리에만 넣은거니까 add해줘야함 
  
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
		return bList;

	} 
	public void write(BoardVo bVo) {
		
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
		    String query = " insert into board "
		    		+ 	   " values (seq_board_no.nextval, ? , ?  , ? , sysdate , ? , 0 ) ";
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1,bVo.getTitle());
			pstmt.setString(2,bVo.getContent());
			pstmt.setInt(3,bVo.getUserNo());
			pstmt.setString(4, bVo.getWriter());
	
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
		
		
		
	}public void view(int no, int hit) {
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
		    String query = " update board  "
		    		+ 	   " set hit = ?  where no = ? ";
		    pstmt = conn.prepareStatement(query);
		    
			pstmt.setInt(1, hit);
			pstmt.setInt(2, no);

			pstmt.executeUpdate();
		   
			// 4.결과처리
			
			
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
		
		
	}public BoardVo getArticle(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo bVo = null;
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = " select no, title, content, user_no, reg_date, writer, hit  from board ";
		    pstmt = conn.prepareStatement(query);
		    rs = pstmt.executeQuery();
	
		    // 4.결과처리
		    while(rs.next()) {
		    	
		    	bVo = new BoardVo();
		    bVo = new BoardVo();
			bVo.setNo(rs.getInt("no"));
			bVo.setUserNo(rs.getInt("user_no"));
			bVo.setTitle(rs.getString("title"));
			bVo.setWriter(rs.getString("writer"));
			bVo.setHit(rs.getInt("hit"));
			bVo.setRegDate(rs.getString("reg_date"));
			bVo.setContent(rs.getString("content"));
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
		return bVo;
		
	} public void modify(BoardVo bVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " update board set title = ? , content = ? "
					+ " where no =  ? ";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getContent());
			pstmt.setInt(3, bVo.getNo());
			
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
		
		
	}public void delete(int no){
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from board "
					+ "where no = ? ";
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setInt(1,no);
			
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
