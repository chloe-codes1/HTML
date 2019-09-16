<%@page import="vo.UsersVO"%>
<%@page import="dao.UsersDao"%>
<%@page import="service.UserServiceImpl"%>
<%@page import="service.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 여기 위에 빼고는 html과 동일함 -->   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
<h3>Log-in 처리</h3>

<!-- JAVA Code 영역 -->
<%

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	
	UsersDao dao = new UsersDao();
	UserService service = new UserServiceImpl(dao);

	UsersVO vo = new UsersVO();
	vo.setId(id);
	vo.setPassword(pw);
	UsersVO data = service.login(id, pw);
	
%>

<!-- 출력 할 때 '%=' 사용 -->

입 력 정 보: <%= id %> / <%= pw %> <br/>
로그인 정보: <%= data %>


<p>
<a href="index.html"> Home으로 돌아가기 </a>
</p>

</body>
</html>