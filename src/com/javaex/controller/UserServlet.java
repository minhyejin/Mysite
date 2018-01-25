package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("user 진입");
		String actionName = request.getParameter("a");
		if("joinform".equals(actionName)) {
			System.out.println("joinform 진입");
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");	
		}else if("join".equals(actionName)) {
			System.out.println("join 진입");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo();
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setPassword(password);
			userVo.setGender(gender);
			
			System.out.println(userVo.toString());
			
			UserDao userDao = new UserDao();
			userDao.insert(userVo);
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
		} else if("loginform".equals(actionName)) {
			System.out.println("loginform 진입");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			
		} else if("login".equals(actionName)) {
			System.out.println("login 진입");
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			System.out.println(email + "/"+ password );
			
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(email, password);
			
			if(userVo == null) {
				System.out.println("로그인 실패");	
				WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail");
				
			}else {
				System.out.println("로그인 성공");
				
				HttpSession session = request.getSession(true);//이미 request안에 있어서 new 할 필요가 없음
				session.setAttribute("authUser", userVo);//session 안에 별명과 실제이름 
				
				WebUtil.redirect(request, response, "/mysite/main");//이미 있는걸 태우는거니까 redirect
				
			}
		}else if("logout".equals(actionName)) {
				HttpSession session = request.getSession();
				session.removeAttribute("authUser");
				session.invalidate();
				WebUtil.redirect(request, response, "/mysite/main");
				
			}
		else if("modifyform".equals(actionName)) {
			System.out.println("modifyform 진입");//포워딩 하기 전에 데이터 디비에서 가져와야함 
			//pk로 가져와야 중복성이 없음 그래서 int no로 만든 다오를 가져옴 ->session에서 no를 get해옴 
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo)session.getAttribute("authUser");			
			if(authUser == null) {//no가 없으면 로그인 폼으로 이동 (redirect)		
			}else {//no가 있으면 데이터가져옴 ->다오에서 가져옴 유저객체에 담음 
				//로그인회원 no
				int no = authUser.getNo();
				//다오에서 가져옴 no
				UserDao userDao = new UserDao();
				UserVo userVo = userDao.getUser(no);
				request.setAttribute("userVo", userVo);//데이터 저장
				//데이터request에 저장 ->포워드
				WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");	
			}
		}else if("modify".equals(actionName)) {//update시키는거 
			
			System.out.println("modify 진입");
			//원래는 if문 있어야함 
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			if(authUser == null) {//로그인 실패	
			}else {//로그인 성공 
				//vo(no, name, password, gender)
			int no = authUser.getNo();
			//int no = Integer.valueOf(request.getParameter("no"));
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String password = request.getParameter("password");//web에서 보낸 정보를 변수에 담는다 
			
		    UserDao userDao = new UserDao();//db
			UserVo userVo = userDao.getUser(no);//다오에서 가져온 정보를 userVo에 저장 
			
			userVo.setName(name);
			userVo.setGender(gender);
			userVo.setPassword(password);//덮어씌움

		    userDao.update(userVo);//디비에 넣어서 업데이트해줌 
		    System.out.println(userVo.toString());
				//session name 값 변경
			authUser.setName(name);//이름이 혹시 바뀌면 같이 바뀌라고 	
				//name redirect 
				WebUtil.redirect(request, response, "/mysite/main");//저장을 시킨 후 메인으로 
			
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
