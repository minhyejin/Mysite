<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
		 <!-- /header -->
		 <jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		
		<div id="navigation">
			<ul>
				<li><a href="">황일영</a></li>
				<li><a href="">방명록</a></li>
				<li><a href="">게시판</a></li>
			</ul>
		</div> <!-- /navigation -->
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form action="gb" method="get">
						
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<td><input type="hidden" name="a" value="add"></td><tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
					</form>
					<ul>
		<% GuestbookDao dao = new GuestbookDao();
		List<GuestbookVo> gList = dao.getList();
		
		for(GuestbookVo gVo : gList){
			int no = gVo.getNo();
			String name = gVo.getName();
			String content = gVo.getContent();
			String date = gVo.getRegDate();
	%>
						<li>
							<table>
								<tr>
									<td><%= no %></td>
									<td><%= name %></td>
									<td><%= date %></td>
									<td><a href="gb?a=deleteform&no=<%=no %>">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
									<%= content %>
										
									</td>
								</tr>
							</table>
							<br>
						</li>
							<% } %>
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<div id="footer">
			<p>(c)opyright 2015,2016,2017</p>
		</div> <!-- /footer -->
		
	</div> <!-- /container -->
	
</body>
</html>