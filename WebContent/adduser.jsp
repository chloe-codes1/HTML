<%@page import="vo.UsersVO"%>
<%@page import="service.UserServiceImpl"%>
<%@page import="service.UserService"%>
<%@page import="dao.UsersDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adduser.jsp</title>
</head>
<body>

	<h3>회 원 가 입 처 리</h3>

	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("PW");
		String name = request.getParameter("Name");
		String role = request.getParameter("Role");

		UsersDao dao = new UsersDao();
		UserService service = new UserServiceImpl(dao);

		UsersVO vo = new UsersVO();
		vo.setId(id);
		vo.setPassword(pw);
		vo.setName(name);
		vo.setRole(role);

		int c = service.addUser(vo);
	%>

	입력 정보:
	<%=id%>
	/
	<%=pw%>
	/
	<%=name%>
	/
	<%=role%>
	<br> 회원 가입 성공 여부:
	<%
		if (c == 0) {
	%>
	<h4>회원 가입에 실패했습니다.</h4>
	<%
		} else {
	%>
	<h4>회원 가입에 성공했습니다.</h4>
	<%
		}
	%>

	<p>
		<a href="index.html"> Home으로 돌아가기 </a>
	</p>
</body>
</html>