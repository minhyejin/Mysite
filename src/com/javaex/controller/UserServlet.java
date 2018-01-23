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
			
		}else if("login".equals(actionName)) {
			System.out.println("login 진입");
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			System.out.println(email + "/"+ password );
			
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(email, password);
			
			if(userVo == null) {
				System.out.println("로그인 실패");		
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
			System.out.println("modifyform 진입");
			
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
						
			request.setAttribute("userInfo", authUser);
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");	
			
		}else if("modify".equals(actionName)) {
			
			System.out.println("modify 진입");
			
			int no = Integer.valueOf(request.getParameter("no"));
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(no);
			
			userVo.setName(name);
			if(password != "")
				userVo.setPassword(password);
			userVo.setGender(gender);
			userDao.update(userVo);
			
			WebUtil.redirect(request, response, "/mysite/main");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
