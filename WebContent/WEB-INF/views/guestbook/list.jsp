<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		 <c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url ="/WEB-INF/views/includes/navi.jsp"></c:import> <!-- /navigation -->
		
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
	
	<c:forEach items = "${list }" var ="userVo" >
	
						<li>
							<table>
								<tr>
									<td>${userVo.no }</td>
									<td>${userVo.name }</td>
									<td>${userVo.regDate }</td>
									<td><a href="/mysite/gb?a=deleteform&no=${userVo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
									${userVo.content }
										
									</td>
								</tr>
							</table>
							<br>
						</li>
							</c:forEach>
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		<c:import url="/WEB-INF/views/includes/foot.jsp"></c:import>
		<!-- /footer -->
		
	</div> <!-- /container -->
	
</body>
</html>