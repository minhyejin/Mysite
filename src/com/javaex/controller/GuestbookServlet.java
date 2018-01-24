package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GuestbookServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("guestbook 진입");
		String actionName = request.getParameter("a");
		System.out.println(actionName);
		if("list".equals(actionName)) {
			
			System.out.println("list 진입");
			GuestbookDao gDao = new GuestbookDao();
			List<GuestbookVo> gList = gDao.getList();
			request.setAttribute("list", gList);
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		}else if("add".equals(actionName)) {
			System.out.println("add 진입");
			

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo gVo = new GuestbookVo(1, name, password, content , "");
			GuestbookDao gDao = new GuestbookDao();
			gDao.insert(gVo);
			
			WebUtil.redirect(request, response, "/mysite/gb?a=list");
	
		} else if("delete".equals(actionName)) {
			System.out.println("delete 진입");
			GuestbookDao gDao = new GuestbookDao();
			int no = Integer.valueOf(request.getParameter("no"));
 			String password = request.getParameter("password");
 			
 			gDao.delete(no, password);
 			WebUtil.redirect(request, response, "/mysite/gb?a=list");
			
		} else if("deleteform".equals(actionName)) {
			System.out.println("deleteform 진입");
			int no = Integer.valueOf(request.getParameter("no"));		
 			request.setAttribute("no", no);
			
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
		} else {
			
			System.out.println("잘못된 a값 처리 ");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
