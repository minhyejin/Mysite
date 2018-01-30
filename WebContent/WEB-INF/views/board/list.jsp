<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navi.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="bo?a=list" method="get">
					
					<input type="text" id="kwd" name="kwd" value="">
					<input type = "hidden" name = "a" value = "list">
					<input type="submit" value="찾기">
				</form>
			
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items = "${list }" var ="bVo" >		
						<tr>
						<td>${bVo.no }</td>
						<td><a href="bo?a=view&no=${bVo.no }">${bVo.title }</a></td>
						<td>${bVo.writer }</td>
						<td>${bVo.hit }</td>
						<td>${bVo.regDate }</td>
						<td><c:if test="${bVo.userNo == authUser.no}">
						<a href="bo?a=delete&no=${bVo.no}&userNo=${bVo.userNo}" class="del">삭제</a>
						</c:if></td>
						</tr>
				</c:forEach>
				</table>
				<div class="pager">
				
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
					
				</div>				
				<div class="bottom">
					<c:if test="${not empty authUser}">
					<a href="bo?a=writeform" id="new-book">글쓰기</a>
					</c:if>	
				</div>				
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/foot.jsp"></c:import> <!-- /footer -->
		
	</div>
</body>
</html>