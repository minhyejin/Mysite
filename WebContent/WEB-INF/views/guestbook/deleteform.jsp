<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
		<c:import url="/WEB-INF/views/includes/navi.jsp"></c:import> <!-- /navigation -->
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook" class="delete-form">

					<form method="get" action="gb?a=delete">
					<input type='text' name="no" value="${no }"> 
						<label>방명록 번호</label>
					
						<input type="password" name="password">	<label>비밀번호</label>
						
						<input type="text" name="a" value ="delete"><input type="submit" value="확인">
					</form>
					<a href="/gb?a=list">방명록 리스트</a>
					
				</div>
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/foot.jsp"></c:import> <!-- /footer -->
		
	</div> <!-- /container -->

</body>
</html>
