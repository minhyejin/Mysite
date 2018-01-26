package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/bo")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    public BoardServlet() {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("board 진입");
		String actionName = request.getParameter("a");
		System.out.println(actionName);
		if("list".equals(actionName)) {
			System.out.println("list 진입");
			BoardDao bDao = new BoardDao();
			List<BoardVo> bList = bDao.getList();
			request.setAttribute("list", bList);
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
			
		}else if("writeform".equals(actionName)) {
			System.out.println("writeform 진입");
			WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
			
		}else if("write".equals(actionName)) {
			System.out.println("write 진입");
			
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			
			BoardVo bVo = new BoardVo();
			bVo.setTitle(title);
			bVo.setContent(content);
			bVo.setUserNo(authUser.getNo());
			bVo.setWriter(authUser.getName());
			
			bVo.setHit(0);
			
			BoardDao bDao = new BoardDao();
			bDao.write(bVo); 
			WebUtil.redirect(request, response, "/mysite/bo?a=list");
		}
		else if("view".equals(actionName)) {
			System.out.println("view 진입");
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao bDao = new BoardDao();
			BoardVo bVo = bDao.getArticle(no);
		
			bDao.view(no, bVo.getHit()+1);
			request.setAttribute("bVo", bVo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
		}
		else if("modify".equals(actionName)) {
			System.out.println("modify 진입");
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			int no = Integer.valueOf(request.getParameter("no"));
			BoardDao bDao = new BoardDao();
			BoardVo bVo = bDao.getArticle(no);
		
			String newTitle = request.getParameter("title");
				String newContent = request.getParameter("content");
				
				bVo.setTitle(newTitle);
				bVo.setContent(newContent);
				
				bDao.modify(bVo);
				WebUtil.redirect(request, response, "bo?a=list");
			
		} else if("modifyform".equals(actionName)) {
			System.out.println("modifyform 진입");
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			int no = Integer.valueOf(request.getParameter("no"));
			BoardDao bDao = new BoardDao();
			BoardVo bVo = bDao.getArticle(no);
			
				request.setAttribute("bVo", bVo);
				WebUtil.forward(request, response, "WEB-INF/views/board/modify.jsp");		
		}else if("delete".equals(actionName)) {
			System.out.println("delete 진입");
			
			BoardDao bDao = new BoardDao();
			int no = Integer.parseInt(request.getParameter("no"));
			bDao.delete(no);
			
			WebUtil.redirect(request, response, "bo?a=list");
			
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
