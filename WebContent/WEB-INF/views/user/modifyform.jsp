<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		 <!-- /header -->
		 <c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
				
		<c:import url="/WEB-INF/views/includes/navi.jsp"></c:import><!-- /navigation -->
		
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="post" action="user">

						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="${userVo.name }" />
	
						<label class="block-label" for="email">이메일</label>
						<strong>${userVo.email }</strong>
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="${userVo.password }" />
						
						
							<legend>성별</legend>
							
							<c:choose> 
							<c:when test = "${'male' == userVo.gender}">
							
							<label>여</label> <input type="radio" name="gender" value="female" >						
							<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							
							</c:when>							
							<c:otherwise>
							
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">						
							<label>남</label> <input type="radio" name="gender" value="male" >
							
							</c:otherwise>
							</c:choose>
						
						<input type="hidden" name="no" value="${userVo.no }">
						<input type="text" name="a" value="modify">
						<input type="submit" value="수정완료">
						
					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url ="/WEB-INF/views/includes/foot.jsp"></c:import><!-- /footer -->
		
	</div> <!-- /container -->

</body>
</html>
